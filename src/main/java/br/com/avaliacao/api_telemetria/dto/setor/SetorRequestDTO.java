package br.com.avaliacao.api_telemetria.dto.setor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Classe responsável por pegar a requisição do usuário para o setor
 * @param nome Nome do Setor
 * @param localizacao Localização do setor
 */
@Schema(description = "Requisição do usuário de um setor")
public record SetorRequestDTO (
        @NotBlank(message = "O nome do setor é obrigatório")
        @Size(max = 100, message = "O nome do setor deve ter no máximo 100 caracteres")
        @Schema(
                description = "Nome do setor",
                example = "Produção"
        )
        String nome,

        @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
        @Schema(
                description = "Localização do setor",
                example = "Chão de fábrica"
        )
        String localizacao
){

}
