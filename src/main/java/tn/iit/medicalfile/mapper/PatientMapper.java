package tn.iit.medicalfile.mapper;

import tn.iit.medicalfile.dto.PatientDto;
import tn.iit.medicalfile.model.Patient;

@org.mapstruct.Mapper(componentModel = Mapper.SPRING)
public interface PatientMapper extends  Mapper <Patient, PatientDto> {

  PatientDto mapToDto(Patient patient);

 Patient mapToEntity(PatientDto patientDto) ;

}
