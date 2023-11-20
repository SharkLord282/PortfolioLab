package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.category.CategoryRepository;

@Controller
public class FormController {
    private final CategoryRepository categoryRepository;

    public FormController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "form";
    }

}
