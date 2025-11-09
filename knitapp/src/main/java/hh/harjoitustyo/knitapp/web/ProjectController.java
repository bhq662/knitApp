package hh.harjoitustyo.knitapp.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.harjoitustyo.knitapp.domain.CategoryRepository;
import hh.harjoitustyo.knitapp.domain.Project;
import hh.harjoitustyo.knitapp.domain.ProjectRepository;
import hh.harjoitustyo.knitapp.domain.StatusRepository;
import hh.harjoitustyo.knitapp.domain.Yarn;
import hh.harjoitustyo.knitapp.domain.YarnRepository;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class ProjectController {

    private ProjectRepository projectRepository;
    private CategoryRepository categoryRepository;
    private StatusRepository statusRepository;
    private YarnRepository yarnRepository;

    public ProjectController(
            ProjectRepository projectRepository,
            CategoryRepository categoryRepository,
            StatusRepository statusRepository,
            YarnRepository yarnRepository) {
        this.projectRepository = projectRepository;
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
        this.yarnRepository = yarnRepository;

    }

    // View all projects
    @GetMapping("/projects")
    public String getProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "allprojects"; // index.html
    }

    // View a specific project
    @GetMapping("/viewproject/{id}")
    public String viewProject(@PathVariable Long id, Model model) {
        var projectOpt = projectRepository.findById(id);
        if (projectOpt.isEmpty()) {
            return "redirect:/projects";
        }
        model.addAttribute("project", projectOpt.get());
        return "viewproject";
    }

    // Edit project
    @GetMapping("/editproject/{id}")
    public String editProjectForm(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null && !project.getYarns().isEmpty()) {
            project.setYarnText(project.getYarns().get(0).getYarnName());
        }
        model.addAttribute("project", project);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("statuses", statusRepository.findAll());
        return "editproject";
    }

    @PostMapping("/editproject/{id}")
    public String editProjectSubmit(@PathVariable Long id, @Valid @ModelAttribute Project project,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("statuses", statusRepository.findAll());
            return "editproject";
        }
        if (project.getYarnText() != null && !project.getYarnText().isBlank()) {
            Yarn yarn = yarnRepository.findByYarnName(project.getYarnText()).orElseGet(() -> {
                Yarn newYarn = new Yarn();
                newYarn.setYarnName(project.getYarnText());
                return yarnRepository.save(newYarn);
            });
            project.setYarns(List.of(yarn));
        }
        project.setProjectId(id);
        projectRepository.save(project);
        return "redirect:/viewproject/" + id;
    }

    // New project
    @GetMapping("/newproject")
    public String newProjectForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("statuses", statusRepository.findAll());
        model.addAttribute("yarns", yarnRepository.findAll());
        return "newproject";
    }

    @PostMapping("/newproject")
    public String saveNewProject(@Valid @ModelAttribute Project project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("statuses", statusRepository.findAll()); // <-- add statuses
            model.addAttribute("yarns", yarnRepository.findAll()); // <-- add yarns
            return "newproject";
        }
        if (project.getYarnText() != null && !project.getYarnText().isBlank()) {
            Yarn yarn = yarnRepository.findByYarnName(project.getYarnText()).orElseGet(() -> {
                Yarn newYarn = new Yarn();
                newYarn.setYarnName(project.getYarnText());
                return yarnRepository.save(newYarn);
            });
            project.setYarns(List.of(yarn));
        }
        projectRepository.save(project);
        return "redirect:/projects";
    }

    // Delete project with confirmation
    @GetMapping("/deleteproject/{id}")
    public String confirmDeleteProject(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectRepository.findById(id).orElse(null));
        return "deleteproject";
    }

    @PostMapping("/deleteproject/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/projects";
    }
}