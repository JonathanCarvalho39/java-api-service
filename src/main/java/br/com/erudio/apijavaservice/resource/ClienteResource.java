package br.com.erudio.apijavaservice.resource;

import br.com.erudio.apijavaservice.domain.Cliente;
import br.com.erudio.apijavaservice.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente", produces = {"application/json"})
@Tag(name = "ApiService HelpDasck")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Busca de Cliente por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de reqisição invalidos"),
            @ApiResponse(responseCode = "400", description = "Dados de parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@RequestParam("id") Integer id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
