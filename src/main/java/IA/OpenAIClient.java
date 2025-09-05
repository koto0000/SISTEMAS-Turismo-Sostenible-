package IA;

import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;

public class OpenAIClient {
    private final String apiKey;

    public OpenAIClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String recomendarDestino(String preferencias) throws Exception {
        String body = """
        {
            "model": "gpt-3.5-turbo",
            "messages": [{"role": "user", "content": "Recomiéndame un destino turístico en Perú para: %s"}]
        }
        """.formatted(preferencias);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openai.com/v1/chat/completions"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + apiKey)
            .POST(BodyPublishers.ofString(body))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    //private OpenAIClient iaClient = new OpenAIClient("sk-..."); // Tu API Key real
}