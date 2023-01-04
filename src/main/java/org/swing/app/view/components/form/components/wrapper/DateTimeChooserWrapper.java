package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.InputComponentWrapperBase;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

import java.awt.Dimension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class DateTimeChooserWrapper extends InputComponentWrapperBase<LocalDateTime> {

    private InputComponent<LocalDate> dateChooser;
    private SimpleComponent hourChooserLabel;
    private InputComponent<String> hourChooser;
    private SimpleComponent minuteChooserLabel;
    private InputComponent<String> minuteChooser;
    private SimpleComponent secondChooserLabel;
    private InputComponent<String> secondChooser;

    private int dateChooserWidth = ViewConstant.DEFAULT_DATE_CHOOSER_WIDTH;

    public DateTimeChooserWrapper(String labelText, LocalDateTime initValue) {
        super();
        init(labelText);
        setValue(initValue);
    }

    public void setDateChooserWidth(int dateChooserWidth) {
        this.dateChooserWidth = dateChooserWidth;
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

    private void initSecondChooserLabel() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.secondChooserLabel = UIComponentFactory.createLabel(messageLoader.getMessage("second.chooser.label"));
        this.secondChooserLabel.setFont(DEFAULT_LABEL_FIELD_FONT);
    }

    private void initSecondChooser() {
        final Set<String> secondValueRange = new LinkedHashSet<>();
        for (byte s = 0; s < 60; s++) {
            secondValueRange.add(String.valueOf(s));
        }
        this.secondChooser = InputComponentFactory.createComboBox(secondValueRange);
        this.secondChooser.setFont(DEFAULT_INPUT_FIELD_FONT);
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

        initSecondChooserLabel();
        addChildComponent(this.secondChooserLabel);

        initSecondChooser();
        addChildComponent(this.secondChooser);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        if (this.rateOfLabelFieldWidthInTotal > 0) {
            this.labelFieldWidth = (short) (this.rateOfLabelFieldWidthInTotal * availableHeight);
        }
        this.childComponentSizeMap.put(this.labelField, new Dimension(this.labelFieldWidth, maxChildComponentHeight));

        this.childComponentSizeMap.put(this.dateChooser,
                new Dimension(this.dateChooserWidth, maxChildComponentHeight));

        final byte timeChooserLabelWidth = 20;
        this.childComponentSizeMap.put(this.hourChooserLabel,
                new Dimension(timeChooserLabelWidth, maxChildComponentHeight));
        this.childComponentSizeMap.put(this.minuteChooserLabel,
                new Dimension(timeChooserLabelWidth, maxChildComponentHeight));
        this.childComponentSizeMap.put(this.secondChooserLabel,
                new Dimension(timeChooserLabelWidth, maxChildComponentHeight));

        final byte timeChooserWidth = 45;
        this.childComponentSizeMap.put(this.hourChooser,
                new Dimension(timeChooserWidth, maxChildComponentHeight));
        this.childComponentSizeMap.put(this.minuteChooser,
                new Dimension(timeChooserWidth, maxChildComponentHeight));
        this.childComponentSizeMap.put(this.secondChooser,
                new Dimension(timeChooserWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.hourChooserLabel.setResizable(false);
        this.hourChooser.setResizable(false);
        this.minuteChooserLabel.setResizable(false);
        this.minuteChooser.setResizable(false);
        this.secondChooserLabel.setResizable(false);
        this.secondChooser.setResizable(false);
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
        this.secondChooser.setValue(String.valueOf(value.getSecond()));
    }

    @Override
    public Optional<LocalDateTime> getValue() {
        final Optional<LocalDate> optionalDateChosen = this.dateChooser.getValue();
        if (!optionalDateChosen.isPresent()) {
            return Optional.empty();
        }

        final String defaultTimeValue = "0";

        final Optional<String> optionalHourChosen = this.hourChooser.getValue();
        final byte hour = Byte.parseByte(optionalHourChosen.orElse(defaultTimeValue));

        final Optional<String> optionalMinuteChosen = this.minuteChooser.getValue();
        final byte minute = Byte.parseByte(optionalMinuteChosen.orElse(defaultTimeValue));

        final Optional<String> optionalSecondChosen = this.secondChooser.getValue();
        final byte second = Byte.parseByte(optionalSecondChosen.orElse(defaultTimeValue));

        final LocalTime timeInputValue = LocalTime.of(hour, minute, second);
        final LocalDateTime dateTime = LocalDateTime.of(optionalDateChosen.get(), timeInputValue);
        return Optional.of(dateTime);
    }

    @Override
    public void clear() {
        this.dateChooser.clear();
        this.hourChooser.clear();
        this.minuteChooser.clear();
        this.secondChooser.clear();
    }
}
