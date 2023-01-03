package org.swing.app.view.common;

import java.awt.Color;
import java.awt.Dimension;

public class ViewConstant {

    public static final byte FRAME_RESERVE_WIDTH = 20;

    // include navbar that contains minimum, maximum, close button.
    public static final byte FRAME_RESERVE_HEIGHT = 50;

    public static final byte FORM_WRAPPER_RESERVE_WIDTH = 50;
    public static final byte FORM_WRAPPER_RESERVE_HEIGHT = 65;

    public static final byte SCROLL_RESERVE_WIDTH = 16;
    public static final byte VERTICAL_SCROLLBAR_WIDTH = 12;
    public static final byte LARGE_RESERVE_WIDTH = 12;
    public static final byte LARGE_RESERVE_HEIGHT = 12;
    public static final byte MEDIUM_RESERVE_WIDTH = 9;
    public static final byte MEDIUM_RESERVE_HEIGHT = 9;
    public static final byte SMALL_RESERVE_WIDTH = 6;
    public static final byte SMALL_RESERVE_HEIGHT = 6;

    public static final byte SMALL_H_GAP = 6;
    public static final byte SMALL_V_GAP = 6;
    public static final byte MEDIUM_H_GAP = 9;
    public static final byte MEDIUM_V_GAP = 9;
    public static final byte LARGE_H_GAP = 12;
    public static final byte LARGE_V_GAP = 12;
    public static final byte VERY_LARGE_H_GAP = 15;
    public static final byte VERY_LARGE_V_GAP = 15;
    public static final byte FORM_WRAPPER_H_GAP = 30;
    public static final byte FORM_WRAPPER_V_GAP = 20;

    public static final String ICON_LOCATION_CHECK = "icons/check.png";
    public static final String ICON_LOCATION_DEADLINE = "icons/deadline.png";
    public static final String ICON_LOCATION_IMPORTANT = "icons/important.png";
    public static final String ICON_LOCATION_UNIMPORTANT = "icons/unimportant.png";
    public static final String ICON_LOCATION_NEW_LIST = "icons/newlist.png";
    public static final String ICON_LOCATION_NEW_TASK = "icons/newtask.png";
    public static final String ICON_LOCATION_PLANNING = "icons/planning.png";
    public static final String ICON_LOCATION_NOTE = "icons/note.png";
    public static final String ICON_LOCATION_MORE_ACTIONS = "icons/more.png";
    public static final String ICON_LOCATION_FILTER = "icons/filter.png";
    public static final String ICON_LOCATION_REMOVE = "icons/remove.png";

    public static final Color PRIMARY_BACKGROUND_COLOR = new Color(242, 242, 242);
    public static final String PRIMARY_FONT_NAME = "Segoe UI";
    public static final byte PRIMARY_FONT_SIZE = 15;

    public static final Dimension HOME_FRAME_PREFER_SIZE = new Dimension(1140, 650);
    public static final Dimension ROOT_TASK_FORM_MODAL_PREFER_SIZE = new Dimension(600, 420);
    public static final Dimension NODE_TASK_FORM_MODAL_PREFER_SIZE = new Dimension(600, 600);
    public static final Dimension LEAF_TASK_FORM_MODAL_PREFER_SIZE = new Dimension(600, 300);
    public static final int SIDEBAR_WIDTH = 330;
    public static final Color ACTIVATION_COLOR = new Color(230, 230, 230);
    public static final byte ROOT_TASK_PANEL_HEIGHT = 60;
    public static final byte NODE_TASK_PANEL_HEIGHT = 40;
    public static final byte LEAF_TASK_PANEL_HEIGHT = 40;

    public static final byte NOTIFY_LABEL_WIDTH = 60;
    public static final byte NOTIFY_LABEL_HEIGHT = 40;

    public static final byte LABEL_FIELD_FONT_SIZE = 13;
    public static final byte INPUT_FIELD_FONT_SIZE = 13;

    public static final byte DEFAULT_LABEL_WIDTH = 100;
    public static final byte DEFAULT_DATE_CHOOSER_WIDTH = 100;

    public static final String DEFAULT_DATE_CHOOSER_FORMAT = "dd/MM/yyyy";
}
