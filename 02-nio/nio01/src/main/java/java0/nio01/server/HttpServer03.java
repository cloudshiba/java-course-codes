package java0.nio01.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer03 {
    private static final int PORT = 8803;

    public static void main(String[] args) throws IOException {
        int processorsSize = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processorsSize + 2);
        final ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Starting Server3......at http://127.0.0.1:" + PORT + "/");
        System.out.println("Processors size: " + processorsSize);

        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(out);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "Hello nio3";
            printWriter.println("Content-Length:" + body.getBytes(StandardCharsets.UTF_8).length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
