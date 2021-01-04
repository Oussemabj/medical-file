package tn.iit.medicalfile.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotNull;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto extends IdentifiableDto<Long>{
    private static final long serialVersionUID = 1L;
    @NotNull
    private long patientId;
    private String patientName;

    private List<MedicalTreatmentDto> treatments;
}
