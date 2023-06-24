package med.voll.api.domain.consultas.validacoes.agendamento;

import med.voll.api.domain.ValidadcaoException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        System.out.println("antes");
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        System.out.println("depois");
        if (medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidadcaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
