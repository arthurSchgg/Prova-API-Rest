package br.com.avaliacao.api_telemetria.controller;

import br.com.avaliacao.api_telemetria.dto.setor.SetorRequestDTO;
import br.com.avaliacao.api_telemetria.dto.setor.SetorResponseDTO;
import br.com.avaliacao.api_telemetria.service.SetorService;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Controller de setores", description = "Endpoints dos setores")
@RestController
@RequestMapping("/v1/api/setores")
public class SetorController {

    private final SetorService service;

    public SetorController(SetorService service) {
        this.service = service;
    }

    @Operation(
        summary = "Cria um setor"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Setor criado com sucesso",
                    content = @Content(schema = @Schema(implementation = SetorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Setor não encontrado",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<SetorResponseDTO> criar(@RequestBody (required = true) SetorRequestDTO requestDTO) {
        SetorResponseDTO novoSetor = service.criar(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(requestDTO)
                .toUri();

        return ResponseEntity.created(uri).body(novoSetor);
    }

    @Operation(
            summary = "Lista todos os setores"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SetorController.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Setor não cadastrado",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<Page<SetorResponseDTO>> listarTodos(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @Operation(
            summary = "Busca um setor pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Setor encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = SetorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Setor não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> buscarPorId(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Atualiza um setor pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Setor atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = SetorController.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Setor não encontrado",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> atualizar(@RequestBody (required = true) SetorRequestDTO requestDTO,
                                                      @PathVariable Integer id) {
        return ResponseEntity.ok(service.atualizar(id, requestDTO));
    }
}
