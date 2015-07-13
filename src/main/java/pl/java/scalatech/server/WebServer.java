package pl.java.scalatech.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

// telnet localhost 9999
@Slf4j
public class WebServer {
    public final static int PORT = 9999;

    public void run() throws IOException {
        ServerSocket serverSocket = null;
        log.info("+++  start...");
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                log.info("+++  start2...");
                Socket s = serverSocket.accept();
                log.info("{}", s);
                handle(s);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (serverSocket != null)
                serverSocket.close();
        }
    }

    private void handle(Socket s) {
        try (InputStream is = s.getInputStream(); OutputStream os = s.getOutputStream(); InputStreamReader isr = new InputStreamReader(is, Charsets.UTF_8)) {
            int data;
            while ((data = is.read()) != -1) {
                if (Character.isDigit(data)) {
                    System.exit(-1);
                }
                os.write(data);
            }

        } catch (IOException e1) {
            throw new UncheckedIOException(e1);

        }
    }

    private void handle2Test(Socket s) {
        try (InputStream is = s.getInputStream(); OutputStream os = s.getOutputStream(); InputStreamReader isr = new InputStreamReader(is, Charsets.UTF_8)) {
            String text = null;
            text = CharStreams.toString(isr);

            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            log.info("result :  {}", text);
            os.write(text.getBytes(Charset.forName("UTF-8")));

        } catch (IOException e1) {
            throw new UncheckedIOException(e1);

        }

    }

    public static void main(String[] args) throws IOException {
        new WebServer().run();
    }
}
