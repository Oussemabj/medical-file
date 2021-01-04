package tn.iit.medicalfile.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.medicalfile.dto.FileDto;
import tn.iit.medicalfile.exception.ResourceAlreadyFoundException;
import tn.iit.medicalfile.exception.ResourceNotFoundException;
import tn.iit.medicalfile.mapper.FileMapper;
import tn.iit.medicalfile.mapper.PatientMapper;
import tn.iit.medicalfile.model.File;
import tn.iit.medicalfile.repository.FileRepository;

import java.util.List;

@Transactional
@Service
public class FileService {
    private final FileMapper fileMapper;
    private final PatientMapper patientMapper;
    private final FileRepository fileRepository;
    private final MedicalTreatmentService medicalTreatmentService;
    private final PatientService patientService;


    public FileService(FileRepository fileRepository, MedicalTreatmentService medicalTreatmentService, FileMapper fileMapper, PatientMapper patientMapper, PatientService patientService) {
        this.fileRepository = fileRepository;
        this.medicalTreatmentService = medicalTreatmentService;
        this.fileMapper = fileMapper;
        this.patientMapper = patientMapper;
        this.patientService = patientService;
    }

    public FileDto getOne(Long id) {
      FileDto fileDto = fileMapper.mapToDto (fileRepository.getOne (id));
      fileDto.setTreatments (medicalTreatmentService.findAllByFileId (id));
     return fileDto;
       // return fileMapper.mapToDto (fileRepository.getOne (id));

    }

    public List<FileDto> getAll() {
return fileMapper.mapToDtos (fileRepository.findAll ());
    }

    public void delete(Long id) {
        try {
            fileRepository.deleteById (id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException ("no file exists with id = " + id);
        } catch (Exception e) {
        }

    }

    public FileDto save(FileDto fileDto) {
//        File savedFile =fileRepository.save (fileMapper.mapToEntity (fileDto));
//        return fileMapper.mapToDto (savedFile);
        if(fileDto == null) {
            throw new RuntimeException("this file is not exist ");
        }
        if(fileRepository.existsById (fileDto.getPatientId ())) {
            throw new ResourceAlreadyFoundException ("The patient already has a file.");
        }
        File savedFile =fileRepository.save (fileMapper.mapToEntity (fileDto));
        if(savedFile == null) {
            throw new RuntimeException ("An error has occurred in save this file");
        }
        return fileMapper.mapToDto(savedFile);
    }

    public FileDto update(FileDto fileDto) {
        return fileMapper.mapToDto (fileRepository.save (fileMapper.mapToEntity (fileDto)));
    }

}
