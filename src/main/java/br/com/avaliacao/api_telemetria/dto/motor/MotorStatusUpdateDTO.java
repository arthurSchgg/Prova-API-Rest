package br.com.avaliacao.api_telemetria.dto.motor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Faz a atualização do status do motor
 *
 * @param statusAtual Status atual do motor
 */
public record MotorStatusUpdateDTO(
        @NotBlank(message = "O status atual é obrigatório")
        @Schema(
                description = "Status atual do motor",
                example = "EM_OPERACAO"
        )
        String statusAtual
) {
}
