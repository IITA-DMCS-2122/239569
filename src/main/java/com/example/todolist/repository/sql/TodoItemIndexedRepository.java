package com.example.todolist.repository.sql;

import com.example.todolist.model.sql.TodoItemEntity;

import java.util.List;

public interface TodoItemIndexedRepository {
    List<TodoItemEntity> search(String query);
}