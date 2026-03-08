package com.todo.taskmanager.controller;

import com.todo.taskmanager.entity.Status;
import com.todo.taskmanager.entity.Task;
import com.todo.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // Injection par constructeur (bonne pratique)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ✅ 1️⃣ Lister toutes les tâches
    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("task", new Task()); // pour le formulaire
        return "index";
    }

    // ✅ 2️⃣ Sauvegarder (ajout ou modification)
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    // ✅ 3️⃣ Modifier (charger la tâche dans le formulaire)
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("task", taskService.getTaskById(id));
        return "index";
    }

    // ✅ 4️⃣ Supprimer
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    // ✅ 5️⃣ Marquer comme terminée
    @GetMapping("/complete/{id}")
    public String markAsCompleted(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        task.setStatus(Status.TERMINE);
        taskService.save(task);
        return "redirect:/tasks";
    }
}