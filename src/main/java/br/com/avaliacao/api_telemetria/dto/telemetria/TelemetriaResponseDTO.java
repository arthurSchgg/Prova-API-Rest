package br.com.avaliacao.api_telemetria.dto.telemetria;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Classe responsável por mandar a resposta da requisição do usuário do historico
 *
 * @param id                 Identificador único do historico
 * @param motorId            Identificador do motor
 * @param dataHora           Data e hora do historico
 * @param temperaturaCarcaca Temperatura da carcaça
 * @param rpmAtual           RPM atual
 * @param vibracaoGlobal     Vibração global
 */
public record TelemetriaResponseDTO(

        @Schema(
                description = "Identificador único do historico",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Identificador motor",
                example = "1"
        )
        Integer motorId,

        @Schema(
                description = "Data e hora do historico",
                example = "2026-10-07 08:00:00.000000"
        )
        LocalDateTime dataHora,

        @Schema(
                description = "Temperatura da carcaça",
                example = "50.10"
        )
        BigDecimal temperaturaCarcaca,

        @Schema(
                description = "RPM atual",
                example = "3000"
        )
        Integer rpmAtual,

        @Schema(
                description = "Vibração global",
                example = "3.20"
        )
        BigDecimal vibracaoGlobal
) {
}
