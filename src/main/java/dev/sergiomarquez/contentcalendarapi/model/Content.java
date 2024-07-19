package dev.sergiomarquez.contentcalendarapi.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Content(
        @Id
        Integer id,
        @NotBlank(message = "Title is required")
        String title,
        String description,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {
}
