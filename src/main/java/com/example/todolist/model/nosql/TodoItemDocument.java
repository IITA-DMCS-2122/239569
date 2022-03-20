package com.example.todolist.model.nosql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("todoitem")
public class TodoItemDocument {
    @Id
    private int id;

    private UUID uuid;

    private String name;
    private boolean done;

    @PrePersist
    protected void onCreate() {
        setUuid(java.util.UUID.randomUUID());
    }
}
