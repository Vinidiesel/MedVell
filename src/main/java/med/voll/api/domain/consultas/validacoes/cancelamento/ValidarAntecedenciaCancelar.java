package med.voll.api.domain.consultas.validacoes.cancelamento;

import med.voll.api.domain.ValidadcaoException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.cancelamento.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarAntecedenciaCancelar implements ValidadorCancelamentoDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosCancelamentoConsulta dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmMinutos < 24 ){
            throw new ValidadcaoException("Consulta deve ser cancelada com antecedencia minima de 24 horas");
        }
    }
}
