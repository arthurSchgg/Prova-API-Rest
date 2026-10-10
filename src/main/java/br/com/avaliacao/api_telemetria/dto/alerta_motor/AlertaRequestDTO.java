package br.com.avaliacao.api_telemetria.dto.alerta_motor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Classe responsável por pegar a requisição do usuário para o alerta
 *
 * @param motorId      Identificador do motor
 * @param tipoAnomalia Tipo da anomalia
 * @param criticidade  Criticidade do alerta
 * @param descricao    Descrição do alerta
 */
public record AlertaRequestDTO(
        @NotNull(message = "O ID do motor é obrigatório")
        @Schema(
                description = "Identificador motor",
                example = "1"
        )
        Integer motorId,

        @NotBlank(message = "O tipo de anomalia é obrigatório")
        @Size(max = 100, message = "O tipo de anomalia deve ter no máximo 100 caracteres")
        @Schema(
                description = "Tipo de anomalia",
                example = "Superaquecimento"
        )
        String tipoAnomalia,

        @NotBlank(message = "A criticidade é obrigatória")
        @Size(max = 20, message = "A criticidade deve ter no máximo 20 caracteres")
        @Schema(
                description = "Criticidade do Alerta",
                example = "ALTA"
        )
        String criticidade,

        @Schema(
                description = "Descrição do alerta",
                example = "Superaqueceu"
        )
        String descricao
) {
}
