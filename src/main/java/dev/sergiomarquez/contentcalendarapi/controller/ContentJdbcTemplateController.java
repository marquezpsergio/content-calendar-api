package dev.sergiomarquez.contentcalendarapi.controller;

import dev.sergiomarquez.contentcalendarapi.model.Content;
import dev.sergiomarquez.contentcalendarapi.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/content/jdbc")
@CrossOrigin
public class ContentJdbcTemplateController {

    private final ContentJdbcTemplateRepository repository;

    public ContentJdbcTemplateController(ContentJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public void create(@Valid @RequestBody Content content) {
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/edit")
    public void update(@RequestBody Content content) {
        repository.update(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        repository.delete(id);
    }

}
