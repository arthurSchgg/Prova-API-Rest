package br.com.avaliacao.api_telemetria.service;

import br.com.avaliacao.api_telemetria.dto.setor.SetorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.setor.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.entity.Setor;
import br.com.avaliacao.api_telemetria.mapper.SetorMapper;
import br.com.avaliacao.api_telemetria.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe que faz as regras de negócio dos setores
 */
@Service
public class SetorService {
    private final SetorRepository setorRepository;
    private final SetorMapper setorMapper;

    public SetorService(SetorRepository setorRepository, SetorMapper setorMapper) {
        this.setorRepository = setorRepository;
        this.setorMapper = setorMapper;
    }

    /**
     * Faz a criação de um novo setor
     *
     * @param dto {@link SetorRequestDTO} Pega os dados da requisição
     * @return Um setor cadastrado
     */
    @Transactional
    public SetorResponseDTO criar(SetorRequestDTO dto) {
        if (setorRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new IllegalArgumentException("Já existe um setor cadastrado com o nome: " + dto.nome());
        }

        Setor setor = setorMapper.toEntity(dto);
        return setorMapper.toDTO(setorRepository.save(setor));
    }

    /**
     * Lista todos os setores cadastrados
     *
     * @param pageable Serve para fazer uma limitação da listagem
     * @return Todos os setores cadastrados
     */
    @Transactional(readOnly = true)
    public Page<SetorResponseDTO> listarTodos(Pageable pageable) {
        return setorRepository.findAll(pageable)
                .map(setorMapper::toDTO);
    }

    /**
     * Busca um setor pelo ID
     *
     * @param id Identificador único
     * @return O Setor cujo tem o mesmo ID
     */
    @Transactional(readOnly = true)
    public SetorResponseDTO buscarPorId(Integer id) {
        return setorRepository.findById(id)
                .map(setorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado para o ID: " + id));
    }

    /**
     * Atualiza completamente um setor
     *
     * @param id  Identificador único
     * @param dto {@link SetorRequestDTO} Pega os dados da requisição
     * @return Um setor atualizado
     */
    @Transactional
    public SetorResponseDTO atualizar(Integer id, SetorRequestDTO dto) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado para o ID: " + id));

        if (setorRepository.existsByNomeIgnoreCaseAndIdNot(dto.nome(), id)) {
            throw new IllegalArgumentException("Já existe outro setor cadastrado com o nome: " + dto.nome());
        }

        setor.setNome(dto.nome());
        setor.setLocalizacao(dto.localizacao());

        return setorMapper.toDTO(setorRepository.save(setor));
    }
}
