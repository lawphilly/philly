package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.InhousePartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class InhousePartController {

    private final InhousePartRepository inhousePartRepository;

    public InhousePartController(InhousePartRepository inhousePartRepository) {
        this.inhousePartRepository = inhousePartRepository;
    }

    @GetMapping("/inhouse/add")
    public String showInhousePartForm(Model model) {
        // ← This line makes 'inhousePart' available to Thymeleaf
        model.addAttribute("inhousePart", new InhousePart());
        return "InhousePartForm";
    }

    @PostMapping("/inhouse/add")
    public String addInhousePart(@Valid InhousePart inhousePart,
                                 BindingResult result,
                                 Model model) {
        // Enforce inv ∈ [minInv, maxInv]
        if (!inhousePart.isInvValid()) {
            result.rejectValue(
                    "inv",
                    "inventory.invalid",
                    "Inventory must be between Min and Max values"
            );
        }

        if (result.hasErrors()) {
            return "InhousePartForm";
        }

        inhousePartRepository.save(inhousePart);
        return "redirect:/parts";
    }
}
