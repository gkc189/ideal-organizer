package com.example.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class TaskmanagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}
}

@RestController
@RequestMapping("/tasks")
class TaskController {

	private final Map<Integer, Task> taskStore = new ConcurrentHashMap<>();
	private final AtomicInteger idGenerator = new AtomicInteger(1);

	@PostMapping
	public Task create(@RequestBody TaskRequest request) {
		int id = idGenerator.getAndIncrement();
		Task task = new Task(id, request.getTitle(), request.getDescription());
		taskStore.put(id, task);
		return task;
	}

	@GetMapping("/{id}")
	public Task getTask(@PathVariable int id) {
		Task task = taskStore.get(id);
		if (task == null) {
			throw new NoSuchElementException("Task not found: " + id);
		}
		return task;
	}

	@GetMapping
	public List<Task> getAllTasks() {
		return new ArrayList<>(taskStore.values());
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable int id, @RequestBody TaskRequest request) {
		Task task = taskStore.get(id);
		if (task == null) {
			throw new NoSuchElementException("Task not found: " + id);
		}
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		return task;
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable int id) {
		if (!taskStore.containsKey(id)) {
			throw new NoSuchElementException("Task not found: " + id);
		}
		taskStore.remove(id);
	}

	public static class TaskRequest {
		private String title;
		private String description;

		public String getTitle() { return title; }
		public void setTitle(String title) { this.title = title; }

		public String getDescription() { return description; }
		public void setDescription(String description) { this.description = description; }
	}

	public static class Task {
		private int id;
		private String title;
		private String description;

		public Task(int id, String title, String description) {
			this.id = id;
			this.title = title;
			this.description = description;
		}

		public int getId() { return id; }
		public void setId(int id) { this.id = id; }

		public String getTitle() { return title; }
		public void setTitle(String title) { this.title = title; }

		public String getDescription() { return description; }
		public void setDescription(String description) { this.description = description; }
	}

		public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}
}
