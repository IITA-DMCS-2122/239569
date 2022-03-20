package com.example.todolist.repository.sql;

import com.example.todolist.model.sql.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface TodoItemEntityRepository extends JpaRepository<TodoItemEntity, Integer>, TodoItemIndexedRepository {
    TodoItemEntity findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}