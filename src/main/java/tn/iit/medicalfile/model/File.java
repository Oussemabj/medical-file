package tn.iit.medicalfile.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class File extends Identifiable<Long> {
    private static final long serialVersionUID = 1L;

    @NotNull
    @OneToOne
    private Patient patient;
}
