package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;
import org.swing.app.view.components.form.components.factory.InputComponentWrapperFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class DatetimeChooser extends PanelWrapperComponent implements InputComponent<LocalDateTime> {

    private static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private InputComponent<LocalDate> dateChooser;
    private InputComponentWrapper<String> hourSelector;
    private InputComponentWrapper<String> minuteSelector;
    private InputComponentWrapper<String> secondSelector;

    private int dateChooserWidth = ViewConstant.DEFAULT_DATE_CHOOSER_WIDTH;

    public DatetimeChooser(LocalDateTime initValue) {
        super();
        setLayout(MAIN_LAYOUT);
        init();
        setValue(initValue);
    }

    public void setDateChooserWidth(int dateChooserWidth) {
        this.dateChooserWidth = dateChooserWidth;
    }

    private void initDateChooser() {
        this.dateChooser = InputComponentFactory.createDateChooser();
    }

    private void initHourSelector() {
        final String hourSelectorLabelText = "h: ";
        final Set<String> hourValueRange = new LinkedHashSet<>();
        for (byte h = 0; h < 24; h++) {
            hourValueRange.add(String.valueOf(h));
        }
        this.hourSelector = InputComponentWrapperFactory.createComboBoxWrapper(
                hourSelectorLabelText, hourValueRange);
    }

    private void initMinuteSelector() {
        final String minuteSelectorLabelText = "m: ";
        final Set<String> minuteValueRange = new LinkedHashSet<>();
        for (byte m = 0; m < 60; m++) {
            minuteValueRange.add(String.valueOf(m));
        }
        this.minuteSelector = InputComponentWrapperFactory.createComboBoxWrapper(
                minuteSelectorLabelText, minuteValueRange);
    }

    private void initSecondSelector() {
        final String secondSelectorLabelText = "m: ";
        final Set<String> secondValueRange = new LinkedHashSet<>();
        for (byte s = 0; s < 60; s++) {
            secondValueRange.add(String.valueOf(s));
        }
        this.secondSelector = InputComponentWrapperFactory.createComboBoxWrapper(
                secondSelectorLabelText, secondValueRange);
    }

    private void init() {
        initDateChooser();
        addChildComponent(this.dateChooser);

        initHourSelector();
        addChildComponent(this.hourSelector);

        initMinuteSelector();
        addChildComponent(this.minuteSelector);

        initSecondSelector();
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
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

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
    public void setValue(LocalDateTime value) {
        if (value == null) {
            clear();
            return;
        }
        update(value);
    }

    @Override
    public Optional<LocalDateTime> getValue() {
        final Optional<LocalDate> optionalLocalDateInputValue = this.dateChooser.getValue();
        if (!optionalLocalDateInputValue.isPresent()) {
            return Optional.empty();
        }

        final String defaultTimeValue = "0";

        final Optional<String> optionalHourInputValue = this.hourSelector.getValue();
        final byte hour = Byte.parseByte(optionalHourInputValue.orElse(defaultTimeValue));

        final Optional<String> optionalMinuteInputValue = this.minuteSelector.getValue();
        final byte minute = Byte.parseByte(optionalMinuteInputValue.orElse(defaultTimeValue));

        final Optional<String> optionalSecondInputValue = this.secondSelector.getValue();
        final byte second = Byte.parseByte(optionalSecondInputValue.orElse(defaultTimeValue));

        final LocalTime timeInputValue = LocalTime.of(hour, minute, second);
        final LocalDateTime dateTime = LocalDateTime.of(optionalLocalDateInputValue.get(), timeInputValue);
        return Optional.of(dateTime);
    }


    @Override
    public void clear() {
        this.dateChooser.clear();
        this.hourSelector.clear();
        this.minuteSelector.clear();
        this.secondSelector.clear();
    }
}