package kg.kazbekov.megatesttask.dto.response;

public record TaskCreateResponse(
        Long id,
        String title,
        String description,
        boolean completed
) {
}
