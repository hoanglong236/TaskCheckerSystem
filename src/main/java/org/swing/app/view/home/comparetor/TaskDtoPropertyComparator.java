package org.swing.app.view.home.comparetor;

import org.swing.app.dto.TaskDto;

import java.time.LocalDateTime;

public class TaskDtoPropertyComparator {

    public static int compareId(TaskDto o1, TaskDto o2) {
        final String taskId1 = o1.getId();
        final String taskId2 = o2.getId();

        return taskId1.compareTo(taskId2);
    }

    /**
     * If both values are null, they will be considered equal,
     * otherwise if either value is null, then the null value will be considered greater.
     *
     * @param o1
     * @param o2
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to,
     * or greater than the second.
     */
    private static int compareDateTime(LocalDateTime o1, LocalDateTime o2) {
        final byte lessThan = -1;
        final byte equal = 0;
        final byte moreThan = 1;

        if (o1 == null && o2 == null) {
            return equal;
        }
        if (o1 == null) {
            return moreThan;
        }
        if (o2 == null) {
            return lessThan;
        }

        if (o1.isBefore(o2)) {
            return lessThan;
        }
        if (o1.isAfter(o2)) {
            return moreThan;
        }
        return equal;
    }

    public static int compareCreateDate(TaskDto o1, TaskDto o2) {
        final LocalDateTime dateTime1 = o1.getCreatedAt();
        final LocalDateTime dateTime2 = o2.getCreatedAt();

        return compareDateTime(dateTime1, dateTime2);
    }

    public static int compareUpdateDate(TaskDto o1, TaskDto o2) {
        final LocalDateTime dateTime1 = o1.getUpdatedAt();
        final LocalDateTime dateTime2 = o2.getUpdatedAt();

        return compareDateTime(dateTime1, dateTime2);
    }
}
