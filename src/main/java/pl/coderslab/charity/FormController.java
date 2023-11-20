package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.Institution.InstitutionRepository;
import pl.coderslab.charity.category.CategoryRepository;

@Controller
public class FormController {
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public FormController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions" ,institutionRepository.findAll());
        return "form";
    }


}
