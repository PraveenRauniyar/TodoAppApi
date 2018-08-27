
package com.tw.todoApp.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<TodoItem, Long> {
}