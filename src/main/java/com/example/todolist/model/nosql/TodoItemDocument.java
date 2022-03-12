package com.example.todolist.model.nosql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("todoitem")
public class TodoItemDocument {
    @Id
    private int id;

    private String name;
    private boolean done;
}
