package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.Institution.InstitutionRepository;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.users.User;
import pl.coderslab.charity.users.UserService;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String homeAction(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {

            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {

                UserDetails userDetails = (UserDetails) principal;


                String username = userDetails.getUsername();
                model.addAttribute("loggedUser", true);
                model.addAttribute("username", username);
            }
        } else {

            model.addAttribute("loggedUser", false);
        }

        model.addAttribute("fundations", institutionRepository.findAll());
        model.addAttribute("numberOfDonatedBags",donationRepository.sumAllDonatedBags());
        model.addAttribute("sumOfGifts", donationRepository.sumOfGifts());

        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }


        return "redirect:/form";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }


        if (userService.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "Użytkownik o podanej nazwie już istnieje");
            return "register";
        }



        user.setPassword(passwordEncoder.encode(user.getPassword()));


        userService.saveUser(user);

        return "redirect:/login?registrationSuccess";
    }





}
