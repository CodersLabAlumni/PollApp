package com.pollapp.controller;

import com.pollapp.entity.Category;
import com.pollapp.entity.Poll;
import com.pollapp.repository.CategoryRepository;
import com.pollapp.repository.PollRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PollRepository pollRepository;

    @GetMapping("")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
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
    public List<Poll> getPollsByCategory(@PathVariable int categoryId) {
        return pollRepository.findAllByCategoriesId(categoryId);
    }
}
