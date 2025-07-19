package kg.kazbekov.megatesttask.dto.request;

public record TaskCreateRequest(
    String title,
    String description,
    boolean completed
) {
}
