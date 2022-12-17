package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.LabelAndInputWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;
import org.swing.app.view.components.form.components.factory.LabelAndInputWrapperFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class DateTimeChooser extends PanelWrapperComponent implements InputComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.MEDIUM_H_GAP, ViewConstant.SMALL_V_GAP);
    private InputComponent dateChooser;
    private LabelAndInputWrapper hourSelector;
    private LabelAndInputWrapper minuteSelector;
    private LabelAndInputWrapper secondSelector;

    private int dateChooserWidth = ViewConstant.DEFAULT_DATE_CHOOSER_WIDTH;

    public DateTimeChooser(LocalDateTime initValue) {
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
        this.hourSelector = LabelAndInputWrapperFactory.createLabelAndComboBoxWrapper(
                hourSelectorLabelText, hourValueRange);
    }

    private void initMinuteSelector() {
        final String minuteSelectorLabelText = "m: ";
        final Set<String> minuteValueRange = new LinkedHashSet<>();
        for (byte h = 0; h < 60; h++) {
            minuteValueRange.add(String.valueOf(h));
        }
        this.minuteSelector = LabelAndInputWrapperFactory.createLabelAndComboBoxWrapper(
                minuteSelectorLabelText, minuteValueRange);
    }

    private void initSecondSelector() {
        final String secondSelectorLabelText = "m: ";
        final Set<String> secondValueRange = new LinkedHashSet<>();
        for (byte h = 0; h < 60; h++) {
            secondValueRange.add(String.valueOf(h));
        }
        this.secondSelector = LabelAndInputWrapperFactory.createLabelAndComboBoxWrapper(
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