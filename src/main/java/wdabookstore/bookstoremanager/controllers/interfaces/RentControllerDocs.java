package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputUpdate;
import wdabookstore.bookstoremanager.dto.output.rent_outputs.RentResponse;

import javax.validation.Valid;
import java.util.List;
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "Rent-Actions")
public interface RentControllerDocs {
    @ApiOperation(value = "Listar Aluguéis")
    @GetMapping
    ResponseEntity<List<RentResponse>> findAll();

    @ApiOperation(value = "Listar Aluguel(Id)")
    @GetMapping("/{id}")
    ResponseEntity<RentResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Novo Aluguel")
    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody RentInputCreate rentInputCreate);

    @ApiOperation(value = "Atualizar Aluguel")
    @PutMapping
    ResponseEntity<Void> update(@Valid @RequestBody RentInputUpdate rentInputUpdate);

    @ApiOperation(value = "Deletar Aluguel")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
