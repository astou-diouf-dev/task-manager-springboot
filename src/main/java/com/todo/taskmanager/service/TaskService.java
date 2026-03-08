package com.todo.taskmanager.service;

import com.todo.taskmanager.entity.Task;
import com.todo.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Lister
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Sauvegarder (ajout / modification)
    public void save(Task task) {
        taskRepository.save(task);
    }

    // Récupérer par ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
    }

    // Supprimer
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}