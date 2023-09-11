package wdabookstore.bookstoremanager.controllers.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {
    @ApiOperation(value = "Listar Usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuarios retornados com sucesso!")
    })
    @GetMapping
    public String hello(){
        return "Hello deu certo";
    }
}
