package tn.iit.medicalfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.medicalfile.model.MedicalTreatment;

import java.util.List;

@Repository
public interface MedicalTreatmentRepository extends JpaRepository<MedicalTreatment,Long> {


    List<MedicalTreatment>findMedicalTreatmentsByFileId(long fileId);

    void deleteMedicalTreatmentsByFileId(long fileId);
}
