package com.pollapp.controller;

import com.pollapp.entity.UserData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class UserDataController {

    @GetMapping("")
    public List<UserData> getData() {
        // TODO
        return null;
    }

    @PostMapping("")
    public UserData createData() {
        // TODO
        return null;
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
