package dev.ezlobin.notebook.service;

import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.enums.Color;
import dev.ezlobin.notebook.exception.WrongRequestDataException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotebookManagerServiceTest {

    @Mock
    private NotebookService notebookService;
    @Mock
    private NoteService noteService;

    @InjectMocks
    private NotebookManagerService notebookManagerService;

    @Test
    public void testDeleteNote_WhenIsCorrect(){
        String notebookId = "65bcf5a66cb4bd11125f4598";
        String noteId = "65bcf5a66cb4bd11125f4598";

        Notebook notebook = new Notebook("Some Author", Color.BLUE);
        notebook.setId(notebookId);
        Note note = new Note("Some content");
        note.setId(noteId);
        notebook.getNotes().add(note);

        when(notebookService.findById(notebookId)).thenReturn(notebook);
        when(noteService.findById(noteId)).thenReturn(note);

        notebookManagerService.deleteNote(notebookId, noteId);

        verify(notebookService, times(1)).findById(notebookId);
        verify(noteService, times(1)).findById(noteId);
        verify(notebookService, times(1)).update(notebook);
    }

    @Test
    public void testDeleteNote_ShouldThrowException_WhenInsertIncorrectNoteId(){
        String notebookId = "65bcf5a66cb4bd11125f4598";
        String correctNoteId = "65bcf5a66cb4bd11125f4598";
        String incorrectNoteId = "65bcf5a66cb4bd11125f4599";

        Notebook notebook = new Notebook("Some Author", Color.BLUE);
        Note note = new Note("Some content");
        note.setId(correctNoteId);
        notebook.getNotes().add(note);

        when(notebookService.findById(notebookId)).thenReturn(notebook);


        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> notebookManagerService.deleteNote(notebookId, incorrectNoteId));
        assertEquals(String.format("An exception was thrown while attempting to delete a note from a notebook. Notebook with id: %s does not contain a note with id: %s.", notebookId, incorrectNoteId), thrown.getMessage());
    }
}