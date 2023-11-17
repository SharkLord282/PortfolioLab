package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @RequestMapping("/")
    public String homeAction(Model model){
        String[] array = {"Lol","lel"};
        model.addAttribute("fundations", array );
        return "index";
    }
}
