package org.swing.app.dto;

import jdk.vm.ci.meta.Local;

import java.time.LocalDateTime;

public class TaskPanelDto {

    private String id;
    private String parentId;
    private String title;
    private boolean important;
    private LocalDateTime startDateTime;
    private LocalDateTime finishDateTime;
    private LocalDateTime submitDateTime;
    private boolean completed;
    private String note;
    private int childTaskCompletedCount;
    private int childTaskCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TaskPanelDto() {
        this.id = null;
        this.parentId = null;
        this.title = null;
        this.important = false;
        this.startDateTime = null;
        this.finishDateTime = null;
        this.submitDateTime = null;
        this.completed = false;
        this.note = null;
        this.childTaskCompletedCount = 0;
        this.childTaskCount = 0;
    }

    public TaskPanelDto(TaskDto taskDto) {
        this.id = taskDto.getId();
        this.parentId = taskDto.getParentId();
        this.title = taskDto.getTitle();
        this.important = taskDto.isImportant();
        this.startDateTime = taskDto.getStartDateTime();
        this.finishDateTime = taskDto.getFinishDateTime();
        this.submitDateTime = taskDto.getSubmitDateTime();
        this.completed = taskDto.isCompleted();
        this.note = taskDto.getNote();
        this.childTaskCompletedCount = 0;
        this.childTaskCount = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(LocalDateTime finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public LocalDateTime getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(LocalDateTime submitDateTime) {
        this.submitDateTime = submitDateTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getChildTaskCompletedCount() {
        return childTaskCompletedCount;
    }

    public void setChildTaskCompletedCount(int childTaskCompletedCount) {
        this.childTaskCompletedCount = childTaskCompletedCount;
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
}
