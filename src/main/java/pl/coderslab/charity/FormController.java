package pl.coderslab.charity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.Institution.InstitutionRepository;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;


@Controller
public class FormController {
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public FormController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/form")
    public String form(Model model,  Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            model.addAttribute("username", username);
        }
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions" ,institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("donation") Donation donation) {
        donationRepository.save(donation);

        return "formConfirmation";
    }


}
