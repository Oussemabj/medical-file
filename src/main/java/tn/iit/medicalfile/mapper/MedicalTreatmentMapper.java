package tn.iit.medicalfile.mapper;

import org.mapstruct.Mapping;
import tn.iit.medicalfile.dto.MedicalTreatmentDto;

import tn.iit.medicalfile.model.MedicalTreatment;


@org.mapstruct.Mapper(componentModel = Mapper.SPRING)
public interface MedicalTreatmentMapper  extends Mapper<MedicalTreatment, MedicalTreatmentDto>{

    @Override
    @Mapping(source = "file.id",target = "fileId")
    MedicalTreatmentDto mapToDto(MedicalTreatment medicalTreatment);

    @Override
    @Mapping(source = "fileId",target = "file.id")
    MedicalTreatment mapToEntity(MedicalTreatmentDto medicalTreatmentDto) ;


}
