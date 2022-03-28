package com.example.todolist.model.nosql;

import com.example.todolist.model.sql.TodoItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("todoitem_event")
public class TodoItemEvent {
    @Id
    private int id;
    private TodoItemEntity details;
    private String event;
    private String status;
}
