package it.mdotm.service;

import it.mdotm.entity.Pet;
import it.mdotm.exception.ElementNotFoundException;
import it.mdotm.exception.MissingArgumentException;
import it.mdotm.repository.PetRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getById(Long id) {
        log.debug("Fetching pet with id: {}", id);
        return petRepository.findById(id)
                .orElseThrow(()-> new ElementNotFoundException("Pet not found with id: " + id));
    }

    public List<Pet> list() {

        return petRepository.findAll();
    }

    public Pet update(Pet pet) {
        log.debug("Updating pet with id: {}", pet.getId());
        if(pet.getId() == null) {
            throw new MissingArgumentException("Pet id must not be null for update.");
        }

        return petRepository.save(pet);
    }

    public void delete(Long id) {
        log.debug("Deleting pet with id: {}", id);
        petRepository.deleteById(id);
    }
}
