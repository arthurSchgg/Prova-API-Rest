package br.com.avaliacao.api_telemetria.dto.alerta_motor;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável pela resolução do alerta
 *
 * @param resolvido Resolução do alerta
 */
public record AlertaResolucaoDTO(
        @NotNull(message = "O status de resolução é obrigatório")
        Boolean resolvido
) {
}
