package br.com.avaliacao.api_telemetria.mapper;

import br.com.avaliacao.api_telemetria.dto.setor.SetorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.setor.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.entity.Setor;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por mapear a entidade setor e fazer as conversão dela
 *
 */
@Component
public class SetorMapper {

    /**
     * Método responsável pela criação de um novo setor
     *
     * @param dto {@link SetorRequestDTO} faz a conversão para a requisição do usuário
     * @return Uma criação de uma entidade
     */
    public Setor toEntity(SetorRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Setor.builder()
                .nome(dto.nome())
                .localizacao(dto.localizacao())
                .build();
    }

    /**
     * Método responsável pela conversão de uma entity para o DTO
     *
     * @param entity Buscada pelo usuário
     * @return Um novo DTO pegando o que o usuário definiu
     */
    public SetorResponseDTO toDTO(Setor entity) {
        if (entity == null) {
            return null;
        }

        return new SetorResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getLocalizacao()
        );
    }
}
