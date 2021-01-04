package tn.iit.medicalfile.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class for identify objects.
 *
 * @param <T> the primary key type.
 * @author Oussema Ben Jmeaa
 */
@MappedSuperclass
@EqualsAndHashCode
@Getter
@Setter
public abstract class Identifiable<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    protected T id;

}