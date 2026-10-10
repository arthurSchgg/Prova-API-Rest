package br.com.avaliacao.api_telemetria.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade que representa o historico de telemetria
 *
 */
@Entity
@Table(name = "historico_telemetria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidade de historico de Telemetira")
public class HistoricoTelemetria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único do historico",
            example = "1"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motor_id", nullable = false)
    @Schema(
            description = "Identificador motor",
            example = "1"
    )
    private Motor motor;

    @Column(name = "data_hora")
    @Schema(
            description = "Data e hora do historico",
            example = "2026-10-07 08:00:00.000000"
    )
    private LocalDateTime dataHora;

    @Column(name = "temperatura_carcaca", precision = 5, scale = 2)
    @Schema(
            description = "Temperatura da carcaça",
            example = "50.10"
    )
    private BigDecimal temperaturaCarcaca;

    @Column(name = "rpm_atual")
    @Schema(
            description = "RPM atual",
            example = "3000"
    )
    private Integer rpmAtual;

    @Column(name = "vibracao_global", precision = 5, scale = 2)
    @Schema(
            description = "Vibração global",
            example = "3.20"
    )
    private BigDecimal vibracaoGlobal;

    @PrePersist
    public void prePersist() {
        if (this.dataHora == null) {
            this.dataHora = LocalDateTime.now();
        }
    }
}
