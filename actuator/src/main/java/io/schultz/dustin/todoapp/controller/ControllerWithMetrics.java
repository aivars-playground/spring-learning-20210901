package io.schultz.dustin.todoapp.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.schultz.dustin.todoapp.dto.TodoListDto;
import io.schultz.dustin.todoapp.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/metered")
public class ControllerWithMetrics {

    private TodoService todoService;
    private Timer timer;

    @Inject
    public ControllerWithMetrics(
            TodoService todoService,
            MeterRegistry registry
    ) {
        this.todoService = todoService;
        timer = registry.timer("my.custom.timer", "customtag1", "customtag2");
    }

    @GetMapping("/lists")
    public List<TodoListDto> getTodoLists() {
        return timer.record(() -> todoService.getTodoLists());
    }
}
