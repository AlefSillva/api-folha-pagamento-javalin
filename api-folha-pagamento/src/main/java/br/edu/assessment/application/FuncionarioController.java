package br.edu.assessment.application;

import br.edu.assessment.domain.Funcionario;
import br.edu.assessment.domain.Mensalista;
import br.edu.assessment.infrastructure.FuncionarioService;
import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

public class FuncionarioController {
    private final Javalin server;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(Javalin server, FuncionarioService funcionarioService) {
        this.server = server;
        this.funcionarioService = funcionarioService;
        registrarRotas();
    }

    private void registrarRotas() {
        // GET - Listar todos os mensalistas
        server.get("/funcionarios/mensalistas", ctx -> {
            List<Mensalista> mensalistas = funcionarioService.findAllMensalistas();
            ctx.json(mensalistas);
            ctx.status(200);
        });

        // GET - Buscar mensalista por matrícula
        server.get("/funcionarios/mensalistas/{matricula}", ctx -> {
            try {
                long matricula = Long.parseLong(ctx.pathParam("matricula"));
                Mensalista mensalista = funcionarioService.findMensalistaByMatricula(matricula);

                if (mensalista != null) {
                    ctx.json(mensalista);
                    ctx.status(200);
                } else {
                    ctx.status(404).json(Map.of("error", "Mensalista não encontrado."));
                }
            } catch (NumberFormatException e) {
                ctx.status(400).json(Map.of("error", "Matrícula inválida."));
            }
        });

        // POST - Criar um novo mensalista
        server.post("/funcionarios/mensalistas", ctx -> {
            try {
                Mensalista novoMensalista = ctx.bodyAsClass(Mensalista.class);
                Mensalista criado = funcionarioService.criarMensalista(novoMensalista);
                ctx.status(201).json(criado);
            } catch (Exception e) {
                ctx.status(400).json(Map.of("error", "Dados inválidos: " + e.getMessage()));
            }
        });
    }
}
