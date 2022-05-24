package com.autosuggestion.engine.services;

import com.autosuggestion.engine.dto.TrieNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrieUtility {

    /**
     * A Helper Function to recursively Search Pattern in the whole Trie
     * @param node
     * @param res
     * @param prefix
     */
    public void recursiveHelper(TrieNode node, List<String> res, String prefix) {
        if (node == null ) return;
        if (node.isEnd()) res.add(prefix + node.getData());
        for (TrieNode child: node.getChildren()) {
            recursiveHelper(child, res, prefix + node.getData());
        }
    }
}
