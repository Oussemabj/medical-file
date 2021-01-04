package tn.iit.medicalfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.medicalfile.model.File;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

}
