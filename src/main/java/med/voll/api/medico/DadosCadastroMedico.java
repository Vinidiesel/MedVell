package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

//Os records serve para ser um DTO
public record DadosCadastroMedico
        (@NotBlank//não pode ser null nem estar vazio somento para campos strings
         String nome,
         @NotBlank
         @Email//verifica se o que foi digitado bate com o padrão de emails
         String email,
         @NotBlank
         String telefone,
         @NotBlank
         @Pattern(regexp = "\\d{4,6}")//o D serve para mostrar que são digitos e que vai de 4 a 6 digitos
         String crm,
         @NotNull
         Especialidade especialidade,
         @NotNull
         @Valid// para validar os outros objetos que está no outro DTO
         DadosEndereco endereco
        ) {
}
