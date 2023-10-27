package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
import wdabookstore.bookstoremanager.dto.rental.RentalResponse;

import javax.validation.Valid;
import java.util.List;
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "Rental-Actions")
public interface RentalControllerDocs {

    @ApiOperation(value = "Listar Todos Aluguéis")
    @GetMapping
    ResponseEntity<List<RentalResponse>> findAllRentals();

    @ApiOperation(value = "Listar Aluguel(Id)")
    @GetMapping("/{id}")
    ResponseEntity<RentalResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Novo Aluguel")

    @PostMapping
    ResponseEntity<Void> create(@ApiParam(name = "Rental", value = "Representation of a new Rental", required = true) @Valid @RequestBody RentalInputCreate rentalInputCreate);

    @ApiOperation(value = "Finalizar Aluguel")
    @PutMapping("/Finalize/{id}")
    ResponseEntity<Void> finalizeRent(@Valid @RequestBody Long id);


    @ApiOperation(value = "Deletar Aluguel")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
