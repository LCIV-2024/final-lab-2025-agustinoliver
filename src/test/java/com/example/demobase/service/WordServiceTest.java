package com.example.demobase.service;

import com.example.demobase.dto.WordDTO;
import com.example.demobase.model.Word;
import com.example.demobase.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordServiceTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    private Word word1;
    private Word word2;
    private Word word3;

    @BeforeEach
    void setUp() {
        word1 = new Word(1L, "PROGRAMADOR", true);
        word2 = new Word(2L, "COMPUTADORA", false);
        word3 = new Word(3L, "TECNOLOGIA", false);
    }

    @Test
    void testGetAllWords() {
        // TODO: Implementar el test para getAllWords
        List<Word> words = Arrays.asList(word1, word2, word3);
        when(wordRepository.findAllOrdered()).thenReturn(words);

        List<WordDTO> resultado = wordService.getAllWords();

        assertNotNull(resultado);
        assertEquals(3, resultado.size());

        assertEquals(1L, resultado.get(0).getId());
        assertEquals("PROGRAMADOR", resultado.get(0).getPalabra());
        assertTrue(resultado.get(0).getUtilizada());

        assertEquals(2L, resultado.get(1).getId());
        assertEquals("COMPUTADORA", resultado.get(1).getPalabra());
        assertFalse(resultado.get(1).getUtilizada());

        assertEquals(3L, resultado.get(2).getId());
        assertEquals("TECNOLOGIA", resultado.get(2).getPalabra());
        assertFalse(resultado.get(2).getUtilizada());

        verify(wordRepository, times(1)).findAllOrdered();
        
    }

    @Test
    void testGetAllWords_EmptyList() {
        // TODO: Implementar el test para getAllWords_EmptyList
        when(wordRepository.findAllOrdered()).thenReturn(Arrays.asList());

        List<WordDTO> resultado = wordService.getAllWords();

        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        assertTrue(resultado.isEmpty());

        verify(wordRepository, times(1)).findAllOrdered();
        
    }
}

