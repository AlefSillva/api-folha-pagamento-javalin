package br.edu.assessment;

import io.javalin.Javalin;

public class Api {
    public static void main(String[] args) {
        //------------------------------------------------
        Javalin app = Javalin.create()
                .get("/hello", ctx -> ctx.html("<h1>Hello, Javalin!</h1>"))
                .start(7000);
        //------------------------------------------------


    }
}
