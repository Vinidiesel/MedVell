package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente
        (
                String nome,
                String email,
                String telefone,
                String cpf,
                Endereco endereco
        ) {
    public DadosDetalhamentoPaciente(Paciente dados) {
        this(dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getCpf(), dados.getEndereco());
    }
}
