package com.example.taskportfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.taskportfolio.form.TaskForm;
import com.example.taskportfolio.service.TaskService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }

    @PostMapping
    public String registerTask(
            @Valid @ModelAttribute("taskForm") TaskForm taskForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tasks", taskService.getTasks());
            return "tasks";
        }

        taskService.createTask(taskForm.getTitle(), taskForm.getDueDate());
        redirectAttributes.addFlashAttribute("successMessage", "登録しました。");
        return "redirect:/tasks";
    }

}
