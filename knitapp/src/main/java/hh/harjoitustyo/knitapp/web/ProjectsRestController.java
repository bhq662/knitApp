package hh.harjoitustyo.knitapp.web;

import org.springframework.web.bind.annotation.*;
import hh.harjoitustyo.knitapp.domain.ProjectRepository;
import hh.harjoitustyo.knitapp.domain.Project;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectsRestController {

    private final ProjectRepository repository;

    public ProjectsRestController(ProjectRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Project> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Project> one(@PathVariable Long id) {
        return repository.findById(id);
    }
}