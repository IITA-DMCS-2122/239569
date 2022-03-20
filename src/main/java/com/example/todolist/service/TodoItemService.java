package com.example.todolist.service;

import com.example.todolist.model.TodoItemDto;
import com.example.todolist.model.nosql.TodoItemDocument;
import com.example.todolist.model.sql.TodoItemEntity;
import com.example.todolist.repository.nosql.TodoItemDocumentRepository;
import com.example.todolist.repository.analytics.AnalyticsRepository;
import com.example.todolist.repository.sql.TodoItemEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoItemService {

    private final TodoItemEntityRepository todoItemEntityRepository;
    private final TodoItemDocumentRepository todoItemDocumentRepository;
    private final AnalyticsRepository analyticsRepository;

    public TodoItemDto findByUuid(UUID uuid) {
        return convertToDto(todoItemEntityRepository.findByUuid(uuid));
    }

    public List<TodoItemDto> findAllFromPostgre() {
        return todoItemEntityRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<TodoItemDto> findAllFromMongo() {
        return todoItemDocumentRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void saveItem(TodoItemEntity item) {
        todoItemEntityRepository.save(item);
        analyticsRepository.save(item);
        todoItemDocumentRepository.save(convertEntityToDocument(item));
    }

    @Transactional
    public List<TodoItemDto> search(String query) {
        return todoItemEntityRepository.search(query).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void updateItem(TodoItemEntity item) {
        TodoItemEntity itemFound = todoItemEntityRepository.findByUuid(item.getUuid());
        if (itemFound != null) {
            itemFound.setName(item.getName());
            itemFound.setDone(item.isDone());
            todoItemEntityRepository.save(itemFound);
            todoItemDocumentRepository.save(convertEntityToDocument(itemFound));
        }
    }

    public void deleteItem(UUID uuid) {
        todoItemEntityRepository.deleteByUuid(uuid);
        todoItemDocumentRepository.deleteByUuid(uuid);
    }

    private TodoItemDocument convertEntityToDocument(TodoItemEntity todoItemEntity) {
        return new TodoItemDocument(todoItemEntity.getId(), todoItemEntity.getUuid(), todoItemEntity.getName(), todoItemEntity.isDone());
    }

    private TodoItemDto convertToDto(TodoItemEntity todoItemEntity) {
        return new TodoItemDto(todoItemEntity.getUuid(), todoItemEntity.getName(), todoItemEntity.isDone());
    }

    private TodoItemDto convertToDto(TodoItemDocument todoItemDocument) {
        return new TodoItemDto(todoItemDocument.getUuid(), todoItemDocument.getName(), todoItemDocument.isDone());
    }
}
