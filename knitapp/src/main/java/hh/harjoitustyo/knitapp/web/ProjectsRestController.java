package hh.harjoitustyo.knitapp.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project project) {
        return repository.save(project);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody Project project) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        project.setProjectId(id);
        return repository.save(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}