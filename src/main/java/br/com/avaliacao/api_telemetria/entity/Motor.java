package br.com.avaliacao.api_telemetria.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa o motores
 *
 */
@Entity
@Table(name = "motores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidade motor")
public class Motor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único do motor",
            example = "1"
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id")
    @Schema(
            description = "Identificador do setor",
            example = "1"
    )
    private Setor setor;

    @Column(name = "codigo_ativo", nullable = false, unique = true, length = 50)
    @Schema(
            description = "Código ativo",
            example = "MOT-USI-001"
    )
    private String codigoAtivo;

    @Schema(
            description = "Fabricante do motor",
            example = "WEG"
    )
    private String fabricante;

    @Schema(
            description = "Modelo do motor",
            example = "Motor elétrico"
    )
    private String modelo;

    @Column(name = "potencia_kw", precision = 6, scale = 2)
    @Schema(
            description = "Potência em KW",
            example = "100"
    )
    private BigDecimal potenciaKw;

    @Column(name = "rpm_nominal")
    @Schema(
            description = "RPM nominal",
            example = "30"
    )
    private Integer rpmNominal;

    @Column(name = "data_instalacao")
    @Schema(
            description = "Data de instalação",
            example = "09/10/2026"
    )
    private LocalDate dataInstalacao;

    @Column(name = "status_atual", length = 30)
    @Schema(
            description = "Status atual do motor",
            example = "EM_OPERACAO"
    )
    private String statusAtual;
}
