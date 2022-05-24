package com.autosuggestion.engine.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TrieNode {

    private int MAX_CHILDREN_COUNT = 128;

    private char data;
    private TrieNode parent;
    private TrieNode[] children = new TrieNode[MAX_CHILDREN_COUNT];
    private boolean isEnd = false;

    public TrieNode(char c) {
        data = c;
    }
}
