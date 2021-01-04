package tn.iit.medicalfile.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto extends  IdentifiableDto <Long>{
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private long cin;
}
