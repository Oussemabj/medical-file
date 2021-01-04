package tn.iit.medicalfile.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class IdentifiableDto<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    public T id;

}