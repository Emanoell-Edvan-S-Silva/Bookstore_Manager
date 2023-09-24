package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;
import wdabookstore.bookstoremanager.dto.user.UserResponse;

import javax.validation.Valid;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "User-Actions")
public interface UserControllerDocs {
    @ApiOperation(value = "Listar Usuários")
    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @ApiOperation(value = "Listar Usuário(Id)")
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Novo Usuário")
    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody UserInputCreate user);

    @ApiOperation(value = "Atualizar Usuário")
    @PutMapping
    ResponseEntity<Void> update(@Valid @RequestBody UserInputUpdate user);

    @ApiOperation(value = "Deletar Usuário")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
