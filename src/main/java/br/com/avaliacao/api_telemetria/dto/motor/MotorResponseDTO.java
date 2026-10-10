package br.com.avaliacao.api_telemetria.dto.motor;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe responsável por mandar a resposta da requisição do usuário do motor
 *
 * @param id             Identificador único
 * @param setorId        Identificador do setor
 * @param setorNome      Nome do setor
 * @param codigoAtivo    Código ativo do motor
 * @param fabricante     Fabricante do motor
 * @param modelo         Modelo do motor
 * @param potenciaKw     Potência em KW
 * @param rpmNominal     RPM nominal
 * @param dataInstalacao Data de instalação
 * @param statusAtual    Status atual do motor
 */
public record MotorResponseDTO(

        @Schema(
                description = "Identificador único do motor",
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Identificador do setor",
                example = "1"
        )
        Integer setorId,

        @Schema(
                description = "Nome do setor",
                example = "Vendas"
        )
        String setorNome,

        @Schema(
                description = "Código ativo",
                example = "MOT-USI-001"
        )
        String codigoAtivo,

        @Schema(
                description = "Fabricante do motor",
                example = "WEG"
        )
        String fabricante,

        @Schema(
                description = "Modelo do motor",
                example = "Motor elétrico"
        )
        String modelo,

        @Schema(
                description = "Potência em KW",
                example = "100"
        )
        BigDecimal potenciaKw,

        @Schema(
                description = "RPM nominal",
                example = "30"
        )
        Integer rpmNominal,

        @Schema(
                description = "Data de instalação",
                example = "09/10/2026"
        )
        LocalDate dataInstalacao,

        @Schema(
                description = "Status atual do motor",
                example = "EM_OPERACAO"
        )
        String statusAtual
) {
}
