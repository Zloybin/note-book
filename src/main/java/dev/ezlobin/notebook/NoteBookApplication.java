package dev.ezlobin.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class NoteBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteBookApplication.class, args);
    }

}
