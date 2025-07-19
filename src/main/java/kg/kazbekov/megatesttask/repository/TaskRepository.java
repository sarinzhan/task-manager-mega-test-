package kg.kazbekov.megatesttask.repository;

import kg.kazbekov.megatesttask.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
