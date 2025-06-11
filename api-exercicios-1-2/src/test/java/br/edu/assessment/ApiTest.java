package br.edu.assessment;

import io.javalin.Javalin;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ApiTest {
    static Javalin server;
    static final String URL_BASE = "http://localhost:7000";

    // Configurando o servidor Javalin para os testes
    @BeforeAll
    static void init() {
        server = Api.criarApi().start(7000);
    }

    @AfterAll
    static void tearDown() {
        server.stop();
    }
    //----------------------------------------------------------

    //------------------ Testes dos endpoints ------------------
    @Test
    @DisplayName("Testando o endpoint /hello")
    void testHelloEndpoint() throws Exception {
        URL url = new URL(URL_BASE + "/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200,
                connection.getResponseCode(),
                "Código de resposta diferente."
        );
    }

    @Test
    @DisplayName("Testando o endpoint /status")
    void testStatusEndpoint() throws Exception {
        URL url = new URL(URL_BASE + "/status");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200,
                connection.getResponseCode(),
                "Código de resposta diferente."
        );
    }

    @Test
    @DisplayName("Testando o endpoint /echo")
    void testEndpointEcho() throws Exception {
        URL url = new URL(URL_BASE + "/echo");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        try (var outputStream = connection.getOutputStream()) {
            outputStream.write("Mensagem de teste".getBytes());
            outputStream.flush();
        }
        assertEquals(200,
                connection.getResponseCode(),
                "Código de resposta diferente."
        );
    }

    @Test
    @DisplayName("Testando o endpoint /saudacao/{nome}")
    void testSaudacaoEndpoint() throws Exception {
        URL url = new URL(URL_BASE + "/saudacao/Alef");
        HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200,
                connection.getResponseCode(),
                "Código de resposta diferente."
        );
    }
    //----------------------------------------------------------
}