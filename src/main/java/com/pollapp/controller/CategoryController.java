package com.pollapp.controller;

import com.pollapp.bean.Ip;
import com.pollapp.entity.Category;
import com.pollapp.repository.CategoryRepository;
import com.pollapp.response.PollResponse;
import com.pollapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PollService pollService;

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

    @GetMapping("/{categoryId}/polls/ongoing")
    public List<PollResponse> getOngoingPollsByCategory(@PathVariable int categoryId) {
        return pollService.getOpenedPollsAvailableToUserByIpAndCategoryId(Ip.remote(), categoryId);
    }

    @GetMapping("/{categoryId}/polls/closed")
    public Page<PollResponse> getClosedPollsByCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "2") int size, @PathVariable int categoryId) {
        return pollService.getClosedPollsByCategoryId(categoryId, new PageRequest(page, size));
    }
}
