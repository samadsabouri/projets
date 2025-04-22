package g53960.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;

public class ContactHandler implements HttpHandler {

    private static String lastMessage = "Aucun message reçu.";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream is = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }

            try {
                JSONObject json = new JSONObject(body.toString());
                String name = json.optString("name", "Inconnu");
                String message = json.optString("message", "");
                lastMessage = "Dernier message : " + name + " a dit \"" + message + "\"";

                String response = "Bonjour " + name + ", votre message a ete recu.";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            } catch (Exception e) {
                String response = "Erreur JSON : " + e.getMessage();
                exchange.sendResponseHeaders(400, response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }

        } else if ("GET".equals(exchange.getRequestMethod())) {
            String response = lastMessage;
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            String response = "Méthode non autorisée";
            exchange.sendResponseHeaders(405, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
}
