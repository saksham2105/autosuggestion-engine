package com.autosuggestion.engine.controllers;

import com.autosuggestion.engine.dao.TrieDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
public class ProjectController {

    @Autowired
    private TrieDAO trieDAO;

    private static final Logger logger = LogManager.getLogger(ProjectController.class);

    private final String NO_SUGGESTIONS_MESSAGE = "Currently No Suggestions Available";

    private final String EXCEPTION_MESSAGE = "Some Exception Occured";

    private final String INSERTION_SUCCESS_MESSAGE = "Word Inserted Successfully in Trie";

    /**
     * An Api to Suggest Words based on Prefix
     *
     * @param prefix
     * @return
     */
    @GetMapping(value = "get/suggestions")
    public Object getSuggestions(@RequestParam(value = "prefix") String prefix) {
        logger.info("Inside Project Controller to get suggestions for prefix {}", prefix);
        try {
            List<String> suggestions = trieDAO.suggest(prefix);
            if (suggestions == null) {
                logger.info("Suggestions are null for prefix {}", prefix);
                return NO_SUGGESTIONS_MESSAGE;
            }
            return suggestions;
        } catch (Exception exception) {
            logger.error("Some Exception occured while searching in Trie as {}", exception.getMessage());
            return EXCEPTION_MESSAGE;
        }
    }

    /**
     * An Api to Insert Word in Trie
     * @param word
     * @return
     */
    @GetMapping(value = "insert/word")
    public String insertWord(@RequestParam(value = "word") String word) {
        logger.info("Inside Project Controller to get suggestions for word {}", word);
        try {
            trieDAO.insert(word);
            return INSERTION_SUCCESS_MESSAGE;
        } catch (Exception exception) {
            logger.error("Some Exception occured while inserting in Trie as {}", exception.getMessage());
            return EXCEPTION_MESSAGE;
        }
    }
}
