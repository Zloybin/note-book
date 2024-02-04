package dev.ezlobin.notebook.mapper;

import dev.ezlobin.notebook.dto.NotebookDto;
import dev.ezlobin.notebook.entity.Notebook;
import dev.ezlobin.notebook.enums.Color;
import dev.ezlobin.notebook.exception.NotebookMapperException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NotebookMapper implements Mapper<Notebook, NotebookDto> {
    @Override
    public Notebook toEntity(NotebookDto dto) {
        final String colorName = dto.getColor();

        if (colorName == null) {
            final String message = "Wrong color value! Inserted value can't be null";
            throw new NotebookMapperException(message);
        }

        Set<String> colorValues = Arrays.stream(Color.values())
                .map(Color::toString)
                .collect(Collectors.toSet());
        if (!colorValues.contains(colorName)) {
            final String message = String.format("Wrong color name! Inserted value was: %s", colorName);
            throw new NotebookMapperException(message);
        }

        Notebook notebook = new Notebook(dto.getAuthor(), Color.valueOf(colorName));

        return notebook;
    }
}
