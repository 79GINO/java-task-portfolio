package com.example.taskportfolio.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.taskportfolio.service.TaskService;

@Component
public class TaskBatch {
    private static final Logger logger = LoggerFactory.getLogger(TaskBatch.class);
    private final TaskService taskService;

    public TaskBatch(TaskService taskService) {
        this.taskService = taskService;
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void run() {

        try {
            int updatedCount = taskService.updateOverdueTasks();
            logger.info("期限切れ更新: {}件", updatedCount);

        } catch (RuntimeException e) {
            logger.error("期限切れ更新に失敗しました。", e);

        }

    }

}
