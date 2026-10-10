package br.com.avaliacao.api_telemetria.mapper;

import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaResponseDTO;
import br.com.avaliacao.api_telemetria.dto.motor.MotorRequestDTO;
import br.com.avaliacao.api_telemetria.entity.AlertaMotor;
import br.com.avaliacao.api_telemetria.entity.Motor;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por mapear a entidade alerta e fazer as conversão dela
 *
 */
@Component
public class AlertaMapper {

    /**
     * Método responsável pela criação de um novo alerta
     *
     * @param dto {@link AlertaRequestDTO} faz a conversão para a requisição do usuário
     * @return Uma criação de uma entidade
     */
    public AlertaMotor toEntity(AlertaRequestDTO dto, Motor motor) {
        if (dto == null) {
            return null;
        }

        return AlertaMotor.builder()
                .motor(motor)
                .tipoAnomalia(dto.tipoAnomalia())
                .criticidade(dto.criticidade())
                .descricao(dto.descricao())
                .build();
    }

    /**
     * Método responsável pela conversão de uma entity para o DTO
     *
     * @param entity Buscada pelo usuário
     * @return Um novo DTO pegando o que o usuário definiu
     */
    public AlertaResponseDTO toDTO(AlertaMotor entity) {
        if (entity == null) {
            return null;
        }

        Integer motorId = entity.getMotor() != null ? entity.getMotor().getId() : null;
        String codigoAtivo = entity.getMotor() != null ? entity.getMotor().getCodigoAtivo() : null;

        return new AlertaResponseDTO(
                entity.getId(),
                motorId,
                codigoAtivo,
                entity.getDataAlerta(),
                entity.getTipoAnomalia(),
                entity.getCriticidade(),
                entity.getDescricao(),
                entity.getResolvido()
        );
    }
}
