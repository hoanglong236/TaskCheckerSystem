package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.form.components.input.InputComponent;
import org.swing.app.view.components.form.components.input.factory.InputComponentFactory;

import java.awt.Dimension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class DateTimeChooserWrapper extends InputComponentWrapperBase<LocalDateTime> {

    private InputComponent<LocalDate> dateChooser;
    private SimpleComponent hourChooserLabel;
    private InputComponent<String> hourChooser;
    private SimpleComponent minuteChooserLabel;
    private InputComponent<String> minuteChooser;

    public DateTimeChooserWrapper(String labelText) {
        super();
        init(labelText);
    }

    private void initDateChooser() {
        this.dateChooser = InputComponentFactory.createDateChooser();
        this.dateChooser.setFont(DEFAULT_INPUT_FIELD_FONT);
    }

    private void initHourChooserLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.hourChooserLabel = UIComponentFactory.createLabel(messageLoader.getMessage("hour.chooser.label"));
        this.hourChooserLabel.setFont(DEFAULT_LABEL_FIELD_FONT);
    }

    private void initHourChooser() {
        final Set<String> hourValueRange = new LinkedHashSet<>();
        for (byte h = 0; h < 24; h++) {
            hourValueRange.add(String.valueOf(h));
        }
        this.hourChooser = InputComponentFactory.createComboBox(hourValueRange);
        this.hourChooser.setFont(DEFAULT_INPUT_FIELD_FONT);
    }

    private void initMinuteChooserLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.minuteChooserLabel = UIComponentFactory.createLabel(messageLoader.getMessage("minute.chooser.label"));
        this.minuteChooserLabel.setFont(DEFAULT_LABEL_FIELD_FONT);
    }

    private void initMinuteChooser() {
        final Set<String> minuteValueRange = new LinkedHashSet<>();
        for (byte m = 0; m < 60; m++) {
            minuteValueRange.add(String.valueOf(m));
        }
        this.minuteChooser = InputComponentFactory.createComboBox(minuteValueRange);
        this.minuteChooser.setFont(DEFAULT_INPUT_FIELD_FONT);
    }

    private void init(String labelText) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initDateChooser();
        addChildComponent(this.dateChooser);

        initHourChooserLabel();
        addChildComponent(this.hourChooserLabel);

        initHourChooser();
        addChildComponent(this.hourChooser);

        initMinuteChooserLabel();
        addChildComponent(this.minuteChooserLabel);

        initMinuteChooser();
        addChildComponent(this.minuteChooser);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableHeight = getSize().height - ReserveSizeConstants.LARGE_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        if (this.rateOfLabelFieldWidthInTotal > 0) {
            this.labelFieldWidth = (int) (this.rateOfLabelFieldWidthInTotal * availableHeight);
        }
        this.childComponentSizeMap.put(this.labelField, new Dimension(this.labelFieldWidth, maxChildComponentHeight));

        final byte dateChooserWidth = ComponentSizeConstants.DEFAULT_DATE_CHOOSER_WIDTH;
        this.childComponentSizeMap.put(this.dateChooser,
                new Dimension(dateChooserWidth, maxChildComponentHeight));

        final byte timeChooserLabelWidth = 20;
        this.childComponentSizeMap.put(this.hourChooserLabel,
                new Dimension(timeChooserLabelWidth, maxChildComponentHeight));
        this.childComponentSizeMap.put(this.minuteChooserLabel,
                new Dimension(timeChooserLabelWidth, maxChildComponentHeight));

        final byte timeChooserWidth = 45;
        this.childComponentSizeMap.put(this.hourChooser,
                new Dimension(timeChooserWidth, maxChildComponentHeight));
        this.childComponentSizeMap.put(this.minuteChooser,
                new Dimension(timeChooserWidth, maxChildComponentHeight));
    }

    @Override
    public void setValue(LocalDateTime value) {
        if (value == null) {
            clear();
            return;
        }
        this.dateChooser.setValue(value.toLocalDate());
        this.hourChooser.setValue(String.valueOf(value.getHour()));
        this.minuteChooser.setValue(String.valueOf(value.getMinute()));
    }

    @Override
    public LocalDateTime getValue() {
        final LocalDate dateChooserValue = this.dateChooser.getValue();
        if (dateChooserValue == null) {
            return null;
        }

        final String hourChooserValue = this.hourChooser.getValue();
        final byte hour = Byte.parseByte(hourChooserValue);

        final String minuteChooserValue = this.minuteChooser.getValue();
        final byte minute = Byte.parseByte(minuteChooserValue);

        final LocalTime timeInputValue = LocalTime.of(hour, minute);
        return LocalDateTime.of(dateChooserValue, timeInputValue);
    }

    @Override
    public void clear() {
        this.dateChooser.clear();
        this.hourChooser.clear();
        this.minuteChooser.clear();
    }
}
