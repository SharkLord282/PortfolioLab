package pl.coderslab.charity.donation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



public interface DonationRepository extends JpaRepository<Donation,Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Integer sumAllDonatedBags();

    @Query("SELECT COUNT(d) FROM Donation d WHERE d.quantity IS NOT NULL")
    Integer sumOfGifts();
}
