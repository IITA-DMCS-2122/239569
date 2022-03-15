package com.example.todolist.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "todoitem")
public class TodoItemSearch {
    @Id
    private int id;

    private String name;
    private boolean done;
}
