package g53960.server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ContactServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/contact", new ContactHandler());
        server.setExecutor(null);
        System.out.println("✅ Serveur démarré sur http://localhost:8000/contact");
        server.start();
    }
}
