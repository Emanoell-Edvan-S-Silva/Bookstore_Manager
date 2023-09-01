package wdabookstore.bookstoremanager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/alugueis")

public class AluguelController {
    @ApiOperation(value = "Listar Aluguéis")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Alugueis retornados com sucesso!")
    })
    @GetMapping
    public String hello(){
        return "Hello deu certo";
    }
}
