package com.example.todolist.repository.analytics;

import com.example.todolist.model.sql.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AnalyticsRepository extends JpaRepository<TodoItemEntity, Integer> {
}
