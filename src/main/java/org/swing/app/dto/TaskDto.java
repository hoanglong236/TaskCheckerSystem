package org.swing.app.dto;

import java.time.LocalDateTime;

public class TaskDto {

    private String id = null;
    private String parentId = null;
    private String title = null;
    private boolean important = false;
    private LocalDateTime startDateTime = null;
    private LocalDateTime finishDateTime = null;
    private LocalDateTime submitDateTime = null;
    private boolean completed = false;
    private String note = null;

    public TaskDto() {
    }

    public TaskDto(TaskDto taskDto) {
        this.id = taskDto.getId();
        this.parentId = taskDto.getParentId();
        this.title = taskDto.getTitle();
        this.important = taskDto.isImportant();
        this.startDateTime = taskDto.getStartDateTime();
        this.finishDateTime = taskDto.getFinishDateTime();
        this.submitDateTime = taskDto.getSubmitDateTime();
        this.completed = taskDto.isCompleted();
        this.note = taskDto.getNote();
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

    // TODO: handle equals method
}
