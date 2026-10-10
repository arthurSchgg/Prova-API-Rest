package br.com.avaliacao.api_telemetria.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa o setores
 *
 */
@Entity
@Table(name = "setores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidade Setor")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único do setor",
            example = "1"
    )
    private Integer id;

    @Column(nullable = false, length = 100)
    @Schema(
            description = "Nome do setor",
            example = "Vendas"
    )
    private String nome;

    @Column(length = 100)
    @Schema(
            description = "Localização do setor",
            example = "Escritório"
    )
    private String localizacao;
}
