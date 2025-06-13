package br.edu.assessment.application;

import br.edu.assessment.domain.EchoMessage;
import io.javalin.Javalin;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class BasicController {
    private final Javalin server;

    public BasicController(Javalin server) {
        this.server = server;

        // Endpoint 1: /hello
        server.get("/hello", ctx -> {
            ctx.result("Hello, Javalin!");
            ctx.status(200);
        });

        // Endpoint 2: /status
        server.get("/status", ctx -> {
            var now = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            var response = Map.of(
                    "status", "ok",
                    "timestamp", now
            );
            ctx.json(response);
        });

        // Endpoint 3: /echo
        server.post("/echo", ctx -> {
            EchoMessage mensagem = ctx.bodyAsClass(EchoMessage.class);
            ctx.json(mensagem);
            ctx.status(200);
        });


        // Endpoint 4: /saudacao/{nome}
        server.get("/saudacao/{nome}", ctx -> {
            String nome = ctx.pathParam("nome");
            ctx.json(Map.of("mensagem", "Olá, " + nome + "!"));
            ctx.status(200);
        });
    }
}
