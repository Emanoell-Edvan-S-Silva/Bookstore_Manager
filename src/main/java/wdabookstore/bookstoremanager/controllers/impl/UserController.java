package wdabookstore.bookstoremanager.controllers.impl;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.controllers.interfaces.UserControllerDocs;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;
import wdabookstore.bookstoremanager.dto.user.UserResponse;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.services.interfaces.user.UserCommandService;
import wdabookstore.bookstoremanager.services.interfaces.user.UserQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/Users")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class UserController implements UserControllerDocs {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserCommandService userCommandService;

    @Override
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserEntity> userEntities = userQueryService.findAllNotDeleted();
        List<UserResponse> users = userEntities.stream()
                .map(userMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(users);
    }

    @Override
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        UserResponse user = userMapper
                .mapperEntityToOutput(userQueryService.findById(id));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<Void> create(@ApiParam(name = "User", value = "Representation of a new User", required = true) @Valid @RequestBody UserInputCreate user){
        userCommandService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@ApiParam(name = "User", value = "Representation of User modification", required = true)@Valid @RequestBody UserInputUpdate user){
        userCommandService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userCommandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
