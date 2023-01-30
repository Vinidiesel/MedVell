package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

//Os records serve para ser um DTO
public record DadosCadastroMedico
        (@NotBlank(message = "Nome é obrigatório")//não pode ser null nem estar vazio somento para campos strings
         String nome,
         @NotBlank(message = "Email é obrigatório")
         @Email(message = "Formato do email é inválido")//verifica se o que foi digitado bate com o padrão de emails
         String email,
         @NotBlank(message = "Telefone é obrigatório")
         String telefone,
         @NotBlank(message = "CRM é obrigatório")
         @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")//o D serve para mostrar que são digitos e que vai de 4 a 6 digitos
         String crm,
         @NotNull(message = "Especialidade é obrigatório")
         Especialidade especialidade,
         @NotNull(message = "Dados do endereço são obrigatórios")
         @Valid// para validar os outros objetos que está no outro DTO
         DadosEndereco endereco
        ) {
}
