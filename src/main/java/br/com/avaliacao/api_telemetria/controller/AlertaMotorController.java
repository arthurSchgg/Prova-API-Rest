package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaResolucaoDTO;
import br.com.avaliacao.api_telemetria.dto.alerta_motor.AlertaResponseDTO;
import br.com.avaliacao.api_telemetria.service.AlertaMotorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Controller dos Alertas", description = "Endpoints dos Alertas")
@RestController
@RequestMapping("/v1/api/alertas")
public class AlertaMotorController {

    private final AlertaMotorService service;

    public AlertaMotorController(AlertaMotorService service) {
        this.service = service;
    }

    @Operation(
            summary = "Faz a criação de um alerta"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Alerta criado com sucesso",
                    content = @Content(schema = @Schema(implementation = AlertaMotorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alerta não cadastrado",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<AlertaResponseDTO> criar(@RequestBody(required = true) AlertaRequestDTO requestDTO) {
        AlertaResponseDTO novoAlerta = service.criar(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(requestDTO)
                .toUri();

        return ResponseEntity.created(uri).body(novoAlerta);
    }

    @Operation(
            summary = "Lista todos os alertas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alertas listados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AlertaMotorController.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não existe nenhum alerta",
                    content = @Content
            )
    })
    @GetMapping("/listar")
    public ResponseEntity<Page<AlertaResponseDTO>> listarTodos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                               Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @Operation(
            summary = "Busca o alerta por motor"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alerta encontrado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AlertaMotorController.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alerta não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/{motorId}")
    public ResponseEntity<Page<AlertaResponseDTO>> buscarPorMotor(@Valid @PathVariable Integer motorId, @PageableDefault(size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorMotor(motorId, pageable));
    }

    @Operation(
            summary = "Lista os alertas por solução"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Alertas listados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AlertaMotorController.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não existe nenhum alerta",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<Page<AlertaResponseDTO>> listarPorStatusResolucao(@Valid @PathVariable @RequestParam Boolean resolvido,
                                                                            @RequestParam(required = true) @PageableDefault(size = 10, sort = "id",
                                                                                    direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(service.listarPorStatusResolucao(resolvido, pageable));
    }

    @Operation(
            summary = "Atualiza o status de resolução"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Status do alerta atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = AlertaMotorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não existe nenhum alerta",
                    content = @Content
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<AlertaResponseDTO> atualizarStatusResolucao(@RequestBody(required = true) @Valid @PathVariable Integer id, AlertaResolucaoDTO resolucaoDTO) {
        return ResponseEntity.ok(service.atualizarStatusResolucao(id, resolucaoDTO));
    }
}
