package br.com.avaliacao.api_telemetria.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidade que representa os alertas dos motores
 *
 */
@Entity
@Table(name = "alertas_motores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidade de Alerta de Motores")
public class AlertaMotor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único do Alerta",
            example = "1"
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motor_id", nullable = false)
    @Schema(
            description = "Identificador único do Alerta",
            example = "1"
    )
    private Motor motor;

    @Column(name = "data_alerta")
    @Schema(
            description = "Data e hora do alerta",
            example = "2026-10-07 08:00:00.000000"
    )
    private LocalDateTime dataAlerta;

    @Column(name = "tipo_anomalia", length = 100)
    @Schema(
            description = "Tipo de anomalia",
            example = "Superaquecimento"
    )
    private String tipoAnomalia;

    @Column(length = 20)
    @Schema(
            description = "Criticidade do Alerta",
            example = "ALTA"
    )
    private String criticidade;

    @Column(columnDefinition = "TEXT")
    @Schema(
            description = "Descrição do alerta",
            example = "Superaqueceu"
    )
    private String descricao;

    @Builder.Default
    private Boolean resolvido = false;

    @PrePersist
    public void prePersist() {
        if (this.dataAlerta == null) {
            this.dataAlerta = LocalDateTime.now();
        }
    }
}
