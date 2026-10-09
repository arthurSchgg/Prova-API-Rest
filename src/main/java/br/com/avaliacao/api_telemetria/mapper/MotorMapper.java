package br.com.avaliacao.api_telemetria.mapper;

import br.com.avaliacao.api_telemetria.dto.motor.MotorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.motor.MotorResponseDTO;
import br.com.avaliacao.api_telemetria.entity.Motor;
import br.com.avaliacao.api_telemetria.entity.Setor;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por mapear a entidade setor e fazer as conversão dela
 *
 */
@Component
public class MotorMapper {

    /**
     * Método responsável pela criação de um novo motor
     * @param dto {@link MotorRequestDTO} faz a conversão para a requisição do usuário
     * @return Uma criação de uma entidade
     */
    public Motor toEntity(MotorRequestDTO dto, Setor setor) {
        if (dto == null) {
            return null;
        }

        return Motor.builder()
                .setor(setor)
                .codigoAtivo(dto.codigoAtivo())
                .fabricante(dto.fabricante())
                .modelo(dto.modelo())
                .potenciaKw(dto.potenciaKw())
                .rpmNominal(dto.rpmNominal())
                .dataInstalacao(dto.dataInstalacao())
                .statusAtual(dto.statusAtual() != null ? dto.statusAtual() : "Operando")
                .build();
    }

    /**
     * Método responsável pela conversão de uma entity para o DTO
     * @param entity Buscada pelo usuário
     * @return Um novo DTO pegando o que o usuário definiu
     */
    public MotorResponseDTO toDTO(Motor entity) {
        if (entity == null) {
            return null;
        }

        Integer setorId = entity.getSetor() != null ? entity.getSetor().getId() : null;
        String setorNome = entity.getSetor() != null ? entity.getSetor().getNome() : null;

        return new MotorResponseDTO(
                entity.getId(),
                setorId,
                setorNome,
                entity.getCodigoAtivo(),
                entity.getFabricante(),
                entity.getModelo(),
                entity.getPotenciaKw(),
                entity.getRpmNominal(),
                entity.getDataInstalacao(),
                entity.getStatusAtual()
        );
    }
}
