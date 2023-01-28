package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;

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
