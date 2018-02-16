package com.pollapp.controller;

import com.pollapp.entity.Category;
import com.pollapp.entity.Poll;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("")
    public List<Category> getCategories() {
        // TODO
        return null;
    }

    @PostMapping("")
    public Category createCategory() {
        // TODO
        return null;
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable long categoryId) {
        // TODO
        return null;
    }

    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable long categoryId) {
        // TODO
        return null;
    }

    @DeleteMapping("/{categoryId}")
    public Category deleteCategory(@PathVariable long categoryId) {
        // TODO
        return null;
    }

    @GetMapping("/{categoryId}/polls")
    public List<Poll> getPollsByCategory(@PathVariable long categoryId) {
        // TODO
        return null;
    }
}
