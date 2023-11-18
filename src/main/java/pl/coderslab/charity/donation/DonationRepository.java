package pl.coderslab.charity.donation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {

    @Query(value = "SELECT SUM(quantity) FROM donation", nativeQuery = true)
    Integer sumAllDonatedBags();
}
