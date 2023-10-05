package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
import wdabookstore.bookstoremanager.dto.rental.RentalExtendRent;
import wdabookstore.bookstoremanager.dto.rental.RentalResponse;

import javax.validation.Valid;
import java.util.List;
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "Rental-Actions")
public interface RentalControllerDocs {

    @ApiOperation(value = "Listar Aluguéis Pendentes")
    @GetMapping("/OutstandingRentals")
    ResponseEntity<List<RentalResponse>> getOutstandingRentals();

    @ApiOperation(value = "Listar Aluguéis Entregues")
    @GetMapping("/HandedOutRentals")
    ResponseEntity<List<RentalResponse>> getHandedOut();

    @ApiOperation(value = "Listar Aluguel(Id)")
    @GetMapping("/{id}")
    ResponseEntity<RentalResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Novo Aluguel")
    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody RentalInputCreate rentalInputCreate);

    @ApiOperation(value = "Finalizar Aluguel")
    @PutMapping("/Finalize/{id}")
    ResponseEntity<Void> finalizeRent(@Valid @RequestBody Long id);

    @ApiOperation(value = "Estender Aluguel")
    @PutMapping("/Extend")
    ResponseEntity<Void> extendRent(@Valid @RequestBody RentalExtendRent rent);

    @ApiOperation(value = "Deletar Aluguel")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
