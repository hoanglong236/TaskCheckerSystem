package org.swing.app.view.components.ui.label;

import org.swing.app.view.common.IconUrlConstants;

public class CompletionRateLabel extends Label {

    public CompletionRateLabel(int completedCount, int totalCount) {
        super(CompletionRateLabel.getCompletionRate(completedCount, totalCount));
        setIcon(IconUrlConstants.ICON_PLANNING);
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
