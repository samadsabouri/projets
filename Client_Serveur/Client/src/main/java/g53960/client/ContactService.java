package g53960.client;

import org.json.JSONObject;

import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ContactService {

    public String sendMessage(String name, String message) {
        try {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("message", message);

            URL url = new URL("http://localhost:8000/contact");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(json.toString().getBytes("utf-8"));
            }

            int code = con.getResponseCode();
            if (code == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return "✅ " + response.toString();
            } else {
                return "❌ Erreur HTTP : " + code;
            }

        } catch (Exception e) {
            return "❌ Erreur : " + e.getMessage();
        }
    }
}
