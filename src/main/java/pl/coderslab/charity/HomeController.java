package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.Institution.InstitutionRepository;

import javax.swing.*;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;

    @Autowired
    public HomeController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }


    @GetMapping("/")
    public String homeAction(Model model){
        model.addAttribute("fundations", institutionRepository.findAll());
        return "index";
    }
}
