package br.com.avaliacao.api_telemetria.dto.alerta_motor;

import java.time.LocalDateTime;

public record AlertaResponseDTO(
        Integer id,
        Integer motorId,
        String codigoAtivoMotor,
        LocalDateTime dataAlerta,
        String tipoAnomalia,
        String criticidade,
        String descricao,
        Boolean resolvido
) {
}
