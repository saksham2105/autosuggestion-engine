package com.autosuggestion.engine.dao;

import com.autosuggestion.engine.dto.TrieNode;
import com.autosuggestion.engine.services.TrieUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrieDAOImpl implements TrieDAO {

    @Autowired
    private TrieUtility trieUtility;

    TrieNode root = new TrieNode(' '); //inserting empty character in Trie

    /**
     * A Helper Function to Insert any Specific Word in Trie
     *
     * @param word
     */
    @Override
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.getChildren()[ch] == null)
                node.getChildren()[ch] = new TrieNode(ch);
            node = node.getChildren()[ch];
        }
        node.setEnd(true);
    }

    /**
     * A Helper Function to Suggest Words By Iterating in the Trie
     * This Method Must Be Cacheable as Caching will reduce search complexity
     * from logarithmic to Constant
     *
     * @param prefix
     * @return
     */
    @Override
    @Cacheable(value = "${suggestions.cache.name}", key = "prefix")
    public List<String> suggest(String prefix) {
        TrieNode node = root;
        List<String> res = new ArrayList<>();
        for (char ch : prefix.toCharArray()) {
            node = node.getChildren()[ch];
            if (node == null)
                return new ArrayList<>();
        }
        trieUtility.recursiveHelper(node, res, prefix.substring(0, prefix.length() - 1));
        return res;
    }
}
