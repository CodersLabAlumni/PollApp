package com.pollapp.controller;

import com.pollapp.entity.Category;
import com.pollapp.entity.Poll;
import com.pollapp.repository.CategoryRepository;
import com.pollapp.response.PollResponse;
import com.pollapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private PollService pollService;

    @Autowired
    private CategoryRepository categoryRepository;

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
    public Page<PollResponse> getAllPollsByCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction, @PathVariable int categoryId) {
        return pollService.getPollsByCategory(categoryId, new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }

    @GetMapping("/{categoryId}/polls/closed")
    public Page<PollResponse> getClosedPollsByCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction, @PathVariable int categoryId) {
        return pollService.getClosedPollsByCategory(categoryId, new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }

    @GetMapping("/{categoryId}/polls/ongoing")
    public Page<PollResponse> getOngoingPollsByCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction, @PathVariable int categoryId) {
        return pollService.getOnGoingPollsByCategory(categoryId, new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }

    @GetMapping("/{categoryId}/polls/available")
    public Page<Poll> getAvailablePollsByCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction, @PathVariable int categoryId) {
        return pollService.getAvailablePollsByCategory(categoryId, new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }
}
