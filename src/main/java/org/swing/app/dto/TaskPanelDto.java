package org.swing.app.dto;

import java.time.LocalDateTime;

public class TaskPanelDto {

    private Integer id;
    private Integer parentId;
    private String title;
    private boolean important;
    private LocalDateTime startDatetime;
    private LocalDateTime finishDatetime;
    private LocalDateTime submitDatetime;
    private boolean completed;
    private String note;
    private int childTaskCompletedCount;
    private int childTaskCount;

    public TaskPanelDto() {
        this.id = null;
        this.parentId = null;
        this.title = null;
        this.important = false;
        this.startDatetime = null;
        this.finishDatetime = null;
        this.submitDatetime = null;
        this.completed = true;
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

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public LocalDateTime getFinishDatetime() {
        return finishDatetime;
    }

    public void setFinishDatetime(LocalDateTime finishDatetime) {
        this.finishDatetime = finishDatetime;
    }

    public LocalDateTime getSubmitDatetime() {
        return submitDatetime;
    }

    public void setSubmitDatetime(LocalDateTime submitDatetime) {
        this.submitDatetime = submitDatetime;
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
}
