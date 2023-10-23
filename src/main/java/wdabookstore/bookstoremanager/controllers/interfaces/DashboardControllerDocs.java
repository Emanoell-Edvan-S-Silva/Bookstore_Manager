package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import wdabookstore.bookstoremanager.dto.book.BookResponse;
import wdabookstore.bookstoremanager.dto.publisher.PublisherResponse;
import wdabookstore.bookstoremanager.dto.rental.RentalResponse;
import wdabookstore.bookstoremanager.dto.user.UserResponse;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "DashBoard-Actions")
public interface DashboardControllerDocs {

    @ApiOperation(value = "Listar Todos os Livros ")
    @GetMapping("/Books")
    ResponseEntity<List<BookResponse>> findAllBooks();

    @ApiOperation(value = "Listar Todas as Editoras ")
    @GetMapping("/Publishers")
    ResponseEntity<List<PublisherResponse>> findAllPublisher();

    @ApiOperation(value = "Listar Todos os Aluguéis")
    @GetMapping("/Rentals")
    ResponseEntity<List<RentalResponse>> findAllRentals();

    @ApiOperation(value = "Listar Todos os usuários")
    @GetMapping("/Users")
    ResponseEntity<List<UserResponse>> findAllUsers();

    @ApiOperation(value = "Top 4 Livros")
    @GetMapping("/Top4books")
    ResponseEntity<List<BookResponse>> findTop4MostLeasedBooks();

    @ApiOperation(value = "Usuário que mais Alugou")
    @GetMapping("/UserWithMostRentals")
    ResponseEntity<UserResponse> findUserWithMostRentals();

    @ApiOperation(value = "Todos Alugueis Entregues no Prazo")
    @GetMapping("/OnTimeRentals")
    ResponseEntity<List<RentalResponse>> findOnTimeRentals();

    @ApiOperation(value = "Todos Alugueis Entregues com atraso")
    @GetMapping("/LateRentals")
    ResponseEntity<List<RentalResponse>> findLateRentals();

    @ApiOperation(value = "Todos Aluguéis Pendentes")
    @GetMapping("/OutstandingRentals")
    ResponseEntity<List<RentalResponse>> findOutstandingRentals();
}
