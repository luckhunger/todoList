package com.todoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {
    @GetMapping("list")
    public String list(Model model) {
        // model : MVC 중 model
        model.addAttribute("data", "hello!!");
        return "list";
    }
}
