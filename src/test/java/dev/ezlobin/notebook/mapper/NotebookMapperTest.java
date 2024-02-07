package dev.ezlobin.notebook.mapper;

import dev.ezlobin.notebook.dto.NotebookDto;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.enums.Color;
import dev.ezlobin.notebook.exception.NotebookMapperException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotebookMapperTest {

    private NotebookMapper notebookMapper = new NotebookMapper();

    @Test
    public void testToEntity_WhereDtoIsCorrect(){
        Notebook expected = new Notebook("authorName", Color.BLUE);
        NotebookDto correctDto = new NotebookDto();
        correctDto.setAuthor("authorName");
        correctDto.setColor("BLUE");

        Notebook actual = notebookMapper.toEntity(correctDto);
        assertEquals(expected, actual);
    }

    @Test
    public void testToEntity_ResultThrownException_WhereColorNull(){
        NotebookDto actual = new NotebookDto();
        actual.setAuthor("authorName");
        actual.setColor(null);

        Throwable thrown = assertThrows(NotebookMapperException.class, () -> {
            notebookMapper.toEntity(actual);
        });

        String expectedMessage = "Wrong color value! Inserted value can't be null";
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void testToEntity_ResultThrownException_WhereColorWrong(){
        NotebookDto actual = new NotebookDto();
        actual.setAuthor("authorName");
        actual.setColor("WRONG_VALUE");

        Throwable thrown = assertThrows(NotebookMapperException.class, () -> {
            notebookMapper.toEntity(actual);
        });

        assertNotNull(thrown.getMessage());

        String expectedMessage = "Wrong color name! Inserted value was: " + actual.getColor();
        assertEquals(expectedMessage, thrown.getMessage());
    }
}