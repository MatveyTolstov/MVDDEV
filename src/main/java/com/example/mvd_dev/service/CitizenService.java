package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.CitizenMapper;
import com.example.mvd_dev.model.Citizen;
import com.example.mvd_dev.modeldto.CitizenDto;
import com.example.mvd_dev.repository.CitizenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mvd_dev.service.ErrorMessages.CITIZEN_NOT_FOUND;

@Service
@AllArgsConstructor
public class CitizenService {
    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    public CitizenDto save(CitizenDto citizenDto) {
        Citizen citizen = citizenMapper.toEntity(citizenDto);
        citizen = citizenRepository.save(citizen);
        return citizenMapper.toDto(citizen);
    }

    public CitizenDto findById(Long id) {
        return citizenRepository.findById(id)
                .map(citizenMapper::toDto)
                .orElseThrow(() -> new RuntimeException(CITIZEN_NOT_FOUND));
    }

    public List<CitizenDto> findAll() {
        return citizenRepository.findAll().stream()
                .map(citizenMapper::toDto)
                .toList();
    }

    public CitizenDto update(Long id, CitizenDto citizenDto) {
        Citizen existingCitizen = citizenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CITIZEN_NOT_FOUND));

        existingCitizen.setName(citizenDto.getName());
        existingCitizen.setSurname(citizenDto.getSurname());


        return citizenMapper.toDto(citizenRepository.save(existingCitizen));
    }

    public void delete(Long id) {
        Citizen existingCitizen = citizenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CITIZEN_NOT_FOUND));
        citizenRepository.delete(existingCitizen);
    }
}