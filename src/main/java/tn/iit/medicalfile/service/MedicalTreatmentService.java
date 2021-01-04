package tn.iit.medicalfile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.medicalfile.dto.MedicalTreatmentDto;
import tn.iit.medicalfile.dto.MedicineDto;
import tn.iit.medicalfile.exception.ResourceNotFoundException;
import tn.iit.medicalfile.mapper.MedicalTreatmentMapper;
import tn.iit.medicalfile.repository.MedicalTreatmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MedicalTreatmentService {
    public Logger logger = LoggerFactory.getLogger (MedicalTreatmentService.class);
    private final MedicalTreatmentRepository medicalTreatmentRepository;
    private final StoreManagementClientService storeManagementClientService;
    private final MedicalTreatmentMapper medicalTreatmentMapper ;
    public MedicalTreatmentService(MedicalTreatmentMapper medicalTreatmentMapper,  MedicalTreatmentRepository medicalTreatmentRepository, StoreManagementClientService storeManagementClientService) {
        this.medicalTreatmentRepository = medicalTreatmentRepository;
        this.medicalTreatmentMapper=medicalTreatmentMapper;
        this.storeManagementClientService = storeManagementClientService;
    }

    public MedicalTreatmentDto save(MedicalTreatmentDto medicalTreatmentDto) {
       return medicalTreatmentMapper.mapToDto (medicalTreatmentRepository.save
               (medicalTreatmentMapper.mapToEntity (medicalTreatmentDto)));

    }
    public void delete(Long id) {
        try {
            medicalTreatmentRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException ("no medicalTreatment exists with id = "+id);
        } catch (Exception e){}

    }
    public MedicalTreatmentDto findOne (Long id ){
        MedicalTreatmentDto medicalTreatmentDto= medicalTreatmentMapper.mapToDto (medicalTreatmentRepository.getOne (id));
        MedicineDto medicineDto= storeManagementClientService.getMedicineById (medicalTreatmentDto.getMedicineId ());
        medicalTreatmentDto.setMedicineName (medicineDto.getName ());
        medicalTreatmentDto.setMedicinePrice (medicineDto.getPrice ());
        return medicalTreatmentDto;
    }
public List<MedicalTreatmentDto>findAll(Pageable pageable){
List<MedicalTreatmentDto>  medicalTreatmentDtos= medicalTreatmentMapper.mapToDtos (medicalTreatmentRepository.findAll (pageable).getContent ());
        medicalTreatmentDtos.forEach (medicalTreatmentDto -> {
            MedicineDto medicineDto=storeManagementClientService.getMedicineById (medicalTreatmentDto.getMedicineId ());
            medicalTreatmentDto.setMedicineName ((medicineDto.getName ()));
            medicalTreatmentDto.setMedicinePrice (medicineDto.getPrice ());
                });
        return medicalTreatmentDtos;
}
public List<MedicalTreatmentDto > findAllByFileId (Long fileId){
    this.logger.debug ("Getting All medicalTreatment By File id {}",fileId);
    List<MedicalTreatmentDto> medicalTreatmentDtos = medicalTreatmentMapper.mapToDtos (medicalTreatmentRepository.findMedicalTreatmentsByFileId (fileId));
    List<Long> ids = medicalTreatmentDtos.stream ().map (MedicalTreatmentDto::getMedicineId).collect(Collectors.toList());
    List<MedicineDto> medicines=storeManagementClientService.getMedicinesByIds (ids);
    medicalTreatmentDtos.forEach (medicalTreatmentDto -> {
        MedicineDto medicineDto = medicines.stream ()
                .filter (fliteredMedicineDto -> fliteredMedicineDto.getId ().toString ().equals(String.valueOf(medicalTreatmentDto.getMedicineId ())))
                .findFirst ()
                .orElseThrow (() -> new ResourceNotFoundException
                        ("NOT FOUND","Missing medicines with id "+medicalTreatmentDto.getMedicineId ()));
        medicalTreatmentDto.setMedicineName (medicineDto.getName ());
        medicalTreatmentDto.setMedicinePrice (medicineDto.getPrice ());
    });
    return medicalTreatmentDtos;
}

}

