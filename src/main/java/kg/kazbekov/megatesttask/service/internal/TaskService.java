package kg.kazbekov.megatesttask.service.internal;

import kg.kazbekov.megatesttask.dto.request.TaskCreateRequest;
import kg.kazbekov.megatesttask.dto.request.UpdateTaskRequest;
import kg.kazbekov.megatesttask.dto.response.GetAllTaskResponse;
import kg.kazbekov.megatesttask.dto.response.GetByIdResponse;
import kg.kazbekov.megatesttask.dto.response.TaskCreateResponse;
import kg.kazbekov.megatesttask.dto.response.UpdateTaskResponse;
import kg.kazbekov.megatesttask.exception.server.MessageNotSendedException;
import kg.kazbekov.megatesttask.model.Task;

import java.util.List;

public interface TaskService {
    TaskCreateResponse create(TaskCreateRequest task) throws MessageNotSendedException;
    List<GetAllTaskResponse> getAll();
    GetByIdResponse getById(Long id);
    UpdateTaskResponse update(UpdateTaskRequest task);
    void delete(Long id);
}
