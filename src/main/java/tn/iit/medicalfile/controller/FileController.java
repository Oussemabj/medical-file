package tn.iit.medicalfile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.medicalfile.config.Paths;
import tn.iit.medicalfile.dto.FileDto;
import tn.iit.medicalfile.model.File;
import tn.iit.medicalfile.service.FileService;
import tn.iit.medicalfile.service.PatientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.FILE_ROOT_URL)
public class FileController {
    private final FileService fileService;
    private final Logger logger= LoggerFactory.getLogger (FileController.class);
    private final PatientService patientService;
    public FileController(FileService fileService, PatientService patientService){
        this.fileService=fileService;
        this.patientService=patientService;

    }
    @GetMapping
    public ResponseEntity<List<FileDto>> getAllFiles() {
        this.logger.debug ("Getting all files");
        return new ResponseEntity<>(fileService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getOneFile(@PathVariable Long id) {
        this.logger.debug ("Getting File {}",id);
        return new ResponseEntity<>(fileService.getOne(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<FileDto> saveFile(@Valid @RequestBody FileDto fileDto){
        this.logger.debug ("Adding new File {}",fileDto);
        return new ResponseEntity<>(fileService.save(fileDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<FileDto> updateFile(@Valid @RequestBody FileDto fileDto){
        this.logger.debug ("Updating file {} with {}",fileDto.getId (),fileDto);
        return new ResponseEntity<>(fileService.update (fileDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable("id") Long id) {
        this.logger.debug ("Deleting File {}",id);
        try {
            fileService.delete(id);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
