package org.swing.app.dto;

import java.time.LocalDateTime;

public class TaskDto {

    private String id = null;
    private String parentId = null;
    private String title = null;
    private boolean important = false;
    private LocalDateTime deadline = null;
    private LocalDateTime submitDateTime = null;
    private boolean completed = false;
    private String note = null;

    private LocalDateTime createdAt = null;

    private LocalDateTime updatedAt = null;

    public TaskDto() {
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

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
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

    public TaskDto getCopy() {
        final TaskDto copy = new TaskDto();

        copy.setId(this.id);
        copy.setParentId(this.parentId);
        copy.setTitle(this.title);
        copy.setImportant(this.important);
        copy.setDeadline(this.deadline);
        copy.setSubmitDateTime(this.submitDateTime);
        copy.setCompleted(this.completed);
        copy.setNote(this.note);
        copy.setCreatedAt(this.createdAt);
        copy.setUpdatedAt(this.updatedAt);

        return copy;
    }
}
