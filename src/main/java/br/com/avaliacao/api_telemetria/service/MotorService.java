package br.com.avaliacao.api_telemetria.service;

import br.com.avaliacao.api_telemetria.dto.motor.MotorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.motor.MotorResponseDTO;
import br.com.avaliacao.api_telemetria.dto.motor.MotorStatusUpdateDTO;
import br.com.avaliacao.api_telemetria.entity.Motor;
import br.com.avaliacao.api_telemetria.entity.Setor;
import br.com.avaliacao.api_telemetria.mapper.MotorMapper;
import br.com.avaliacao.api_telemetria.repository.MotorRepository;
import br.com.avaliacao.api_telemetria.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe que faz as regras de negócio dos motores
 */
@Service
public class MotorService {
    private final MotorRepository motorRepository;
    private final SetorRepository setorRepository;
    private final MotorMapper motorMapper;

    public MotorService(MotorRepository motorRepository, SetorRepository setorRepository, MotorMapper motorMapper) {
        this.motorRepository = motorRepository;
        this.setorRepository = setorRepository;
        this.motorMapper = motorMapper;
    }

    /**
     * Faz a criação de um novo motor
     * @param dto {@link MotorRequestDTO} Pega os dados da requisição
     * @return Um motor cadastrado
     */
    @Transactional
    public MotorResponseDTO criar(MotorRequestDTO dto) {
        if (motorRepository.existsByCodigoAtivo(dto.codigoAtivo())) {
            throw new IllegalArgumentException("Já existe um motor cadastrado com o código do ativo: " + dto.codigoAtivo());
        }

        Setor setor = buscarSetorSeInformado(dto.setorId());
        Motor motor = motorMapper.toEntity(dto, setor);

        return motorMapper.toDTO(motorRepository.save(motor));
    }

    /**
     * Lista todos os motores
     * @param pageable Serve para fazer a limitação da lista
     * @return Todos os motores
     */
    @Transactional(readOnly = true)
    public Page<MotorResponseDTO> listarTodos(Pageable pageable) {
        return motorRepository.findAll(pageable)
                .map(motorMapper::toDTO);
    }

    /**
     * Faz a busca pelo ID de um motor
     * @param id Identificador único do motor
     * @return O motor cujo ID é o mesmo do buscado
     */
    @Transactional(readOnly = true)
    public MotorResponseDTO buscarPorId(Integer id) {
        return motorRepository.findById(id)
                .map(motorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + id));
    }

    /**
     * Faz a busca pelo código ativo de um motor
     * @param codigoAtivo Código que está no motor
     * @return O motor cujo código ativo é o mesmo do buscado
     */
    @Transactional(readOnly = true)
    public MotorResponseDTO buscarPorCodigoAtivo(String codigoAtivo) {
        return motorRepository.findByCodigoAtivo(codigoAtivo)
                .map(motorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o código: " + codigoAtivo));
    }

    /**
     * Atualiza completamente um motor
     * @param id Identificador único do motor
     * @param dto {@link MotorRequestDTO} Pega os dados da requisição
     * @return Um motor atualizado
     */
    @Transactional
    public MotorResponseDTO atualizar(Integer id, MotorRequestDTO dto) {
        Motor motor = motorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + id));

        if (motorRepository.existsByCodigoAtivoAndIdNot(dto.codigoAtivo(), id)) {
            throw new IllegalArgumentException("O código de ativo informado já está em uso por outro motor.");
        }

        Setor setor = buscarSetorSeInformado(dto.setorId());

        motor.setSetor(setor);
        motor.setCodigoAtivo(dto.codigoAtivo());
        motor.setFabricante(dto.fabricante());
        motor.setModelo(dto.modelo());
        motor.setPotenciaKw(dto.potenciaKw());
        motor.setRpmNominal(dto.rpmNominal());
        motor.setDataInstalacao(dto.dataInstalacao());
        if (dto.statusAtual() != null) {
            motor.setStatusAtual(dto.statusAtual());
        }

        return motorMapper.toDTO(motorRepository.save(motor));
    }

    /**
     * Atualiza o status do motor
     * @param id Identificador único do motor
     * @param dto {@link MotorStatusUpdateDTO} faz a atualização do status
     * @return O motor com o status atualizado
     */
    @Transactional
    public MotorResponseDTO atualizarStatus(Integer id, MotorStatusUpdateDTO dto) {
        Motor motor = motorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motor não encontrado para o ID: " + id));

        motor.setStatusAtual(dto.statusAtual());
        return motorMapper.toDTO(motorRepository.save(motor));
    }

    /**
     * Busca um setor pelo ID
     * @param setorId Identificador do Setor
     * @return A confirmação se encontrou o setor
     */
    private Setor buscarSetorSeInformado(Integer setorId) {
        if (setorId == null) {
            return null;
        }
        return setorRepository.findById(setorId)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado para o ID: " + setorId));
    }
}
