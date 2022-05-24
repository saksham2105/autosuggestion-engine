package com.autosuggestion.engine.dao;

import java.util.List;

public interface TrieDAO {
    public void insert(String word);
    public List<String> suggest(String prefix);
}
