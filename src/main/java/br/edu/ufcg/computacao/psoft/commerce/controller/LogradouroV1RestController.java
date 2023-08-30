package br.edu.ufcg.computacao.psoft.commerce.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import br.edu.ufcg.computacao.psoft.commerce.service.LogradouroXXXXService;

@RestController
@RequestMapping("/logradouros")
public class LogradouroV1RestController {

    @Autowired
    private LogradouroXXXXService logradouroService;

    @GetMapping
    public ResponseEntity<List<Logradouro>> getAllLogradouros() {
        List<Logradouro> logradouros = logradouroService.getAllLogradouros();
        return new ResponseEntity<>(logradouros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logradouro> getLogradouroById(@PathVariable Long id) {
        Logradouro logradouro = logradouroService.getLogradouroById(id);
        return new ResponseEntity<>(logradouro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Logradouro> createLogradouro(@RequestBody Logradouro logradouro) {
        Logradouro newLogradouro = logradouroService.createLogradouro(logradouro);
        return new ResponseEntity<>(newLogradouro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Logradouro> updateLogradouro(@PathVariable Long id, @RequestBody Logradouro logradouro) {
        Logradouro updatedLogradouro = logradouroService.updateLogradouro(id, logradouro);
        return new ResponseEntity<>(updatedLogradouro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogradouro(@PathVariable Long id) {
        logradouroService.deleteLogradouro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Logradouro> patchLogradouro(@PathVariable Long id,
            @RequestBody List<PatchOperation> patchOperations) {
        Logradouro patchedLogradouro = logradouroService.patchLogradouro(id, patchOperations);
        return new ResponseEntity<>(patchedLogradouro, HttpStatus.OK);
    }
}
