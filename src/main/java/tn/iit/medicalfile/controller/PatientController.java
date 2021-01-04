package tn.iit.medicalfile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.config.Paths;
import tn.iit.medicalfile.dto.PatientDto;
import tn.iit.medicalfile.service.PatientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.PATIENT_ROOT_URL)
public class PatientController {
    private final PatientService patientService;
    private final Logger logger= LoggerFactory.getLogger (PatientController.class);
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        this.logger.debug ("Getting all Patients");

        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getOnePatient(@PathVariable Long id) {
        this.logger.debug ("Getting patient {}",id);
        return new ResponseEntity<>(patientService.getOne(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<PatientDto> savePatient(@RequestBody @Valid PatientDto patientDto){
        this.logger.debug ("Adding new Patient {}", patientDto);
    return new ResponseEntity<>(patientService.save(patientDto), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(@Valid @RequestBody PatientDto patientDto){
        this.logger.debug ("Updating Category {} with {}",patientDto.getId (),patientDto.getName ());
        return new ResponseEntity<>(patientService.update (patientDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        this.logger.debug ("Deleting patient {}",id);
        try {
            patientService.delete(id);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
