package com.app.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth2")

@PreAuthorize("denyAll()")
public class TeskAuth2Controller {
    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('READ')")
    public String helloGet() {
        return "Hello from GET method";
    }

    @PostMapping("/post")
    public String helloPost() {
        return "Hello from POST method";
    }

    @PutMapping("/put")
    public String helloPut() {
        return "Hello from PUT method";
    }

    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hello from DELETE method";

    }
}