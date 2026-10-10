package br.com.avaliacao.api_telemetria.dto.telemetria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Classe responsável por pegar a requisição do usuário para o historico
 *
 * @param motorId            Identificador do motor
 * @param temperaturaCarcaca Temperatura da carcaça
 * @param rpmAtual           RPM atual
 * @param vibracaoGlobal     Vibração global
 */
public record TelemetriaRequestDTO(
        @NotNull(message = "O ID do motor é obrigatório")
        @Schema(
                description = "Identificador motor",
                example = "1"
        )
        Integer motorId,

        @NotNull(message = "A temperatura é obrigatória")
        @Schema(
                description = "Temperatura da carcaça",
                example = "50.10"
        )
        BigDecimal temperaturaCarcaca,

        @NotNull(message = "O RPM é obrigatório")
        @Schema(
                description = "RPM atual",
                example = "3000"
        )
        Integer rpmAtual,

        @NotNull(message = "A vibração é obrigatória")
        @Schema(
                description = "Vibração global",
                example = "3.20"
        )
        BigDecimal vibracaoGlobal
) {
}
