package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.telemetria.TelemetriaRequestDTO;
import br.com.avaliacao.api_telemetria.dto.telemetria.TelemetriaResponseDTO;
import br.com.avaliacao.api_telemetria.service.TelemetriaService;
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

@Tag(name = "Controller do Historico", description = "Endpoints dos historicos")
@RestController
@RequestMapping("/v1/api/telemetria")
public class TelemetriaController {

    private final TelemetriaService service;

    public TelemetriaController(TelemetriaService service) {
        this.service = service;
    }

    @Operation(
            summary = "Lista todos os historicos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Historicos listados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TelemetriaController.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Historico não encontrado",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<Page<TelemetriaResponseDTO>> listarTodos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @Operation(
            summary = "Busca um historico pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Historico encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = TelemetriaController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Historico não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TelemetriaResponseDTO> buscarPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Lista os historicos pelos motores"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Historico encontrado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TelemetriaController.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Historico não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/motorID/{motorId}")
    public ResponseEntity<Page<TelemetriaResponseDTO>> listarPorMotor(@Valid @PathVariable Integer motorId,
                                                                      @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorMotor(motorId, pageable));
    }


    @Operation(
            summary = "Faz a criação de um historico"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Historico criado com sucesso",
                    content = @Content(schema = @Schema(implementation = TelemetriaController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Historico não cadastrado",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<TelemetriaResponseDTO> registrar(@RequestBody(required = true) TelemetriaRequestDTO requestDTO) {

        TelemetriaResponseDTO novoHistorico = service.registrar(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(requestDTO)
                .toUri();

        return ResponseEntity.created(uri).body(novoHistorico);
    }

}
