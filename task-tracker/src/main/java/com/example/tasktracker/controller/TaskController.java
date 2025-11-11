package com.example.tasktracker.controller;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) { this.service = service; }

    @GetMapping
    public List<Task> all(@RequestParam(value = "q", required = false) String q,
                          @RequestParam(value = "status", required = false) Status status) {
        if (q != null && !q.isBlank()) return service.search(q);
        if (status != null) return service.byStatus(status);
        return service.all();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) { return service.get(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody Task t) { return service.create(t); }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task t) { return service.update(id, t); }

    @PatchMapping("/{id}")
    public Task patch(@PathVariable Long id, @RequestBody Task t) { return service.patch(id, t); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}