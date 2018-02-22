package com.pollapp.controller;

import com.pollapp.bean.poll.PollJsonResponse;
import com.pollapp.bean.poll.PollProcess;
import com.pollapp.entity.Category;
import com.pollapp.repository.CategoryRepository;
import com.pollapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollProcess pollProcess;

    @GetMapping("")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable int categoryId) {
        return categoryRepository.findOne(categoryId);
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
    public List<PollJsonResponse> getPollsByCategory(@PathVariable int categoryId) {
        List<PollJsonResponse> response = new ArrayList<>();
        pollRepository.findAllByCategoriesId(categoryId).forEach(poll ->
                response.add(new PollJsonResponse(poll, pollProcess.process(poll))));
        return response;
    }
}
