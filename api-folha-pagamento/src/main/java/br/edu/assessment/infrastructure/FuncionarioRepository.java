package br.edu.assessment.infrastructure;

import br.edu.assessment.domain.Funcionario;
import br.edu.assessment.domain.Mensalista;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FuncionarioRepository {

    private final List<Funcionario> funcionarios = new ArrayList<>();

    // Construtor que carrega dados iniciais de funcionários
    public FuncionarioRepository() {
        carregarDadosIniciais();
    }

    // Carregar dados iniciais de funcionários para simulação
    private void carregarDadosIniciais() {
        Mensalista m1 = new Mensalista(12345, "Alef Silva", "Analista", 3500.0);
        m1.setId(UUID.randomUUID().toString());
        funcionarios.add(m1);

        Mensalista m2 = new Mensalista(54321, "Alef Santos", "Gerente", 5000.0);
        m2.setId(UUID.randomUUID().toString());
        funcionarios.add(m2);
    }

    // Retornar todos os funcionários armazenados
    public List<Funcionario> findAll() {
        return new ArrayList<>(funcionarios);
    }

    // Inserir um novo funcionário na lista
    public Funcionario insert(Funcionario funcionario) {
        if (funcionario.getId() == null || funcionario.getId().isEmpty()) {
            funcionario.setId(UUID.randomUUID().toString());
        }
        funcionarios.add(funcionario);
        return funcionario;
    }

    // Buscar funcionário pelo ID
    public Funcionario findById(String id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() != null && funcionario.getId().equals(id)) {
                return funcionario;
            }
        }
        return null;
    }

    public Funcionario findByMatricula(long matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }
}
