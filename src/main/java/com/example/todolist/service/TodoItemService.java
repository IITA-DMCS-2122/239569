package com.example.todolist.service;

import com.example.todolist.model.nosql.TodoItemDocument;
import com.example.todolist.model.sql.TodoItemEntity;
import com.example.todolist.repository.nosql.TodoItemDocumentRepository;
import com.example.todolist.repository.analytics.AnalyticsRepository;
import com.example.todolist.repository.sql.TodoItemEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoItemService {

    private final TodoItemEntityRepository todoItemEntityRepository;
    private final TodoItemDocumentRepository todoItemDocumentRepository;
    private final AnalyticsRepository analyticsRepository;

    public TodoItemEntity findById(int id) {
        return todoItemEntityRepository.findById(id).get();
    }

    public List<TodoItemEntity> findAllFromPostgre() {
        return todoItemEntityRepository.findAll();
    }

    public List<TodoItemDocument> findAllFromMongo() {
        return todoItemDocumentRepository.findAll();
    }

    public void saveItem(TodoItemEntity item) {
        todoItemEntityRepository.save(item);
        analyticsRepository.save(item);
        todoItemDocumentRepository.save(convertEntityToDocument(item));
    }

    @Transactional
    public List<TodoItemEntity> search(String query) {
        return todoItemEntityRepository.search(query);
    }

    public void updateItem(TodoItemEntity item) {
        Optional<TodoItemEntity> itemFound = todoItemEntityRepository.findById(item.getId());
        if (itemFound.isPresent()) {
            itemFound.get().setName(item.getName());
            itemFound.get().setDone(item.isDone());
            todoItemEntityRepository.save(itemFound.get());
            todoItemDocumentRepository.save(convertEntityToDocument(itemFound.get()));
        }
    }

    public void deleteItem(int id) {
        todoItemEntityRepository.deleteById(id);
        todoItemDocumentRepository.deleteById(id);
    }

    private TodoItemDocument convertEntityToDocument(TodoItemEntity todoItemEntity) {
        return new TodoItemDocument(todoItemEntity.getId(), todoItemEntity.getName(), todoItemEntity.isDone());
    }
}
