package com.example.todolist.controller;

import com.example.todolist.model.TodoItemDto;
import com.example.todolist.model.sql.TodoItemEntity;
import com.example.todolist.service.TodoItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TodoItemController {
    private final TodoItemService todoItemService;

    @GetMapping("/countEvents")
    public long countEvents() {
        return todoItemService.countEvents();
    }

    @GetMapping("/getItemsFromMongo")
    public List<TodoItemDto> getItemsFromMongo() {
        return todoItemService.findAllFromMongo();
    }

    @GetMapping("/getItemsFromPostgre")
    public List<TodoItemDto> getItemsFromPostgre() {
        return todoItemService.findAllFromPostgre();
    }

    @GetMapping("/get/{uuid}")
    public TodoItemDto getItemsFromPostgre(@PathVariable UUID uuid) {
        return todoItemService.findByUuid(uuid);
    }

    @GetMapping("/search/{name}")
    public List<TodoItemDto> search(@PathVariable String name) {
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

    @DeleteMapping("/delete/{uuid}")
    public void deleteCourse(@PathVariable UUID uuid) {
        todoItemService.deleteItem(uuid);
    }
}
