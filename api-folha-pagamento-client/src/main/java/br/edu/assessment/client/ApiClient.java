package br.edu.assessment.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:7000";

    public static String criarMensalista() throws IOException {
        URL url = new URL(BASE_URL + "/funcionarios/mensalistas");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = """
            {
                "matricula": 1001,
                "nome": "João Silva",
                "cargo": "Desenvolvedor",
                "salario": 5000.0
            }
            """;

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonInputString.getBytes(StandardCharsets.UTF_8));
        }

        return "POST criarMensalista: " + connection.getResponseCode();
    }

    public static String listarMensalistas() throws IOException {
        URL url = new URL(BASE_URL + "/funcionarios/mensalistas");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream is = connection.getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static String buscarMensalistaPorMatricula(long matricula) throws IOException {
        URL url = new URL(BASE_URL + "/funcionarios/mensalistas/" + matricula);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream is = connection.getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static String consultarStatus() throws IOException {
        URL url = new URL(BASE_URL + "/status");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream is = connection.getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
