package br.com.erudio.apijavaservice.resource;

import br.com.erudio.apijavaservice.domain.Chamado;
import br.com.erudio.apijavaservice.dtos.ChamadoDTO;
import br.com.erudio.apijavaservice.services.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @Operation(summary = "Buscar Chamado por ID", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Consulta Realizada com sucesso!",
            content = @Content(mediaType = "aplication/json",
                    schema = @Schema(implementation = ChamadoDTO.class),
                    examples = @ExampleObject(value = """
                                  {
                                    "id": 0,
                                    "dataAbertura": "2024-06-16",
                                    "dataFechamento": "2024-06-16",
                                    "prioridade": 0,
                                    "status": 0,
                                    "titulo": "string",
                                    "observacoes": "string",
                                    "tecnico": 0,
                                    "cliente": 0,
                                    "nomeCliente": "string",
                                    "nomeTecnico": "string"
                                  }
                            """))), @ApiResponse(responseCode = "404",
            description = "O Chamado não existe",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ChamadoDTO.class),
                    examples = @ExampleObject(value = """
                                  {
                                   	"timestamp": 1718518698611,
                                    "status": 404,
                                    "error": "Object Not Found",
                                    "message": "Chamado não encontrado: 111",
                                    "path": "/api/v1/chamados/111"
                                  }
                            """)

            )), @ApiResponse(responseCode = "400",
            description = "Parametros invalidos",
            content = @Content(mediaType = "Application/json",
                    schema = @Schema(implementation = ChamadoDTO.class),
                    examples = @ExampleObject(value = """
                                    {
                                    	"timestamp": 1718518990418,
                                    	"status": 400,
                                    	"error": "Parametro invalido",
                                    	"message": "Erro, parametros invalidos",
                                    	"path": "/api/v1/chamados/A",
                                    	"fieldMessages": []
                                    }
                            """))), @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }


    @Operation(summary = "Consultar Chamados", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Consulta Realizada Com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ChamadoDTO.class),
                    examples = @ExampleObject(value = """
                                [
                                    {
                                        "id": 1,
                                        "dataAbertura": "2024-06-16",
                                        "dataFechamento": "2024-06-23",
                                        "prioridade": 1,
                                        "status": 1,
                                        "titulo": "Chamado-01",
                                        "observacoes": "Primeiro chamado",
                                        "tecnico": 1,
                                        "cliente": 5,
                                        "nomeCliente": "Pedro",
                                        "nomeTecnico": "Jonathan"
                                    },
                                    {
                                        "id": 2,
                                        "dataAbertura": "2024-06-16",
                                        "dataFechamento": "2024-07-12",
                                        "prioridade": 0,
                                        "status": 2,
                                        "titulo": "Chamado-02",
                                        "observacoes": "Segundo chamado",
                                        "tecnico": 2,
                                        "cliente": 5,
                                        "nomeCliente": "Pedro",
                                        "nomeTecnico": "Andre"
                                    }
                                ]
                            """))), @ApiResponse(responseCode = "404",
            description = "Parabetros invalidos",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ChamadoDTO.class),
                    examples = @ExampleObject(value = """
                            {
                            "timestamp": 1718504152139,
                            "status": 404,
                            "error": "Object Not Found",
                            "message": "Não existe Chamados",
                            "path": "/api/v1/chamados",
                            "fieldMessages": []
                            }
                            """))), @ApiResponse(responseCode = "400",
            description = "Parametro Invalido",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ChamadoDTO.class),
                    examples = @ExampleObject(value = """
                            {
                            "timestamp": 1718504152139,
                            "status": 400,
                            "error": "Parametro invalido",
                            "message": "Erro, parametros invalidos",
                            "path": "/api/v1/chamados",
                            "fieldMessages": []
                            }
                                        """))), @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")})
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findByAll() {
        List<Chamado> listChamado = service.findByAll();
        List<ChamadoDTO> listDTO = listChamado.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}
