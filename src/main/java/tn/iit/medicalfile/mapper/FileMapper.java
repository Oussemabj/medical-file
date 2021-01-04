package tn.iit.medicalfile.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tn.iit.medicalfile.dto.FileDto;


import tn.iit.medicalfile.model.File;



@org.mapstruct.Mapper(componentModel = Mapper.SPRING)
public interface FileMapper extends Mapper<File, FileDto>{
    @Override
            @Mapping(source = "patient.id",target = "patientId")
            @Mapping(source = "patient.name",target = "patientName")
    FileDto mapToDto(File file);

    @Override
            @Mapping(source = "patientId",target = "patient.id")
            @Mapping(source = "patientName",target = "patient.name")
    File mapToEntity(FileDto fileDto) ;





}
