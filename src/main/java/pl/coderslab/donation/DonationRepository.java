package pl.coderslab.donation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public interface DonationRepository extends JpaRepository<Donation,Long> {
}
