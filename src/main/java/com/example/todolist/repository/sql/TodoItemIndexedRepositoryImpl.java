package com.example.todolist.repository.sql;

import com.example.todolist.model.sql.TodoItemEntity;
import org.hibernate.search.mapper.orm.Search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TodoItemIndexedRepositoryImpl implements TodoItemIndexedRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TodoItemEntity> search(String query) {
        return Search.session(entityManager)
                .search(TodoItemEntity.class)
                .where(f -> f.match()
                        .fields("name")
                        .matching(query)
                        .fuzzy())
                .fetchAllHits();
    }
}
