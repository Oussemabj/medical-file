package tn.iit.medicalfile.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.medicalfile.dto.PatientDto;
import tn.iit.medicalfile.exception.ResourceAlreadyFoundException;
import tn.iit.medicalfile.exception.ResourceNotFoundException;
import tn.iit.medicalfile.mapper.PatientMapper;
import tn.iit.medicalfile.model.Patient;
import tn.iit.medicalfile.repository.PatientRepository;

import java.util.List;

@Transactional
@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final  PatientMapper patientMapper;

    PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }
    public  PatientDto save(PatientDto patientDto) {
        if(patientDto == null) {
            throw new ResourceNotFoundException ("invalid input  ");
        }
        Patient savedPatient =patientRepository.save (patientMapper.mapToEntity (patientDto));
        if(savedPatient == null) {
            throw new RuntimeException ("An error has occurred while saving this patient");
        }
        return patientMapper.mapToDto(savedPatient);
    }

    public PatientDto update(PatientDto patientDto) {
        return patientMapper.mapToDto (patientRepository.save (patientMapper.mapToEntity (patientDto)));
    }
    public void delete(Long id) {
        try {
            patientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new  ResourceNotFoundException("no patient exists with id = "+id);
        } catch (Exception e){}

    }
    public PatientDto getOne(Long id) {
        return patientMapper.mapToDto (patientRepository.getOne(id));

    }
    public List<PatientDto> getAll() {

        return patientMapper.mapToDtos(patientRepository.findAll());
    }



}
