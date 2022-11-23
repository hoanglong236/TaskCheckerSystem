package org.swing.app.view.components.form.components.factory.impl.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.components.form.components.InputAndLabelWrapper;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.factory.InputAndLabelWrapperFactory;
import org.swing.app.view.components.form.components.factory.impl.wrapper.InputAndLabelWrapperFactoryImpl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

class DateTimeChooser extends WrapperComponent implements InputComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.MEDIUM_H_GAP, ViewConstant.SMALL_V_GAP);
    private static final InputAndLabelWrapperFactory INPUT_AND_LABEL_WRAPPER_FACTORY =
            new InputAndLabelWrapperFactoryImpl();

    private DateChooser dateChooser;
    private InputAndLabelWrapper hourSelector;
    private InputAndLabelWrapper minuteSelector;
    private InputAndLabelWrapper secondSelector;

    private int dateChooserWidth;

    public DateTimeChooser(LocalDateTime initValue) {
        super();
        setLayout(MAIN_LAYOUT);
        init(initValue);
        this.dateChooserWidth = ViewConstant.DEFAULT_DATE_CHOOSER_WIDTH;
    }

    public void setDateChooserWidth(int dateChooserWidth) {
        this.dateChooserWidth = dateChooserWidth;
    }

    private void initDateChooser(LocalDate date) {
        this.dateChooser = new DateChooser(date);
    }

    private void initHourSelector(int hour) {
        final String hourSelectorLabelText = "h: ";
        final Set<String> hourValueRange = new LinkedHashSet<>();
        for (byte h = 0; h < 24; h++) {
            hourValueRange.add(String.valueOf(h));
        }
        this.hourSelector = INPUT_AND_LABEL_WRAPPER_FACTORY.createComboBoxAndLabelWrapper(hourSelectorLabelText,
                hourValueRange, String.valueOf(hour));
    }

    private void initMinuteSelector(int minute) {
        final String minuteSelectorLabelText = "m: ";
        final Set<String> minuteValueRange = new LinkedHashSet<>();
        for (byte h = 0; h < 60; h++) {
            minuteValueRange.add(String.valueOf(h));
        }
        this.minuteSelector = INPUT_AND_LABEL_WRAPPER_FACTORY.createComboBoxAndLabelWrapper(minuteSelectorLabelText,
                minuteValueRange, String.valueOf(minute));
    }

    private void initSecondSelector(int second) {
        final String secondSelectorLabelText = "m: ";
        final Set<String> secondValueRange = new LinkedHashSet<>();
        for (byte h = 0; h < 60; h++) {
            secondValueRange.add(String.valueOf(h));
        }
        this.secondSelector = INPUT_AND_LABEL_WRAPPER_FACTORY.createComboBoxAndLabelWrapper(secondSelectorLabelText,
                secondValueRange, String.valueOf(second));
    }

    private void init(LocalDateTime dateTime) {
        initDateChooser(dateTime.toLocalDate());
        addChildComponent(this.dateChooser);

        initHourSelector(dateTime.getHour());
        addChildComponent(this.hourSelector);

        initMinuteSelector(dateTime.getMinute());
        addChildComponent(this.minuteSelector);

        initSecondSelector(dateTime.getSecond());
        addChildComponent(this.secondSelector);
    }

    private void update(LocalDateTime dateTime) {
        this.dateChooser.setValue(dateTime.toLocalDate());
        this.hourSelector.setValue(String.valueOf(dateTime.getHour()));
        this.minuteSelector.setValue(String.valueOf(dateTime.getMinute()));
        this.secondSelector.setValue(String.valueOf(dateTime.getSecond()));
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        this.childComponentSizeMap.put(this.dateChooser,
                new Dimension(this.dateChooserWidth, maxChildComponentHeight));

        final byte hourSelectorWidth = 50;
        this.childComponentSizeMap.put(this.hourSelector, new Dimension(hourSelectorWidth, maxChildComponentHeight));

        final byte minuteSelectorWidth = 50;
        this.childComponentSizeMap.put(this.minuteSelector,
                new Dimension(minuteSelectorWidth, maxChildComponentHeight));

        final byte secondSelectorWidth = 50;
        this.childComponentSizeMap.put(this.secondSelector,
                new Dimension(secondSelectorWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        if (!(value instanceof LocalDateTime)) {
            throw new IllegalArgumentException();
        }
        update((LocalDateTime) value);
    }

    @Override
    public Object getValue() {
        final LocalDate date = (LocalDate) this.dateChooser.getValue();

        final byte hour = Byte.parseByte((String) this.hourSelector.getValue());
        final byte minute = Byte.parseByte((String) this.minuteSelector.getValue());
        final byte second = Byte.parseByte((String) this.secondSelector.getValue());
        final LocalTime time = LocalTime.of(hour, minute, second);

        return LocalDateTime.of(date, time);
    }

    @Override
    public void clear() {
        this.dateChooser.clear();
        this.hourSelector.clear();
        this.minuteSelector.clear();
        this.secondSelector.clear();
    }
}