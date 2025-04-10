package com.example.mvd_dev.controller;

import com.example.mvd_dev.modeldto.CitizenDto;
import com.example.mvd_dev.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizens")
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping
    public ResponseEntity<CitizenDto> createCitizen(@RequestBody CitizenDto citizenDto) {
        CitizenDto savedCitizen = citizenService.save(citizenDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCitizen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenDto> getCitizenById(@PathVariable Long id) {
        CitizenDto citizen = citizenService.findById(id);
        return ResponseEntity.ok(citizen);
    }

    @GetMapping
    public List<CitizenDto> getAllCitizens() {
        return citizenService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitizenDto> updateCitizen(@PathVariable Long id, @RequestBody CitizenDto citizenDto) {
        CitizenDto updatedCitizen = citizenService.update(id, citizenDto);
        return ResponseEntity.ok(updatedCitizen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable Long id) {
        citizenService.delete(id);
        return ResponseEntity.noContent().build();
    }


}