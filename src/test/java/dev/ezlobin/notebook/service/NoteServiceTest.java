package dev.ezlobin.notebook.service;

import dev.ezlobin.notebook.dto.NoteDto;
import dev.ezlobin.notebook.entity.Note;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.enums.Color;
import dev.ezlobin.notebook.exception.WrongRequestDataException;
import dev.ezlobin.notebook.mapper.NoteMapper;
import dev.ezlobin.notebook.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NoteServiceTest {
    @Mock
    private NoteMapper noteMapper;
    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private NoteService noteService;



    @Test
    public void testFindById_WhenInsertCorrect(){
        String noteId = "65bcf5a66cb4bd11125f4587";
        String expectedContent = "Some content";
        Note expectedNote = new Note(expectedContent);
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(expectedNote));
        Note actualNote = noteService.findById(noteId);
        verify(noteRepository, times(1)).findById(noteId);
        assertEquals(expectedContent, actualNote.getContent());
    }

    @Test
    public void testFindById_ShouldThrowException_WhenInsertIdNull(){
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> noteService.findById(null));
        assertEquals("An exception was thrown while attempting to retrieve a note from the data store. The note ID cannot be null.", thrown.getMessage());
    }

    @Test
    public void testFindById_ShouldThrowException_WhenInsertIncorrectId(){
        String incorrectId = "65bcf5a66cb4bd11125f4576";
        when(noteRepository.findById(any())).thenReturn(Optional.empty());
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> noteService.findById(incorrectId));
        assertEquals(String.format("An exception was thrown while attempting to retrieve a note from the data store. Note with id: %s does not exist.", incorrectId), thrown.getMessage());
    }

    @Test
    public void testSave_WhenInsertCorrect(){
        String content = "Some content";
        NoteDto dto = new NoteDto(content);
        Note note = new Note(content);
        when(noteMapper.toEntity(dto)).thenReturn(note);
        noteService.save(dto);
        verify(noteMapper, times(1)).toEntity(dto);
        verify(noteRepository, times(1)).insert(note);
    }

    @Test
    public void testSave_ShouldThrowException_WhenInsertIdNull(){
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> noteService.save(null));
        assertEquals("An exception was thrown while saving note! The entity being stored can't be null", thrown.getMessage());
    }

    @Test
    public void testUpdate_WhenInsertCorrect(){
        String updatedContent = "Some updated content";
        NoteDto dto = new NoteDto(updatedContent);
        String id = "65bcf5a66cb4bd11125f4587";
        Note note = new Note(updatedContent);
        note.setId(id);

        when(noteMapper.toEntity(dto)).thenReturn(note);
        when(noteRepository.existsById(id)).thenReturn(true);
        when(noteRepository.findById(id)).thenReturn(Optional.of(new Note("Some content")));
        noteService.update(id, dto);

        verify(noteMapper, times(1)).toEntity(dto);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void testUpdate_ShouldThrowException_WhenInsertIdNull(){
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> noteService.update(null, new NoteDto("Some updated content")));
        assertEquals("An exception was thrown while updating note! The note ID can't be null", thrown.getMessage());
    }

    @Test
    public void testUpdate_ShouldThrowException_WhenInsertNoteDtoNull(){
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> noteService.update("65bcf5a66cb4bd11125f4587", null));
        assertEquals("An exception was thrown while updating note! The note dto can't be null", thrown.getMessage());
    }

    @Test
    public void testUpdate_ShouldThrowException_WhenInsertIncorrectId(){
        String id = "65bcf5a66cb4bd11125f4587";
        NoteDto dto = new NoteDto("Some updated content");
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> noteService.update(id, dto));
        assertEquals(String.format("An exception was thrown while updating note! Note with id: %s was not exist.", id), thrown.getMessage());
    }

    @Test
    public void testDeleteById_ShouldThrowException_WhenInsertIdNull(){
        WrongRequestDataException thrown = assertThrows(WrongRequestDataException.class, () -> noteService.deleteById(null));
        assertEquals("An exception was thrown while deleting note! The note ID can't be null" ,thrown.getMessage());
    }
}