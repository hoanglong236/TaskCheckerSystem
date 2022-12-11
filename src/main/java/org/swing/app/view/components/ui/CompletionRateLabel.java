package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;

public class CompletionRateLabel extends Label {

    public CompletionRateLabel(int completedCount, int totalCount) {
        super(ViewConstant.ICON_LOCATION_PLANNING, CompletionRateLabel.getCompletionRate(completedCount, totalCount));
    }

    public void update(int completedCount, int totalCount) {
        setText(getCompletionRate(completedCount, totalCount));
    }

    private static String getCompletionRate(int completedCount, int totalCount) {
        final StringBuilder completionRate = new StringBuilder();

        completionRate.append(completedCount);
        completionRate.append("/");
        completionRate.append(totalCount);

        return completionRate.toString();
    }
}
