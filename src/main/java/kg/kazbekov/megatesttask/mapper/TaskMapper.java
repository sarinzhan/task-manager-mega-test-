package kg.kazbekov.megatesttask.mapper;

import kg.kazbekov.megatesttask.dto.request.TaskCreateRequest;
import kg.kazbekov.megatesttask.dto.request.UpdateTaskRequest;
import kg.kazbekov.megatesttask.dto.response.GetAllTaskResponse;
import kg.kazbekov.megatesttask.dto.response.GetByIdResponse;
import kg.kazbekov.megatesttask.dto.response.TaskCreateResponse;
import kg.kazbekov.megatesttask.dto.response.UpdateTaskResponse;
import kg.kazbekov.megatesttask.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskCreateRequest dto);

    TaskCreateResponse toResponse(Task task);

    GetByIdResponse toGetByIdResponse(Task task);

    List<GetAllTaskResponse> toGetAllTaskResponse(List<Task> task);

    Task updateTaskRequestToEntity(UpdateTaskRequest request);

    UpdateTaskResponse toUpdateTaskResponse(Task task);
}
