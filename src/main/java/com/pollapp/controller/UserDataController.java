package com.pollapp.controller;

import com.pollapp.entity.UserData;
import com.pollapp.repository.UserDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class UserDataController {

	@Autowired
	private UserDataRepository userDataRepository;
	
    @GetMapping("")
    public List<UserData> getData() {
        return userDataRepository.findAll();
    }

    @PostMapping("")
    public UserData createData(@RequestBody UserData userData) {
        return userDataRepository.save(userData);
    }

    @GetMapping("/{dataId}")
    public UserData getData(@PathVariable long dataId) {
        // TODO
        return null;
    }

    @PutMapping("/{dataId}")
    public UserData updateData(@PathVariable long dataId) {
        // TODO
        return null;
    }

    @DeleteMapping("/{dataId}")
    public UserData deleteData(@PathVariable long dataId) {
        // TODO
        return null;
    }
}
