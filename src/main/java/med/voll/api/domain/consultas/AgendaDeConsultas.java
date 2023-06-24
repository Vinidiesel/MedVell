package med.voll.api.domain.consultas;

import med.voll.api.domain.ValidadcaoException;
import med.voll.api.domain.consultas.cancelamento.DadosCancelamentoConsulta;
import med.voll.api.domain.consultas.agendamento.DadosAgendamentoConsulta;
import med.voll.api.domain.consultas.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consultas.validacoes.cancelamento.ValidadorCancelamentoDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//Usada para fazer as regras de negocio
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadoresAgendamento;
    @Autowired
    private List<ValidadorCancelamentoDeConsultas> validadoresCancelamento;


    public DadosDetalhamentoConsultas agendar(DadosAgendamentoConsulta dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidadcaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidadcaoException("Id do medico informado não existe!");
        }

        validadoresAgendamento.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if (medico == null){
            throw new ValidadcaoException("Não existe medico disponivel nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsultas(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw  new ValidadcaoException("Especialidade é obrigatoria quando medico não for escolhida!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idConsulta())){
            throw new ValidadcaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        validadoresCancelamento.forEach(v -> v.validar(dados));

        consulta.cancelar(dados.motivo());

    }
}
