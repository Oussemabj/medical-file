package tn.iit.medicalfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.medicalfile.model.Patient;

@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long> {
}
