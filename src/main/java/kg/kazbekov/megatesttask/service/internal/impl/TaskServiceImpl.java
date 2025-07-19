package kg.kazbekov.megatesttask.service.internal.impl;

import kg.kazbekov.megatesttask.dto.request.TaskCreateRequest;
import kg.kazbekov.megatesttask.dto.request.UpdateTaskRequest;
import kg.kazbekov.megatesttask.dto.response.GetAllTaskResponse;
import kg.kazbekov.megatesttask.dto.response.GetByIdResponse;
import kg.kazbekov.megatesttask.dto.response.TaskCreateResponse;
import kg.kazbekov.megatesttask.dto.response.UpdateTaskResponse;
import kg.kazbekov.megatesttask.exception.client.TaskNotFoundException;
import kg.kazbekov.megatesttask.exception.server.MessageNotSendedException;
import kg.kazbekov.megatesttask.mapper.TaskMapper;
import kg.kazbekov.megatesttask.model.Task;
import kg.kazbekov.megatesttask.repository.TaskRepository;
import kg.kazbekov.megatesttask.service.external.EmailService;
import kg.kazbekov.megatesttask.service.internal.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final EmailService emailService;
    private final TaskMapper taskMapper;


    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
    public TaskCreateResponse create(TaskCreateRequest requestTask) throws MessageNotSendedException {
        var mappedTask = taskMapper.toEntity(requestTask);

        repository.save(mappedTask);

        emailService.sendTaskCreatedEmail(
                "sarinzhankazbekov@gmail.com",
                "Новая задача создана",
                "Задача: " + requestTask.title() + "\nОписание: " + requestTask.description()
        );

        return taskMapper.toResponse(mappedTask);
    }

    @Cacheable(value = "tasks")
    @Override
    public List<GetAllTaskResponse> getAll() {
        return taskMapper.toGetAllTaskResponse(
                repository.findAll()
        );
    }

    @Override
    public GetByIdResponse getById(Long id) {
        var task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return taskMapper.toGetByIdResponse(task);
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public UpdateTaskResponse update(UpdateTaskRequest updateTaskRequest) {
        repository.findById(updateTaskRequest.id())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Task mappedTask = taskMapper.updateTaskRequestToEntity(updateTaskRequest);
        repository.save(mappedTask);
        return taskMapper.toUpdateTaskResponse(mappedTask);
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
