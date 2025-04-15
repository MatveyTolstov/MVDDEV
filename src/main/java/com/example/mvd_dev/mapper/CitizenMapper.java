package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.Citizen;
import com.example.mvd_dev.modeldto.CitizenDto;
import org.springframework.stereotype.Component;

@Component
public class CitizenMapper {

    public Citizen toEntity(CitizenDto citizenDto) {
        if (citizenDto == null) {
            return null;
        }
        Citizen citizen = new Citizen();
        citizen.setIdCitizen(citizenDto.getIdCitizen());
        citizen.setName(citizenDto.getName());
        citizen.setSurname(citizenDto.getSurname());
        citizenDto.setUserId(citizen.getUser ().getIdUser ());
        return citizen;
    }

    public CitizenDto toDto(Citizen citizen) {
        if (citizen == null) {
            return null;
        }
        CitizenDto citizenDto = new CitizenDto();
        citizenDto.setIdCitizen(citizen.getIdCitizen());
        citizenDto.setName(citizen.getName());
        citizenDto.setSurname(citizen.getSurname());
        citizenDto.setUserId(citizen.getUser ().getIdUser ());
        return citizenDto;
    }
}