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
    @PreAuthorize("hasAnyAuthority('CREATE') or hasAnyAuthority('READ')")
    public String helloPost() {
        return "Hello from POST method";
    }

    @PutMapping("/put")
    public String helloPut() {
        return "Hello from PUT method";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('CREATE')")
    public String helloDelete() {
        return "Hello from DELETE method";

    }

    @PatchMapping("/patch")
    @PreAuthorize("hasAnyAuthority('REFACTOR')")
    public String helloPatch() {
        return "Hello from DELETE method";

    }
}