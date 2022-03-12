package com.example.todolist.repository.sql;

import com.example.todolist.model.sql.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemEntityRepository extends JpaRepository<TodoItemEntity, Integer> {
}