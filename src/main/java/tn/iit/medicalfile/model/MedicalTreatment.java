package tn.iit.medicalfile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalTreatment extends Identifiable<Long>{
    private static final long serialVersionUID = 1L;
    @NotNull
    @ManyToOne
    private File file;
    @NotNull
    private long medicineId;
}
