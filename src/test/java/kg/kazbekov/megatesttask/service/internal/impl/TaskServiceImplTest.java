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
import kg.kazbekov.megatesttask.mapper.TaskMapperImpl;
import kg.kazbekov.megatesttask.model.Task;
import kg.kazbekov.megatesttask.repository.TaskRepository;
import kg.kazbekov.megatesttask.service.external.impl.EmailServiceImpl;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private EmailServiceImpl emailService;

    @InjectMocks
    private TaskServiceImpl service;

    private Task task;
    private TaskMapper taskMapper = new TaskMapperImpl();


    @BeforeEach
    void setUp() {
        task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .create();

        service = new TaskServiceImpl(repository, emailService, taskMapper);
    }


    @Test
    void createTask_throwMessageNotSendedException() throws MessageNotSendedException {
        // arrange
        when(repository.save(task))
                .thenReturn(task);

        doThrow(new MessageNotSendedException())
                .when(emailService)
                .sendTaskCreatedEmail(anyString(), anyString(), anyString());


        var taskCreateRequest = new TaskCreateRequest(task.getTitle(), task.getDescription(), task.isCompleted());
        //act
        assertThrows(MessageNotSendedException.class, () -> service.create(taskCreateRequest));

        // verify
        verify(repository).save(task);
        verify(emailService).sendTaskCreatedEmail(anyString(), anyString(), anyString());
    }

    @Test
    void createTask_ok() throws MessageNotSendedException {
        // arrange
        when(repository.save(task))
                .thenReturn(task);

        doNothing()
                .when(emailService)
                .sendTaskCreatedEmail(anyString(), anyString(), anyString());

        var taskCreateRequest = new TaskCreateRequest(task.getTitle(), task.getDescription(), task.isCompleted());
        // act
        TaskCreateResponse saved = service.create(taskCreateRequest);

        // assert
        assertEquals(task.getDescription(), saved.description());
        assertEquals(task.getTitle(), saved.title());
    }

    @Test
    void returnAllTask_ok() {
        when(repository.findAll()).thenReturn(List.of(task));
        List<GetAllTaskResponse> tasks = service.getAll();
        assertThat(tasks).hasSize(1);
        assertEquals(task.getDescription() , tasks.getFirst().description());
    }

    @Test
    void getById_ok() {
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        GetByIdResponse found = service.getById(1L);
        assertEquals(task.getDescription(), found.description());
    }

    @Test
    void getById_ThrowNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void updateTask_ok() {
        // arrange
        UpdateTaskRequest updateTaskRequest = Instancio.create(UpdateTaskRequest.class);
        var taskById = Instancio.create(Task.class);
        when(repository.findById(updateTaskRequest.id())).thenReturn(Optional.of(taskById));

        when(repository.save(any(Task.class))).thenReturn(taskById);

        // act
        UpdateTaskResponse result = service.update(updateTaskRequest);

        // assert
        assertEquals(updateTaskRequest.description(), result.description());
        assertEquals(updateTaskRequest.title(), result.title());
        assertEquals(updateTaskRequest.id(), result.id());
    }

    @Test
    void updateTask_throwNotFoundException() {
        // arrange
        UpdateTaskRequest updateTaskRequest = Instancio.create(UpdateTaskRequest.class);
        when(repository.findById(updateTaskRequest.id())).thenReturn(Optional.empty());

        // act
        assertThrows(TaskNotFoundException.class,() -> service.update(updateTaskRequest));
    }

    @Test
    void shouldDeleteTask() {
        service.delete(1L);
        verify(repository).deleteById(1L);
    }
}