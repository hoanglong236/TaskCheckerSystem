package org.swing.app.dto;

public class TaskPanelDto {

    private TaskDto taskDto = null;
    private int completedChildTaskCount = 0;
    private int childTaskCount = 0;

    public TaskPanelDto() {
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
    }

    public int getCompletedChildTaskCount() {
        return completedChildTaskCount;
    }

    public void setCompletedChildTaskCount(int completedChildTaskCount) {
        this.completedChildTaskCount = completedChildTaskCount;
    }

    public int getChildTaskCount() {
        return childTaskCount;
    }

    public void setChildTaskCount(int childTaskCount) {
        this.childTaskCount = childTaskCount;
    }

    public TaskPanelDto getCopy() {
        final TaskPanelDto copy = new TaskPanelDto();

        copy.setTaskDto(this.taskDto);
        copy.setCompletedChildTaskCount(this.completedChildTaskCount);
        copy.setChildTaskCount(this.childTaskCount);

        return copy;
    }
}
