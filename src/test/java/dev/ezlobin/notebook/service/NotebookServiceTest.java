package dev.ezlobin.notebook.service;

import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.enums.Color;
import dev.ezlobin.notebook.exception.WrongRequestDataException;
import dev.ezlobin.notebook.mapper.NotebookMapper;
import dev.ezlobin.notebook.repository.NotebookRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotebookServiceTest {
    @Mock
    private NotebookMapper mapper;
    @Mock
    private NotebookRepository repository;
    @InjectMocks
    private NotebookService notebookService;

    @Test
    public void testFindById_WhenInsertCorrect(){
        String notebookId = "65bcf5a66cb4bd11125f4598";
        final String expectedAuthor = "Author name";
        Notebook expected = new Notebook(expectedAuthor, Color.GREEN);

        when(repository.findById(notebookId)).thenReturn(Optional.of(expected));

        Notebook actual = notebookService.findById(notebookId);

        verify(repository, times(1)).findById(notebookId);
        assertEquals(expectedAuthor, actual.getAuthor());
    }

    @Test
    public void testFindById_ShouldThrowException_WhenInsertedIdNull(){
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> notebookService.findById(null));
        assertEquals("An exception was thrown while getting entity! Entity ID cannot be null", thrown.getMessage());
    }

    @Test
    public void testFindById_ShouldThrowException_WhenInsertedNonExistedId(){
        String unexpectedId = "65bcf5a66cb4bd11125f4598";

        when(repository.findById(anyString())).thenReturn(Optional.empty());

        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> notebookService.findById(unexpectedId));
        verify(repository, times(1)).findById(anyString());
        assertEquals("An exception was thrown while getting entity! An entity with the specified id was not found. Id: " + unexpectedId, thrown.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenInsertedNull(){
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> notebookService.save(null));
        assertEquals("An exception was thrown while saving notebook! The entity being stored can't be null", thrown.getMessage());
    }
}