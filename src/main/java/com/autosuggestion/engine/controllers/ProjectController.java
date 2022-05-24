package com.autosuggestion.engine.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
public class ProjectController {

    /**
     * An Api to Suggest Words based on Prefix
     * @param prefix
     * @return
     */
    @GetMapping(value = "get/suggestions")
    public List<String> getSuggestions(@RequestParam(value = "prefix") String prefix) {
        List<String> suggestions = null;
        return suggestions;
    }
}
