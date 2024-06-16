package br.com.erudio.apijavaservice.resource;

import br.com.erudio.apijavaservice.domain.Tecnico;
import br.com.erudio.apijavaservice.dtos.TecnicoDTO;
import br.com.erudio.apijavaservice.services.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @Operation(summary = "Busca de Tecnico por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TecnicoDTO.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"nome\":\"João Silva\"}"))),
            @ApiResponse(responseCode = "422", description = "Dados de reqisição invalidos"),
            @ApiResponse(responseCode = "400", description = "Dados de parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable("id") Integer id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @Operation(summary = "Buscar todos os tecnicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TecnicoDTO.class),
                            examples = @ExampleObject(value = "[{\"id\":1,\"nome\":\"João Silva\"}, {\"id\":2,\"nome\":\"Maria Oliveira\"}]"))),
            @ApiResponse(responseCode = "422", description = "Dados de reqisição invalidos"),
            @ApiResponse(responseCode = "400", description = "Dados de parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")
    })
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> listTec = service.findAll();
        List<TecnicoDTO> listDTO = listTec.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @Operation(summary = "Adicionar Tecnico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tecnico criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TecnicoDTO.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"nome\":\"Novo Tecnico\"}"))),
            @ApiResponse(responseCode = "422", description = "Dados de reqisição invalidos"),
            @ApiResponse(responseCode = "400", description = "Dados de parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")
    })
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO obj) {
        Tecnico newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar Tecnico", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TecnicoDTO.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"nome\":\"Tecnico Atualizado\"}"))),
            @ApiResponse(responseCode = "422", description = "Dados de reqisição invalidos"),
            @ApiResponse(responseCode = "400", description = "Dados de parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @RequestBody TecnicoDTO objDto) {
        Tecnico obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
