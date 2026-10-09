package br.com.avaliacao.api_telemetria.dto.telemetria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TelemetriaResponseDTO(
        Long id,
        Integer motorId,
        LocalDateTime dataHora,
        BigDecimal temperaturaCarcaca,
        Integer rpmAtual,
        BigDecimal vibracaoGlobal
) {
}
