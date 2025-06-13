package br.edu.assessment.application;

import io.javalin.Javalin;
import org.junit.jupiter.api.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;


class ApiTest {
    private static Javalin server;
    static final String URL_BASE = "http://localhost:7000";

    @BeforeAll
    static void init() {
        server = Api.criarServidor();
        server.start(7000);
    }

    @AfterAll
    static void tearDown() {
        server.stop();
    }

    @Test
    @DisplayName("Testando o endpoint /hello com status e corpo")
    void testHelloEndpoint() throws Exception {
        URL url = new URL(URL_BASE + "/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode());

        try (var inputStream = connection.getInputStream()) {
            String responseBody = new String(inputStream.readAllBytes());
            assertEquals("Hello, Javalin!", responseBody);
        }
    }
    //-------------------------------------------------------------

    @Test
    @DisplayName("Testando criação de Mensalista (POST)")
    void testCriarMensalista() throws Exception {
        URL url = new URL(URL_BASE + "/funcionarios/mensalistas");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonNovoFuncString = """
        {
            "matricula": 95974,
            "nome": "Fulano de Tal",
            "cargo": "Analista Pleno",
            "salario": 4500.0
        }
        """;

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonNovoFuncString.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = connection.getResponseCode();
        assertEquals(201, responseCode);
    }
    //-------------------------------------------------------------

    @Test
    @DisplayName("Testando busca de Mensalista por matrícula")
    void testBuscarMensalistaPorMatricula() throws Exception {
        // Primeiro cria um mensalista
        testCriarMensalista();

        long matricula = 95974;
        URL url = new URL(URL_BASE + "/funcionarios/mensalistas/" + matricula);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode());

        try (var inputStream = connection.getInputStream()) {
            String responseBody = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            assertTrue(responseBody.contains("\"matricula\":" + matricula));
            assertTrue(responseBody.contains("\"nome\":\"Fulano de Tal\""));
        }
    }
    //-------------------------------------------------------------

    @Test
    @DisplayName("Testando listagem de Mensalistas não vazia")
    void testListarMensalistas() throws Exception {
        // Criar pelo menos um mensalista para garantir que lista não será vazia
        testCriarMensalista();

        URL url = new URL(URL_BASE + "/funcionarios/mensalistas");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode());

        try (var inputStream = connection.getInputStream()) {
            String responseBody = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            assertTrue(responseBody.startsWith("[") && responseBody.length() > 2);
        }
    }
}