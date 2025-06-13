package br.edu.assessment.infrastructure;

import br.edu.assessment.domain.Funcionario;
import br.edu.assessment.domain.Mensalista;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService() {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    public List<Mensalista> findAllMensalistas() {
        return funcionarioRepository.findAll().stream()
                .filter(f -> f instanceof Mensalista)
                .map(f -> (Mensalista) f)
                .collect(Collectors.toList());
    }

    public Mensalista findMensalistaByMatricula(long matricula) {
        return funcionarioRepository.findAll().stream()
                .filter(f -> f instanceof Mensalista && f.getMatricula() == matricula)
                .map(f -> (Mensalista) f)
                .findFirst()
                .orElse(null);
    }

    public Mensalista criarMensalista(Mensalista mensalista) {
        if (mensalista == null) {
            throw new IllegalArgumentException("Mensalista inválido");
        }

        if (mensalista.getNome() == null || mensalista.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do mensalista é obrigatório");
        }

        if (mensalista.getMatricula() <= 0) {
            throw new IllegalArgumentException("Matrícula inválida");
        }

        if (mensalista.getSalario() <= 0) {
            throw new IllegalArgumentException("Salário do mensalista deve ser maior que zero");
        }

        mensalista.setId(UUID.randomUUID().toString());
        return (Mensalista) funcionarioRepository.insert(mensalista);
    }

}
