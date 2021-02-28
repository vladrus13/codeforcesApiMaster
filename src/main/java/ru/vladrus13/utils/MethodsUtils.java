package ru.vladrus13.utils;

import org.json.JSONObject;
import ru.vladrus13.Pair;
import ru.vladrus13.exception.FailedAPIRequestException;
import ru.vladrus13.request.ApiRequest;

import java.util.ArrayList;
import java.util.List;

public class MethodsUtils {
    public static ArrayList<Pair<String, String>> getArguments(List<String> names, List<Object> arguments) {
        if (names.size() != arguments.size()) {
            throw new IllegalArgumentException("Names size and arguments size not equal");
        }
        ArrayList<Pair<String, String>> returned = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Object object = arguments.get(i);
            if (object != null) {
                String stringFromObject;
                if (object instanceof ArrayList) {
                    stringFromObject = String.join(";", (CharSequence) object);
                } else {
                    stringFromObject = object.toString();
                }
                returned.add(new Pair<>(names.get(i), stringFromObject));
            }
        }
        return returned;
    }

    public static Object send(String type, ArrayList<Pair<String, String>> arguments) throws FailedAPIRequestException {
        ApiRequest apiRequest = new ApiRequest(
                type,
                arguments);
        String answer = apiRequest.send();
        JSONObject object = new JSONObject(answer);
        String status = (String) object.get("status");
        if (!status.equals("OK")) {
            throw new FailedAPIRequestException();
        }
        return object.get("result");
    }
}
