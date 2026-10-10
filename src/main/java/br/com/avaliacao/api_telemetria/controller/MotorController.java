package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.motor.MotorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.motor.MotorResponseDTO;
import br.com.avaliacao.api_telemetria.dto.motor.MotorStatusUpdateDTO;
import br.com.avaliacao.api_telemetria.service.MotorService;
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

@Tag(name = "Controller dos motores", description = "Endpoints dos motores")
@RestController
@RequestMapping("/v1/api/motores")
public class MotorController {

    private final MotorService service;

    public MotorController(MotorService service) {
        this.service = service;
    }

    @Operation(
            summary = "Faz a criação de um motor"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Motor criado com sucesso",
                    content = @Content(schema = @Schema(implementation = MotorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não cadastrado",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<MotorResponseDTO> criar(@RequestBody(required = true) MotorRequestDTO requestDTO) {
        MotorResponseDTO novoMotor = service.criar(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(requestDTO)
                .toUri();

        return ResponseEntity.created(uri).body(novoMotor);
    }

    @Operation(
            summary = "Lista todos os motores"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Motores listados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MotorController.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<Page<MotorResponseDTO>> listarTodos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @Operation(
            summary = "Busca um motor pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = MotorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<MotorResponseDTO> buscarPorId(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Busca um motor pelo seu código ativo"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = MotorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/buscar/")
    public ResponseEntity<MotorResponseDTO> buscarPorCodigoAtivo(@Valid @PathVariable @RequestParam String codigoAtivo) {
        return ResponseEntity.ok(service.buscarPorCodigoAtivo(codigoAtivo));
    }

    @Operation(
            summary = "Atualiza um motor"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = MotorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<MotorResponseDTO> atualizar(@RequestBody(required = true) MotorRequestDTO requestDTO, @PathVariable Integer id) {
        return ResponseEntity.ok(service.atualizar(id, requestDTO));
    }

    @Operation(
            summary = "Atualiza o status de um motor"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Motor atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = MotorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Motor não encontrado",
                    content = @Content
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<MotorResponseDTO> atualizarStatus(@Valid @PathVariable Integer id, MotorStatusUpdateDTO statusUpdateDTO) {
        return ResponseEntity.ok(service.atualizarStatus(id, statusUpdateDTO));
    }
}
