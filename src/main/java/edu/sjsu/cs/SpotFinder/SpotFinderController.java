package edu.sjsu.cs.SpotFinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotFinderController {
    @GetMapping("/home")
    public String index() {
        return "Welcome to Spot Finder!";
    }
}
