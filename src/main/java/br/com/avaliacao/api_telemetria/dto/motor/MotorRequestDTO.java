package br.com.avaliacao.api_telemetria.dto.motor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe responsável por pegar a requisição do usuário para o motor
 *
 * @param setorId        Identificador do setor
 * @param codigoAtivo    Código do motor
 * @param fabricante     Fabricante do motor
 * @param modelo         Modelo do motor
 * @param potenciaKw     Potência em KW
 * @param rpmNominal     RPM nominal
 * @param dataInstalacao Data de instalação
 * @param statusAtual    Status atual do motor
 */
public record MotorRequestDTO(

        @Schema(
                description = "Identificador do setor",
                example = "1"
        )
        Integer setorId,

        @NotBlank(message = "O código do ativo é obrigatório")
        @Size(max = 50, message = "O código do ativo deve ter no máximo 50 caracteres")
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

        @Positive(message = "A potência deve ser um valor positivo")
        @Schema(
                description = "Potência em KW",
                example = "100"
        )
        BigDecimal potenciaKw,

        @Positive(message = "O RPM nominal deve ser um valor positivo")
        @Schema(
                description = "RPM nominal",
                example = "30"
        )
        Integer rpmNominal,

        @Schema(
                description = "Data de instalação",
                example = "2026-10-09"
        )
        LocalDate dataInstalacao,

        @Schema(
                description = "Status atual do motor",
                example = "EM_OPERACAO"
        )
        String statusAtual
) {
}
