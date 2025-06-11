package br.edu.assessment.api;

import io.javalin.Javalin;
import io.javalin.http.Context;
public class MensalistaController {
    public static void configure(Javalin app) {
        app.get("/status", ctx -> ctx.json());

    }
}
