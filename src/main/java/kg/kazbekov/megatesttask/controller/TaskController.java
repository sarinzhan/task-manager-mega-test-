package kg.kazbekov.megatesttask.controller;

import kg.kazbekov.megatesttask.dto.RestResponse;
import kg.kazbekov.megatesttask.dto.request.TaskCreateRequest;
import kg.kazbekov.megatesttask.dto.request.UpdateTaskRequest;
import kg.kazbekov.megatesttask.dto.response.GetAllTaskResponse;
import kg.kazbekov.megatesttask.dto.response.GetByIdResponse;
import kg.kazbekov.megatesttask.dto.response.TaskCreateResponse;
import kg.kazbekov.megatesttask.dto.response.UpdateTaskResponse;
import kg.kazbekov.megatesttask.exception.server.MessageNotSendedException;
import kg.kazbekov.megatesttask.service.internal.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<RestResponse<TaskCreateResponse>> create(
            @RequestBody TaskCreateRequest task
    ) throws MessageNotSendedException {
        return RestResponse
                .message("Задача создана")
                .ok(
                        service.create(task)
                );
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<GetAllTaskResponse>>> getAll() {
        return RestResponse.ok(
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<GetByIdResponse>> getById(
            @PathVariable Long id
    ) {
        return RestResponse.ok(
                service.getById(id)
        );
    }

    @PutMapping()
    public ResponseEntity<RestResponse<UpdateTaskResponse>> update(
            @RequestBody UpdateTaskRequest task
    ) {
        return RestResponse
                .message("Задача обновлена")
                .ok(service.update(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return RestResponse
                .message("Задача удалена")
                .build();
    }
}
