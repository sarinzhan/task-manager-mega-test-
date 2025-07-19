package kg.kazbekov.megatesttask.dto.request;

public record UpdateTaskRequest(
        Long id,
        String title,
        String description,
        boolean completed
) {
}
