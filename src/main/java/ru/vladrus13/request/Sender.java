package ru.vladrus13.request;

import ru.vladrus13.utils.Writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Sender {

    private static final Logger logger = Logger.getLogger(Sender.class.getName());

    private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    private static final ConcurrentHashMap<String, String> answers = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    private static void take() throws IOException {
        String command = queue.poll();
        if (command == null) return;
        logger.info("Take command: " + command);
        URL url = new URL(command);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        answers.put(command, content.toString());
    }

    static {
        Runnable runnable = () -> {
            try {
                take();
            } catch (IOException e) {
                Writer.printStackTrace(logger, e);
            }
        };
        service.scheduleAtFixedRate(runnable, 0, 6, TimeUnit.SECONDS);
    }

    public static String run(String request) {
        queue.add(request);
        while (true) {
            if (answers.containsKey(request)) {
                String answer = answers.get(request);
                answers.remove(request);
                return answer;
            }
        }
    }

    public static void stop() {
        service.shutdown();
    }
}
