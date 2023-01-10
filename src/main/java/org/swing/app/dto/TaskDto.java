package org.swing.app.dto;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskDto) {
            final TaskDto taskDtoInstance = (TaskDto) obj;

            final boolean idCompare = Objects.equals(taskDtoInstance.id, this.id);
            if (!idCompare) {
                return false;
            }
            final boolean parentIdCompare = Objects.equals(taskDtoInstance.parentId, this.parentId);
            if (!parentIdCompare) {
                return false;
            }
            final boolean titleCompare = Objects.equals(taskDtoInstance.title, this.title);
            if (!titleCompare) {
                return false;
            }
            final boolean importantCompare = taskDtoInstance.important == this.important;
            if (!importantCompare) {
                return false;
            }
            final boolean startDateTimeCompare = Objects.equals(taskDtoInstance.startDateTime, this.startDateTime);
            if (!startDateTimeCompare) {
                return false;
            }
            final boolean finishDateTimeCompare = Objects.equals(taskDtoInstance.finishDateTime, this.finishDateTime);
            if (!finishDateTimeCompare) {
                return false;
            }
            final boolean submitDateTimeCompare = Objects.equals(taskDtoInstance.submitDateTime, this.submitDateTime);
            if (!submitDateTimeCompare) {
                return false;
            }
            final boolean completedCompare = taskDtoInstance.completed == this.completed;
            if (!completedCompare) {
                return false;
            }
            final boolean noteCompare = Objects.equals(taskDtoInstance.note, this.note);
            if (!noteCompare) {
                return false;
            }

            return true;
        }
        return false;
    }

    public TaskDto getCopy() {
        final TaskDto copy = new TaskDto();

        copy.setId(this.id);
        copy.setParentId(this.parentId);
        copy.setTitle(this.title);
        copy.setImportant(this.important);
        copy.setStartDateTime(this.startDateTime);
        copy.setFinishDateTime(this.finishDateTime);
        copy.setSubmitDateTime(this.submitDateTime);
        copy.setCompleted(this.completed);
        copy.setNote(this.note);

        return copy;
    }
}
