package tn.iit.medicalfile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.config.Paths;

import tn.iit.medicalfile.dto.MedicalTreatmentDto;
import tn.iit.medicalfile.service.MedicalTreatmentService;

import java.util.List;

@RestController
@RequestMapping(Paths.MEDICAL_TREATMENT_ROOT_URL)
public class MedicalTreatmentController {
    private final Logger logger = LoggerFactory.getLogger (MedicalTreatmentController.class);
    private final MedicalTreatmentService medicalTreatmentService;

    public MedicalTreatmentController(MedicalTreatmentService medicalTreatmentService) {
        this.medicalTreatmentService = medicalTreatmentService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalTreatmentDto>> getAllTreatments(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String pageSort) {
        logger.debug ("Getting all files with pagination");
        return new ResponseEntity<> (medicalTreatmentService.findAll (PageRequest.of (pageNo, pageSize, Sort.by (pageSort).ascending ())), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<MedicalTreatmentDto> getOneMedicalTreatment(@PathVariable Long id) {
        return new ResponseEntity<>(medicalTreatmentService.findOne (id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MedicalTreatmentDto> saveMedicalTreatment(@RequestBody MedicalTreatmentDto medicalTreatmentDto){
        this.logger.debug ("Adding new MedicalTreatment {}", medicalTreatmentDto);
        return new ResponseEntity<>(medicalTreatmentService.save(medicalTreatmentDto), HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<MedicalTreatmentDto> updateMedicalTreatment(@RequestBody MedicalTreatmentDto medicalTreatmentDto){
        this.logger.debug ("Updating Treatments {} with {}", medicalTreatmentDto.getId (), medicalTreatmentDto);
        return new ResponseEntity<>(medicalTreatmentService.save (medicalTreatmentDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicalTreatment(@PathVariable("id") Long id) {
        this.logger.debug ("Deleting treatment {}",id);
        try {
            medicalTreatmentService.delete(id);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}


