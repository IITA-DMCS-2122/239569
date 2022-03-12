package com.example.todolist.repository.nosql;

import com.example.todolist.model.nosql.TodoItemDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemDocumentRepository extends MongoRepository<TodoItemDocument, Integer> {
}
