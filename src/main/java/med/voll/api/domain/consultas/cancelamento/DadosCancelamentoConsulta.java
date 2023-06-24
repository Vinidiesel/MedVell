package med.voll.api.domain.consultas.cancelamento;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consultas.cancelamento.MotivoCancelamento;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
