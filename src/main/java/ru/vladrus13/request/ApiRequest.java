package ru.vladrus13.request;

import ru.vladrus13.Pair;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class ApiRequest {

    private static final String key;
    private static final String secret;

    static {
        Properties properties = new Properties();
        try {
            properties.load(ApiRequest.class.getResourceAsStream("/codeforces.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        key = properties.getProperty("key");
        secret = properties.getProperty("secret");
    }

    private static final String prefix = "https://codeforces.com/api/";

    private final String methodName;
    private final ArrayList<Pair<String, String>> keys;

    private String random6symbols() {
        Random random = new Random(System.currentTimeMillis());
        int number = random.nextInt(899999) + 100000;
        return String.valueOf(number);
    }

    public ApiRequest(String methodName, ArrayList<Pair<String, String>> keys) {
        this.methodName = methodName;
        this.keys = keys;
    }

    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static int stringCompare(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
        for (int i = 0; i < lmin; i++) {
            int str1_ch = str1.charAt(i);
            int str2_ch = str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        } else {
            return 0;
        }
    }

    public String send() {
        StringBuilder request = new StringBuilder();
        request.append(prefix);
        request.append(methodName);
        request.append("?");
        for (Pair<String, String> key : keys) {
            request.append(key.a).append("=").append(key.b).append("&");
        }
        request.append("apiKey=").append(key).append("&");
        long time = System.currentTimeMillis() / 1000;
        request.append("time=").append(time).append("&");
        String random6 = random6symbols();
        StringBuilder apiSig = new StringBuilder();
        apiSig.append(random6).append("/").append(methodName).append("?");
        keys.sort((a, b) -> stringCompare(a.a, b.a) == 0 ? stringCompare(a.b, b.b) : stringCompare(a.a, b.a));
        apiSig.append("apiKey=").append(key).append("&");
        for (Pair<String, String> key : keys) {
            apiSig.append(key.a).append("=").append(key.b).append("&");
        }
        apiSig.append("time=").append(time).append("#").append(secret);
        request.append("apiSig=").append(random6).append(sha256(apiSig.toString()));
        try {
            System.out.println("Send: " + request.toString());
            return Sender.run(request.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
