package it.mdotm.controller;

import it.mdotm.entity.Pet;
import it.mdotm.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> create(@Valid @RequestBody Pet pet) {
        return new ResponseEntity<>(petService.create(pet), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> getById(@PathVariable Long id) {
        return new ResponseEntity<>(petService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pet>> list() {
        return new ResponseEntity<>(petService.list(), HttpStatus.OK);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> update(@Valid @RequestBody Pet pet) {
        return new ResponseEntity<>(petService.update(pet), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
