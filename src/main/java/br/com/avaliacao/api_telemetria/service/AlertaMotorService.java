package br.com.avaliacao.api_telemetria.service;

import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaResolucaoDTO;
import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaResponseDTO;
import br.com.avaliacao.api_telemetria.entity.AlertaMotor;
import br.com.avaliacao.api_telemetria.entity.Motor;
import br.com.avaliacao.api_telemetria.mapper.AlertaMapper;
import br.com.avaliacao.api_telemetria.repository.AlertaMotorRepository;
import br.com.avaliacao.api_telemetria.repository.MotorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe que faz as regras de negócio dos alertas
 */
@Service
public class AlertaMotorService {
    private final AlertaMotorRepository alertaRepository;
    private final MotorRepository motorRepository;
    private final AlertaMapper alertaMapper;

    public AlertaMotorService(AlertaMotorRepository alertaRepository, MotorRepository motorRepository, AlertaMapper alertaMapper) {
        this.alertaRepository = alertaRepository;
        this.motorRepository = motorRepository;
        this.alertaMapper = alertaMapper;
    }

    /**
     * Faz a criação de um alerta
     *
     * @param dto {@link AlertaRequestDTO} Pega os dados da requisição
     * @return Um alerta cadastrado
     */
    @Transactional
    public AlertaResponseDTO criar(AlertaRequestDTO dto) {
        Motor motor = motorRepository.findById(dto.motorId())
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + dto.motorId()));

        AlertaMotor alerta = alertaMapper.toEntity(dto, motor);
        return alertaMapper.toDTO(alertaRepository.save(alerta));
    }

    /**
     * Lista todos os alertas
     *
     * @param pageable Serve para fazer a limitação da lista
     * @return Todos os alertas
     */
    @Transactional(readOnly = true)
    public Page<AlertaResponseDTO> listarTodos(Pageable pageable) {
        return alertaRepository.findAll(pageable)
                .map(alertaMapper::toDTO);
    }

    /**
     * Lista os alertas por status de resolução
     *
     * @param resolvido Verifica a resolução
     * @param pageable  Serve para fazer a limitação da lista
     * @return Os alertas por status de resolução
     */
    @Transactional(readOnly = true)
    public Page<AlertaResponseDTO> listarPorStatusResolucao(Boolean resolvido, Pageable pageable) {
        return alertaRepository.findByResolvidoOrderByDataAlertaDesc(resolvido, pageable)
                .map(alertaMapper::toDTO);
    }

    /**
     * Faz a busca do historico pelo ID do motor
     *
     * @param motorId  Identificador do motor
     * @param pageable Serve para fazer a limitação da lista
     * @return O historico cuso ID do motor é o mesmo do buscado
     */
    @Transactional(readOnly = true)
    public Page<AlertaResponseDTO> buscarPorMotor(Integer motorId, Pageable pageable) {
        if (!motorRepository.existsById(motorId)) {
            throw new EntityNotFoundException("Motor não encontrado para o ID: " + motorId);
        }
        return alertaRepository.findByMotorIdOrderByDataAlertaDesc(motorId, pageable)
                .map(alertaMapper::toDTO);
    }

    /**
     * Atualiza o status de resolução
     *
     * @param id  Identificador único do alerta
     * @param dto {@link AlertaResolucaoDTO} Verifica a resolução pelo DTO
     * @return O alerta com a resolução atualizada
     */
    @Transactional
    public AlertaResponseDTO atualizarStatusResolucao(Integer id, AlertaResolucaoDTO dto) {
        AlertaMotor alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado para o ID: " + id));

        alerta.setResolvido(dto.resolvido());
        return alertaMapper.toDTO(alertaRepository.save(alerta));
    }
}
