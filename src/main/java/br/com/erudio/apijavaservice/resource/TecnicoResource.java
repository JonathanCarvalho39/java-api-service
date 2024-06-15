package br.com.erudio.apijavaservice.resource;

import br.com.erudio.apijavaservice.domain.Cliente;
import br.com.erudio.apijavaservice.domain.Tecnico;
import br.com.erudio.apijavaservice.dtos.TecnicoDTO;
import br.com.erudio.apijavaservice.services.ClienteService;
import br.com.erudio.apijavaservice.services.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;


    @Operation(summary = "Busca de Tecnico por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de reqisição invalidos"),
            @ApiResponse(responseCode = "400", description = "Dados de parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable("id") Integer id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @Operation(summary = "Buscar totos os tecnicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
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

}
