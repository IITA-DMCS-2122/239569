package com.example.todolist.repository.nosql;

import com.example.todolist.model.search.TodoItemSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SearchRepository extends ElasticsearchRepository<TodoItemSearch, String> {
    List<TodoItemSearch> findByName(String name);
}