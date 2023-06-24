package med.voll.api.domain.consultas.validacoes.agendamento;

import med.voll.api.domain.ValidadcaoException;
import med.voll.api.domain.consultas.agendamento.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = repository. findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo){
            throw new ValidadcaoException("Consulta não pode ser agendada com paciente excluido");
        }
    }

}
