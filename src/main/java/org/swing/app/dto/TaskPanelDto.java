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

    public String getId() {
        return this.taskDto.getId();
    }

    public String getParentId() {
        return this.taskDto.getParentId();
    }

    public String getTitle() {
        return this.taskDto.getTitle();
    }

    public boolean isImportant() {
        return this.taskDto.isImportant();
    }

    public LocalDateTime getStartDateTime() {
        return this.taskDto.getStartDateTime();
    }

    public LocalDateTime getFinishDateTime() {
        return this.taskDto.getFinishDateTime();
    }

    public LocalDateTime getSubmitDateTime() {
        return this.taskDto.getSubmitDateTime();
    }

    public boolean isCompleted() {
        return this.taskDto.isCompleted();
    }

    public String getNote() {
        return this.taskDto.getNote();
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
