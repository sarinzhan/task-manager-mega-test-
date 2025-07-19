package kg.kazbekov.megatesttask.dto.response;

public record GetByIdResponse (
        Long id,
        String title,
        String description,
        boolean completed
){
}
