package com.example.todolist.repository.nosql;

import com.example.todolist.model.nosql.TodoItemEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemEventRepository extends MongoRepository<TodoItemEvent, Integer> {
}
