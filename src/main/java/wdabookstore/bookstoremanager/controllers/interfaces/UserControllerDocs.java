package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.inputs.user_inputs.UserInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.user_inputs.UserInputUpdate;
import wdabookstore.bookstoremanager.dto.output.user_outputs.UserResponse;

import javax.validation.Valid;
import java.util.List;

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
    ResponseEntity<UserResponse> create(@Valid @RequestBody UserInputCreate userInput);

    @ApiOperation(value = "Atualizar Usuário")
    @PutMapping
    ResponseEntity<UserResponse> update(@Valid @RequestBody UserInputUpdate userInput);

    @ApiOperation(value = "Deletar Usuário")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
