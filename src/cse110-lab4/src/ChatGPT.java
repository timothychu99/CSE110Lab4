package src;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT{
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";
     
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException
{
        // Set request parameters
        String prompt = "What is velocity in agile development?";
        int maxTokens = 100;


        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        // Create the HTTP Client
        HttpClient client = HttpClient.newHttpClient();

//        String jsonPayload = "{\"model\":\"text-davinci-003\",\"prompt\": \"who is the tallest man in the world!\" ,\"max_tokens\":50}";
        String jsonPayload2 = "{\"model\":\"text-davinci-003\",\"prompt\": \"" + args[1] + "\" ,\"max_tokens\":" + args[0] + "}";

        //filled
        URI API_ENDPOINT_URI = new URI(API_ENDPOINT);

        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(API_ENDPOINT_URI) /*filled */
        .header("Content-Type", "application/json") /* Filled */
        .header("Authorization", String.format("Bearer %s", API_KEY)) /* need secret key*/
        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload2)) /* Put in the login and password */
        .build();


        // Send the request and receive the response
        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );

        // Process the response
        String responseBody = response.body();

//        System.out.println(responseBody);
        

        JSONObject responseJson = new JSONObject(responseBody);


        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");


        System.out.println(generatedText);

    }
}