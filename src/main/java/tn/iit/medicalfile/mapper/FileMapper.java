package tn.iit.medicalfile.mapper;

import org.mapstruct.Mapping;
import tn.iit.medicalfile.dto.FileDto;

import tn.iit.medicalfile.dto.MedicalTreatmentDto;
import tn.iit.medicalfile.model.File;
import tn.iit.medicalfile.model.MedicalTreatment;


@org.mapstruct.Mapper(componentModel = Mapper.SPRING)
public interface FileMapper extends Mapper<File, FileDto>{
    @Override
    @Mapping(source = "patient.id",target = "patientId")
    FileDto mapToDto(File file);

    @Override
    @Mapping(source = "patientId",target = "patient.id")
    File mapToEntity(FileDto fileDto) ;



}
