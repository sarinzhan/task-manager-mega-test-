package kg.kazbekov.megatesttask.dto.response;

public record UpdateTaskResponse (
        Long id,
        String title,
        String description,
        boolean completed
){
}
