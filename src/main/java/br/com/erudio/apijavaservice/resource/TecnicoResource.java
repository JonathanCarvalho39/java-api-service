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

    @Operation(summary = "Busca de Técnico por id", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TecnicoDTO.class), examples = @ExampleObject(value = """
            {
                "id": 1,
                "nome": "João Silva",
                "cpf": "000.000.000-00",
                "email": "joao.silva@email.com",
                "senha": "123",
                "perfis": ["ADMIN"],
                "dataCriacao": "2024-06-16"
            }"""))), @ApiResponse(responseCode = "404", description = "Técnico não encontrado", content = @Content(mediaType = "application/json", examples = @ExampleObject("""
            {
                "timestamp": 1718503514901,
                "status": 404,
                "error": "Object Not Found",
                "message": "Técnico não encontrado: 1",
                "path": "/api/v1/tecnicos/1"
            }"""))), @ApiResponse(responseCode = "400", description = "Dados de parâmetros inválidos", content = @Content(mediaType = "application/json", examples = @ExampleObject("""
            {
                "timestamp": 1718504152139,
                "status": 400,
                "error": "Parametro invalido",
                "message": "Erro, parametros invalidos",
                "path": "/api/v1/tecnicos/a",
                "fieldMessages": []
            }"""))), @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable("id") Integer id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @Operation(summary = "Buscar todos os Técnico", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TecnicoDTO.class), examples = @ExampleObject(value = """
            [
                {
                    "id": 1,
                    "nome": "João Silva",
                    "cpf": "000.000.000-00",
                    "email": "joao.silva@email.com",
                    "senha": "123",
                    "perfis": ["ADMIN"],
                    "dataCriacao": "2024-06-16"
                },
                {
                    "id": 2,
                    "nome": "Maria Oliveira",
                    "cpf": "111.111.111-11",
                    "email": "maria.oliveira@email.com",
                    "senha": "456",
                    "perfis": ["USER"],
                    "dataCriacao": "2024-06-16"
                }
            ]"""))), @ApiResponse(responseCode = "404", description = "Técnico não encontrado", content = @Content(mediaType = "application/json", examples = @ExampleObject("""
            {
                "timestamp": 1718503514901,
                "status": 404,
                "error": "Object Not Found",
                "message": "Nenhum técnico encontrado",
                "path": "/api/v1/tecnicos"
            }"""))), @ApiResponse(responseCode = "400", description = "Dados de parâmetros inválidos", content = @Content(mediaType = "application/json", examples = @ExampleObject("""
            {
                "timestamp": 1718504152139,
                "status": 400,
                "error": "Parametro invalido",
                "message": "Erro, parametros invalidos",
                "path": "/api/v1/tecnicos",
                "fieldMessages": []
            }"""))), @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")})
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> listTec = service.findAll();
        List<TecnicoDTO> listDTO = listTec.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @Operation(summary = "Adicionar Técnico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tecnico criado com sucesso", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
            {
                "id": 1,
                "nome": "Novo Tecnico",
                "cpf": "000.000.000-00",
                "email": "novo.tecnico@email.com",
                "perfis": ["USER"],
                "dataCriacao": "2024-06-16"
            }
            """))), @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"), @ApiResponse(responseCode = "400", description = "Dados de parâmetros inválidos"), @ApiResponse(responseCode = "500", description = "Erro ao criar os dados")})
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Atualização de Técnico", content = @Content(schema = @Schema(implementation = TecnicoDTO.class), examples = @ExampleObject(value = """
            {
                "nome": "Adicionar Atualizado",
                "cpf": "000.000.000-00",
                "email": "tecnico.atualizado@email.com",
                "perfis": [0, 1, 2]
            }"""))) @Valid @RequestBody TecnicoDTO obj) {
        Tecnico newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new TecnicoDTO(newObj));
    }

    @Operation(summary = "Atualizar Técnico", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TecnicoDTO.class), examples = @ExampleObject(value = """
            {
                "id": 1,
                "nome": "Tecnico Atualizado",
                "cpf": "000.000.000-00",
                "email": "tecnico.atualizado@email.com",
                "perfis": [0],
                "dataCriacao": "2024-06-16"
            }"""))), @ApiResponse(responseCode = "404", description = "Tecnico não encontrado", content = @Content(mediaType = "application/json", examples = @ExampleObject("""
            {
                "timestamp": 1718503514901,
                "status": 404,
                "error": "Object Not Found",
                "message": "Técnico não encontrado: 10",
                "path": "/api/v1/tecnicos/10"
            }"""))), @ApiResponse(responseCode = "400", description = "Dados de parâmetros inválidos", content = @Content(mediaType = "application/json", examples = @ExampleObject("""
            {
                "timestamp": 1718504152139,
                "status": 400,
                "error": "Parametro invalido",
                "message": "Erro, parametros invalidos",
                "path": "/api/v1/tecnicos/a",
                "fieldMessages": []
            }""")))})
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Atualização de Técnico", content = @Content(schema = @Schema(implementation = TecnicoDTO.class), examples = @ExampleObject(value = """
            {
                "nome": "Técnico Atualizado",
                "cpf": "000.000.000-00",
                "email": "tecnico.atualizado@email.com",
                "perfis": [0, 1, 2]
            }"""))) @Valid @RequestBody TecnicoDTO objDto) {
        Tecnico obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @Operation(summary = "Deletar Técnico", method = "DELETE")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Técnico deletado com sucesso", content = @Content(mediaType = "application/json", examples = @ExampleObject())), @ApiResponse(responseCode = "500", description = "Erro ao deletar os dados")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
