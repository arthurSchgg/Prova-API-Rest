package br.com.avaliacao.api_telemetria.mapper;

import br.com.avaliacao.api_telemetria.dto.motor.MotorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.telemetria.TelemetriaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.telemetria.TelemetriaResponseDTO;
import br.com.avaliacao.api_telemetria.entity.HistoricoTelemetria;
import br.com.avaliacao.api_telemetria.entity.Motor;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por mapear a entidade historico e fazer as conversão dela
 *
 */
@Component
public class TelemetriaMapper {

    /**
     * Método responsável pela criação de um novo historico
     *
     * @param dto {@link TelemetriaRequestDTO} faz a conversão para a requisição do usuário
     * @return Uma criação de uma entidade
     */
    public HistoricoTelemetria toEntity(TelemetriaRequestDTO dto, Motor motor) {
        if (dto == null) {
            return null;
        }

        return HistoricoTelemetria.builder()
                .motor(motor)
                .temperaturaCarcaca(dto.temperaturaCarcaca())
                .rpmAtual(dto.rpmAtual())
                .vibracaoGlobal(dto.vibracaoGlobal())
                .build();
    }

    /**
     * Método responsável pela conversão de uma entity para o DTO
     *
     * @param entity Buscada pelo usuário
     * @return Um novo DTO pegando o que o usuário definiu
     */
    public TelemetriaResponseDTO toDTO(HistoricoTelemetria entity) {
        if (entity == null) {
            return null;
        }

        Integer motorId = (entity.getMotor() != null) ? entity.getMotor().getId() : null;

        return new TelemetriaResponseDTO(
                entity.getId(),
                motorId,
                entity.getDataHora(),
                entity.getTemperaturaCarcaca(),
                entity.getRpmAtual(),
                entity.getVibracaoGlobal()
        );
    }
}
