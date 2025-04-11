package com.example.loginauthapi.controllers;

import com.example.loginauthapi.domain.user.cadastro;
import com.example.loginauthapi.repositories.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/controller")


public class CadastroController {


    @Autowired
    CadastroRepository repositorio;

    @PostMapping("/AdicionaCadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public cadastro AdicionaCadastro(@RequestBody cadastro Cadastro) {
        return repositorio.save(Cadastro);
    }

    @GetMapping
    public List<cadastro> obterTodos() {
        return repositorio.findAll();
    }

    @GetMapping("{id}")
    public cadastro AcharPorId(@PathVariable Integer id){
        return repositorio.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/Atualiza/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar (@PathVariable Integer id, @RequestBody cadastro Cadastro){
        repositorio .findById(id)
                .map(cadastro -> {
                    cadastro.setNome(Cadastro.getNome());
                    cadastro.setDescricao(Cadastro.getDescricao());
                    return repositorio.save(cadastro);
                } )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/Deleta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaCadastro(@PathVariable Integer id){
        //repo.deleteById(id); DESTA FORMA TAMBÉM DA CERTO EM APAGAR O CADASTRO
        repositorio.findById(id)
                .map(cadastro -> {
                    repositorio.delete(cadastro);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
