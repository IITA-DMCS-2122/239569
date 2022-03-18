package com.example.todolist.controller;

import com.example.todolist.model.nosql.TodoItemDocument;
import com.example.todolist.model.sql.TodoItemEntity;
import com.example.todolist.service.TodoItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TodoItemController {
    private final TodoItemService todoItemService;

    @GetMapping("/getItemsFromMongo")
    public List<TodoItemDocument> getItemsFromMongo() {
        return todoItemService.findAllFromMongo();
    }

    @GetMapping("/getItemsFromPostgre")
    public List<TodoItemEntity> getItemsFromPostgre() {
        return todoItemService.findAllFromPostgre();
    }

    @GetMapping("/get/{id}")
    public TodoItemEntity getItemsFromPostgre(@PathVariable int id) {
        return todoItemService.findById(id);
    }

    @GetMapping("/search/{name}")
    public List<TodoItemEntity> getItemsFromMongo(@PathVariable String name) {
        return todoItemService.search(name);
    }

    @PostMapping("/addItem")
    public void addItem(@RequestBody TodoItemEntity item) {
        todoItemService.saveItem(item);
    }

    @PutMapping("/updateItem")
    public void updateItem(@RequestBody TodoItemEntity item) {
        todoItemService.updateItem(item);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable int id) {
        todoItemService.deleteItem(id);
    }
}
