package med.voll.api.domain.consultas.validacoes.cancelamento;

import med.voll.api.domain.consultas.cancelamento.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsultas {

    void validar(DadosCancelamentoConsulta dados);

}
