package com.example.mongodb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
@Data
public class Course {
    @Id
    @Schema(description = "The unique identifier of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @Schema(description = "The name of the course", example = "Java")
    private String name;
}