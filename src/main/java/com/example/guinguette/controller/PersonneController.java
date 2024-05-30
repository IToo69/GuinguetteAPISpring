package com.example.guinguette.controller;
import com.example.guinguette.dto.PersonneDto;
import com.example.guinguette.entities.Personne;
import com.example.guinguette.services.PersonneService;
import com.example.guinguette.services.PichetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guinguette/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @Autowired
    private PichetService pichetService;

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneService.getAllPersonnes();
    }

    @GetMapping("/{id}")
    public Personne getPersonneById(@PathVariable Long id) {
        return personneService.getPersonneById(id);
    }

    @PostMapping
    public Personne createPersonne(@RequestBody PersonneDto personneDto) {
        Personne newPersonne = new Personne();
        newPersonne.setNom(personneDto.getNom());
        newPersonne.setPrenom(personneDto.getPrenom());

        return personneService.createPersonne(newPersonne);

    }

    @PutMapping("/{id}")
    public Personne updatePersonne(@PathVariable Long id, @RequestBody PersonneDto personneDto) {
        Personne existingPersonne = personneService.getPersonneById(id);
        existingPersonne.setNom(personneDto.getNom());
        existingPersonne.setPrenom(personneDto.getPrenom());

        return personneService.updatePersonne(id, existingPersonne);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable Long id) {
        personneService.deletePersonne(id);
    }

    @GetMapping("/totalcount")
    public ResponseEntity<Long> getNombrePersonnes() {
        return ResponseEntity.ok(personneService.getTotalNumberOfPersonnes());
    }

    @GetMapping("/{id}/totalpichets")
    public ResponseEntity<Long> countPichetByPersonneId(@PathVariable Long id) {
        return ResponseEntity.ok(personneService.countPichetsByPersonneId(id));
    }

    @GetMapping("/{id}/totalamount")
    public ResponseEntity<Long> getAmountByPersonneId(@PathVariable Long id) {
        return ResponseEntity.ok(personneService.getAmountByPersonneId(id));
    }

    @GetMapping("/avgamount")
    public ResponseEntity<Long> getAvgOfMoneyForPersonne() {
        double amount = pichetService.getTotalAmount();
        Long personnes = personneService.getTotalNumberOfPersonnes();
        Long longAmount = (long) amount;
        Long res = personnes/longAmount;
        return ResponseEntity.ok(res);
    }
    
}
