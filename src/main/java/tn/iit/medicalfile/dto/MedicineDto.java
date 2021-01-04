package tn.iit.medicalfile.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineDto  extends  IdentifiableDto {
    private static final long serialVersionUID = 1L;
    private String name;
    private String price;
    private Long categoryId;
}
