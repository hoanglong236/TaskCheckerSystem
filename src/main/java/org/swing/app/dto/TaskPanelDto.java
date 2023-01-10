package org.swing.app.dto;

import java.time.LocalDateTime;

public class TaskPanelDto {

    private TaskDto taskDto = null;
    private int childCompletedTaskCount = 0;
    private int childTaskCount = 0;

    private LocalDateTime createdAt = null;
    private LocalDateTime updatedAt = null;

    public TaskPanelDto() {
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
    }

    public int getChildCompletedTaskCount() {
        return childCompletedTaskCount;
    }

    public void setChildCompletedTaskCount(int childCompletedTaskCount) {
        this.childCompletedTaskCount = childCompletedTaskCount;
    }

    public int getChildTaskCount() {
        return childTaskCount;
    }

    public void setChildTaskCount(int childTaskCount) {
        this.childTaskCount = childTaskCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TaskPanelDto getCopy() {
        final TaskPanelDto copy = new TaskPanelDto();

        copy.setTaskDto(this.taskDto);
        copy.setChildCompletedTaskCount(this.childCompletedTaskCount);
        copy.setChildTaskCount(this.childTaskCount);
        copy.setCreatedAt(this.createdAt);
        copy.setUpdatedAt(this.updatedAt);

        return copy;
    }
}
