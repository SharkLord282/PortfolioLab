package pl.coderslab.Institution;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public interface InstitutionRepository extends JpaRepository<Institution,Long> {


}
