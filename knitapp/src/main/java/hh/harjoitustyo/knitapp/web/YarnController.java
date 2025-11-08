package hh.harjoitustyo.knitapp.web;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import hh.harjoitustyo.knitapp.domain.Yarn;
import hh.harjoitustyo.knitapp.domain.YarnRepository;

@Controller
public class YarnController {

    private YarnRepository yarnRepository;

    public YarnController(YarnRepository yarnRepository) {
        this.yarnRepository = yarnRepository;
    }

    // Get all yarns
    @GetMapping("/yarns")
    public String getYarns(Model model) {
        model.addAttribute("yarns", yarnRepository.findAll());
        return "allyarns";
    }

    // View a specific yarn
    @GetMapping("/viewyarn/{id}")
    public String viewYarn(@PathVariable Long id, Model model) {
        Yarn yarn = yarnRepository.findById(id).orElse(null);
        model.addAttribute("yarn", yarn);
        model.addAttribute("projects", yarn.getProjects());
        return "viewyarn";
    }

    // Edit yarn
    @GetMapping("/edityarn/{id}")
    public String editYarnForm(@PathVariable Long id, Model model) {
        model.addAttribute("yarn", yarnRepository.findById(id).orElse(null));
        return "edityarn";
    }

    @PostMapping("/edityarn/{id}")
    public String editYarnSubmit(@PathVariable Long id, @Valid @ModelAttribute Yarn yarn, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("yarn", yarn);
            return "edityarn";
        }
        yarn.setYarnId(id);
        yarnRepository.save(yarn);
        return "redirect:/viewyarn/" + id;
    }

    // Add a new Yarn
    @GetMapping("/newyarn")
    public String newYarnForm(Model model) {
        model.addAttribute("yarn", new Yarn());
        return "newyarn";
    }

    public String saveNewYarn(@Valid @ModelAttribute Yarn yarn, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("yarn", yarn);
            return "newyarn";
        }
        yarnRepository.save(yarn);
        return "redirect:/yarns";
    }

    // Delete existing yarn
    @GetMapping("/deleteyarn/{id}")
    public String confirmDeleteYarn(@PathVariable Long id, Model model) {
        model.addAttribute("yarn", yarnRepository.findById(id).orElse(null));
        return "deleteyarn";
    }

    @PostMapping("/deleteyarn/{id}")
    public String deleteYarn(@PathVariable Long id) {
        yarnRepository.deleteById(id);
        return "redirect:/yarns";
    }

}
