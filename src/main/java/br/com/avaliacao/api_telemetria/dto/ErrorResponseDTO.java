package br.com.avaliacao.api_telemetria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Classe responsável por mandar o erro em casos de acontecer algo errado
 *
 * @param timestamp   Data e hora
 * @param status      Status do erro
 * @param error       Erro em si
 * @param message     Mensagem do erro
 * @param fieldErrors Campos que estão incorretos
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDTO(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        Map<String, String> fieldErrors
) {
    public ErrorResponseDTO(Integer status, String error, String message) {
        this(LocalDateTime.now(), status, error, message, null);
    }

    public ErrorResponseDTO(Integer status, String error, String message, Map<String, String> fieldErrors) {
        this(LocalDateTime.now(), status, error, message, fieldErrors);
    }
}
