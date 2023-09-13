package wdabookstore.bookstoremanager.controllers.impl;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.Mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.inputs.UserInputDTO;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
import wdabookstore.bookstoremanager.dto.output.UserOutputDTO;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.services.user.UserCommandService;
import wdabookstore.bookstoremanager.services.user.UserQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserCommandService userCommandService;

    @ApiOperation(value = "Listar Usuários")
    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> findAll(){
        List<UserEntity> userOutputDTOS = userQueryService.findAll();
        List<UserOutputDTO> user = userOutputDTOS.stream()
                .map(userMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "Listar Editora(Id)")
    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> findById(@PathVariable Long id){
        UserOutputDTO user = userMapper
                .mapperEntityToOutput(userQueryService.findById(id));
        return ResponseEntity.ok().body(user);
    }
    @ApiOperation(value = "Criar Novo Usuário")
    @PostMapping
    public ResponseEntity<UserOutputDTO> create(@Valid @RequestBody UserInputDTO userInput){
        UserOutputDTO createdPublisher = userCommandService.create(userInput);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar Usuário")
    @PutMapping
    public ResponseEntity<UserOutputDTO> update(@Valid @RequestBody UserInputDTO userInput){
        UserOutputDTO updatedPublisher = userCommandService.update(userInput);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar Usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
