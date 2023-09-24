package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.rent.RentFinalizeRent;
import wdabookstore.bookstoremanager.dto.rent.RentInputCreate;
import wdabookstore.bookstoremanager.dto.rent.RentExtendRent;
import wdabookstore.bookstoremanager.dto.rent.RentResponse;

import javax.validation.Valid;
import java.util.List;
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "Rent-Actions")
public interface RentControllerDocs {

    @ApiOperation(value = "Listar Aluguéis Atrasados/No Prazo")
    @GetMapping("/LateAndOnTimeRents")
    ResponseEntity<List<RentResponse>> findLateAndOnTimeRents();

    @ApiOperation(value = "Listar Aluguéis Pendentes")
    @GetMapping("/OutstandingRents")
    ResponseEntity<List<RentResponse>> findOutstandingRents();

    @ApiOperation(value = "Listar Aluguel(Id)")
    @GetMapping("/{id}")
    ResponseEntity<RentResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Novo Aluguel")
    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody RentInputCreate rentInputCreate);


    @ApiOperation(value = "Finalizar Aluguel")
    @PutMapping("/finalize")
    ResponseEntity<Void> finalizeRent(@Valid @RequestBody RentFinalizeRent rent);

    @ApiOperation(value = "Estender Aluguel")
    @PutMapping("/extend")
    ResponseEntity<Void> extendRent(@Valid @RequestBody RentExtendRent rent);

    @ApiOperation(value = "Deletar Aluguel")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
