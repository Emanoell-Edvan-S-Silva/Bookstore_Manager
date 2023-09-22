package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.controllers.interfaces.UserControllerDocs;
import wdabookstore.bookstoremanager.dto.inputs.user_inputs.UserInputCreate;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.inputs.user_inputs.UserInputUpdate;
import wdabookstore.bookstoremanager.dto.output.user_outputs.UserResponse;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.services.user.UserCommandService;
import wdabookstore.bookstoremanager.services.user.UserQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserCommandService userCommandService;

    @Override
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserEntity> userOutputDTOS = userQueryService.findAll();
        List<UserResponse> user = userOutputDTOS.stream()
                .map(userMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        UserResponse user = userMapper
                .mapperEntityToOutput(userQueryService.findById(id));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserInputCreate userInput){
        UserResponse createdPublisher = userCommandService.create(userInput);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserInputUpdate userInput){
        UserResponse updatedPublisher = userCommandService.update(userInput);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
