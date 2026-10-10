package br.com.avaliacao.api_telemetria.dto.setor;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Classe responsável por mandar a resposta da requisição do usuário do setor
 *
 * @param id          Identificador único do Setor
 * @param nome        Nome do Setor
 * @param localizacao Localização do setor
 */
public record SetorResponseDTO(

        @Schema(
                description = "Identificador único do setor",
                example = "10"
        )
        Integer id,

        @Schema(
                description = "Nome do setor",
                example = "Produção"
        )
        String nome,

        @Schema(
                description = "Localização do setor",
                example = "Chão de fábrica"
        )
        String localizacao
) {
}
