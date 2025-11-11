package com.example.tasktracker.service;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> all() { return repo.findAll(); }
    public Task get(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found")); }
    public Task create(Task t) { return repo.save(t); }
    public Task update(Long id, Task incoming) {
        Task t = get(id);
        t.setTitle(incoming.getTitle());
        t.setDescription(incoming.getDescription());
        t.setStatus(incoming.getStatus());
        return repo.save(t);
    }
    public Task patch(Long id, Task incoming) {
        Task t = get(id);
        if (incoming.getTitle() != null) t.setTitle(incoming.getTitle());
        if (incoming.getDescription() != null) t.setDescription(incoming.getDescription());
        if (incoming.getStatus() != null) t.setStatus(incoming.getStatus());
        return repo.save(t);
    }
    public void delete(Long id) { repo.deleteById(id); }
    public List<Task> search(String q) { return repo.findByTitleContainingIgnoreCase(q); }
    public List<Task> byStatus(Status s) { return repo.findByStatus(s); }
}