package org.swing.app.dto;

import java.time.LocalDateTime;

public class TaskPanelDto {

    private Integer id;
    private Integer parentId;
    private String title;
    private boolean important;
    private LocalDateTime startDateTime;
    private LocalDateTime finishDateTime;
    private LocalDateTime submitDateTime;
    private boolean cancelable;
    private boolean completed;
    private boolean onSchedule;

    private String note;

    private int childTaskCompletedCount;

    private int childTaskCount;

    public TaskPanelDto() {
        this.id = null;
        this.parentId = null;
        this.title = null;
        this.important = false;
        this.startDateTime = null;
        this.finishDateTime = null;
        this.submitDateTime = null;
        this.cancelable = false;
        this.completed = true;
        this.onSchedule = true;
        this.note = null;
        this.childTaskCompletedCount = 0;
        this.childTaskCount = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isOnSchedule() {
        return onSchedule;
    }

    public void setOnSchedule(boolean onSchedule) {
        this.onSchedule = onSchedule;
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
}
