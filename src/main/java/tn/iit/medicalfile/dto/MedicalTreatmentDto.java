package tn.iit.medicalfile.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalTreatmentDto  extends IdentifiableDto<Long>{
    private static final long serialVersionUID = 1L;
    @NotNull
    private long fileId;
    @NotNull
    private long medicineId;
    private String medicineName;
    private String medicinePrice;
    private Long medicineCategoryId;
}
