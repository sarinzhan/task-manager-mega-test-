package kg.kazbekov.megatesttask.dto.response;

import java.io.Serializable;

public record GetAllTaskResponse(
        Long id,
        String title,
        String description,
        boolean completed
)implements Serializable { }
