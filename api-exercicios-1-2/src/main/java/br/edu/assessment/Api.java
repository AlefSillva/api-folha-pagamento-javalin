package br.edu.assessment;

import io.javalin.Javalin;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Api {
    public static Javalin criarApi(){
        Javalin app = Javalin.create();

        app.get("/hello", ctx -> ctx.result("Hello, Javalin!"));

        // Configurando a rota /status
        app.get("/status", ctx -> {
            var now = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            var response = Map.of(
                    "status", "ok",
                    "timestamp", now
            );
            ctx.json(response);
        });
        //----------------------------------------------------------

        // Configurando a rota /echo
        app.post("/echo", ctx -> {
            String body = ctx.body();
            var response = Map.of(
                    "mensagem", body
            );
            ctx.json(response);
        });

        //----------------------------------------------------------

        // Configurando a rota /saudacao/{nome}
        app.get("/saudacao/{nome}", ctx -> {
            String nome = ctx.pathParam("nome");
            var response = Map.of("mensagem", "Olá, " + nome + "!");
            ctx.json(response);
        });
        //----------------------------------------------------------

        return app;
    }
}
