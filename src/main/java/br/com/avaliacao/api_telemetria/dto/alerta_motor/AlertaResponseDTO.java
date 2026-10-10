package br.com.avaliacao.api_telemetria.dto.alerta_motor;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Classe responsável por mandar a resposta da requisição do usuário do alerta
 *
 * @param id               Identificador único do alerta
 * @param motorId          Identificador do motor
 * @param codigoAtivoMotor Código ativo do motor
 * @param dataAlerta       Data e hora do alerta
 * @param tipoAnomalia     Tipo da anomalia
 * @param criticidade      Criticidade do alerta
 * @param descricao        Descrição do alerta
 * @param resolvido        Resolução do alerta
 */
public record AlertaResponseDTO(

        @Schema(
                description = "Identificador único do Alerta",
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Identificador único do Alerta",
                example = "1"
        )
        Integer motorId,

        @Schema(
                description = "Código ativo do motor",
                example = "2232132193"
        )
        String codigoAtivoMotor,

        @Schema(
                description = "Data e hora do alerta",
                example = "2026-10-07 08:00:00.000000"
        )
        LocalDateTime dataAlerta,

        @Schema(
                description = "Tipo de anomalia",
                example = "Superaquecimento"
        )
        String tipoAnomalia,

        @Schema(
                description = "Criticidade do Alerta",
                example = "ALTA"
        )
        String criticidade,

        @Schema(
                description = "Descrição do alerta",
                example = "Superaqueceu"
        )
        String descricao,


        Boolean resolvido
) {
}
