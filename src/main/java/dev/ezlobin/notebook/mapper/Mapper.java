package dev.ezlobin.notebook.mapper;

public interface Mapper<T, D> {
    T toEntity(D dto);
}
