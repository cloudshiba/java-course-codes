package java0.nio01.server;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer01 {
    private static final int PORT = 8801;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Starting Server1......at http://127.0.0.1:" + PORT + "/");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("socket " + socket);
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) throws IOException {
        OutputStream out = null;
        InputStream in = null;
        try {
//            StringBuffer stringBuffer = new StringBuffer();
//            in = socket.getInputStream();
//            InputStreamReader inR = new InputStreamReader( in );
//            BufferedReader buf = new BufferedReader( inR );
//            String line;
//            while ( ( line = buf.readLine() ) != null ) {
//                System.out.println( line );
//                stringBuffer.append(line).append(System.lineSeparator());
//            }
//            System.out.println(stringBuffer.toString());
//            System.out.println(IOUtils.toString( in ));

            out = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(out, true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body ="Hello nio1";
            printWriter.println("Content-Length:" + body.getBytes(StandardCharsets.UTF_8).length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            IOUtils.closeQuietly(in);
            if (in != null) in.close();
            if (out != null) out.close();
        }
    }
}
