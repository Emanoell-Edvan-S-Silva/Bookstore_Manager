package wdabookstore.bookstoremanager.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/editoras")

public class EditoraController {
    @ApiOperation(value = "Listar Editoras")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Editoras retornadas com sucesso!")
    })
    @GetMapping
    public String hello(){
        return "Hello deu certo";
    }




}
