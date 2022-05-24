package com.autosuggestion.engine.engine;

import com.autosuggestion.engine.dao.TrieDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EngineApplicationTests {

	private String prefix = "Ta";

	private List<String> words;

	@Mock
	private TrieDAO trieDAO;

	private void initializeWords() {
		words = new ArrayList<>();
		words.add("Tank");
		words.add("Tang");
		words.add("TanQ");
		words.add("TanC");
	}

	@BeforeEach
	void setup() {
	  initializeWords();
	  Mockito.when(trieDAO.suggest(prefix)).thenReturn(words);
	}

	@Test
	void notNullTest() {
	  Assertions.assertNotNull(trieDAO);
	}

	@Test
	void positiveSuggestionsTest() {
       Assertions.assertEquals(trieDAO.suggest(prefix).size(),4);
	}

	@Test
	void negativeSuggestionsTest() {
		Assertions.assertFalse(trieDAO.suggest(prefix).size() == 3);
	}

}
