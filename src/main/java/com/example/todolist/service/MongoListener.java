package com.example.todolist.service;

import com.example.todolist.model.nosql.TodoItemEvent;
import com.example.todolist.repository.analytics.AnalyticsRepository;
import com.example.todolist.repository.sql.TodoItemEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableMongoAuditing
public class MongoListener extends AbstractMongoEventListener<TodoItemEvent> {

    private final TodoItemEntityRepository todoItemEntityRepository;
    private final AnalyticsRepository analyticsRepository;

    @Override
    public void onAfterSave(AfterSaveEvent<TodoItemEvent> event) {
        todoItemEntityRepository.save(event.getSource().getDetails());
        analyticsRepository.save(event.getSource().getDetails());
    }
}