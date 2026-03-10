package com.todo.taskmanager.controller;

import com.todo.taskmanager.entity.Task;
import com.todo.taskmanager.entity.Status;
import com.todo.taskmanager.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    // redirection localhost:8080 -> /tasks
    @GetMapping("/")
    public String home() {
        return "redirect:/tasks";
    }

    // afficher tâches + formulaire
    @GetMapping("/tasks")
    public String viewTasks(Model model) {

        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskService.getAllTasks());

        return "index";
    }

    // sauvegarder tâche
    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute Task task) {

        taskService.saveTask(task);

        return "redirect:/tasks";
    }

    // modifier tâche
    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {

        Task task = taskService.getTaskById(id);

        model.addAttribute("task", task);
        model.addAttribute("tasks", taskService.getAllTasks());

        return "index";
    }

    // supprimer tâche
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return "redirect:/tasks";
    }

    // marquer terminé
    @GetMapping("/tasks/complete/{id}")
    public String completeTask(@PathVariable Long id) {

        Task task = taskService.getTaskById(id);

        task.setStatus(Status.TERMINE);

        taskService.saveTask(task);

        return "redirect:/tasks";
    }
}