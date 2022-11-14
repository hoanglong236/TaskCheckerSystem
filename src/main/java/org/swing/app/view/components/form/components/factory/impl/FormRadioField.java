package org.swing.app.view.components.form.components.factory.impl;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.components.form.components.FormInputComponent;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FormRadioField<E> extends WrapperComponent implements FormInputComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    private static final int DEFAULT_ROW_HEIGHT = 40;

    private final ButtonGroup buttonGroup;

    private RadioButton selectedBtn;

    private Set<?> valueRange;
    private final Map<JRadioButton, ?> radioBtnAndValueHashMap;
    private final Map<?, JRadioButton> valueAndRadioBtnHashMap;

    private int rowHeight;
    private boolean fitContentAllRadioBtns;
    private int radioBtnWidth;


    public FormRadioField(Set<E> valueRange, E value) {
        super();
        if (valueRange == null || valueRange.isEmpty()) {
            MessageLoader messLoader = MessageLoader.getInstance();
            throw new IllegalArgumentException(messLoader.getMessage("..."));
        }
        this.valueRange = valueRange;
        this.buttonGroup = new ButtonGroup();
        this.radioBtnAndValueHashMap = new HashMap<>();
        this.valueAndRadioBtnHashMap = new HashMap<>();
        this.selectedBtn = null;
        this.rowHeight = DEFAULT_ROW_HEIGHT;
        this.fitContentAllRadioBtns = true;
        setLayout(MAIN_LAYOUT);
        initComponents();
    }

    public void setRowHeight(int rowHeight) {
        if (rowHeight > 0) {
            this.rowHeight = rowHeight;
        } else {
            MessageLoader messLoader = MessageLoader.getInstance();
            throw new IllegalArgumentException(messLoader.getMessage("fradiofield.rowheight.positive"));
        }
    }

    private boolean isFitContentAllRadioBtns() {
        return fitContentAllRadioBtns;
    }

    private void setFitContentAllRadioBtns(boolean fitContentAllRadioBtns) {
        this.fitContentAllRadioBtns = fitContentAllRadioBtns;
    }

    public int getRadioBtnWidth() {
        return radioBtnWidth;
    }

    public void setRadioBtnWidth(int radioBtnWidth) {
        this.radioBtnWidth = radioBtnWidth;
        setFitContentAllRadioBtns(false);
    }

    private void initRadioBtnsWithFitContent(int availableWidth, int maxChildComponentWidth, int availableRows) {
        final int hGap = MAIN_LAYOUT.getHgap();
        final FontMetrics fontMetrics = this.component.getFontMetrics(this.component.getFont());
        int rowWidth = availableContainerWidth;

        for (Object value : this.valueRange) {
            String text = value.toString();
            int radioBtnWidth = fontMetrics.stringWidth(text);

            if (radioBtnWidth > availableComponentWidth) {
                radioBtnWidth = availableComponentWidth;
            }

            JRadioButton radioBtn = createRadioBtn(new Dimension(radioBtnWidth, getRowHeight()), text);

            add(radioBtn);
            this.buttonGroup.add(radioBtn);

            rowWidth = rowWidth - hgap - radioBtnWidth;
            if (rowWidth < 0) {
                if (availableRows < 0) {
                    final MessageLoader messLoader = MessageLoader.getInstance();
                    throw new IllegalArgumentException(messLoader.getMessage("fradiofield.size.invalid"));
                }
                rowWidth = availableContainerWidth - hgap - radioBtnWidth;
            }
        }
    }

    private void initRadioBtnsWithFixedWidth(int availableContainerWidth, int availableComponentWidth,
            int availableRows) {

        final int hgap = MAIN_LAYOUT.getHgap();
        int rowWidth = availableContainerWidth;
        int radioBtnWidth = getRadioBtnWidth();
        final MessageLoader messLoader = MessageLoader.getInstance();

        if (radioBtnWidth > availableComponentWidth) {
            throw new IllegalArgumentException(messLoader.getMessage("fradiofield.radiobtn.width.invalid"));
        }

        for (Object value : this.valueRange) {
            JRadioButton radioBtn = createRadioBtn(new Dimension(radioBtnWidth, getRowHeight()), value.toString());

            add(radioBtn);
            this.buttonGroup.add(radioBtn);

            rowWidth = rowWidth - hgap - radioBtnWidth;
            if (rowWidth < 0) {
                if (availableRows < 0) {
                    throw new IllegalArgumentException(messLoader.getMessage("fradiofield.size.invalid"));
                }
                rowWidth = availableContainerWidth - hgap - radioBtnWidth;
            }
        }
    }

    private void initComponents() {
        final int availableContainerWidth = (int) getPreferredSize().getWidth() - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableContainerHeight = (int) getPreferredSize().getHeight() - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int hgap = MAIN_LAYOUT.getHgap();
        final int vgap = MAIN_LAYOUT.getVgap();

        final int availableComponentWidth = availableContainerWidth - hgap;
        final int availableComponentHeight = availableContainerHeight - vgap;

        int availableRows;

        if (getRowHeight() > availableComponentHeight) {
            setRowHeight(availableContainerHeight);
            availableRows = 1;
        } else {
            availableRows = availableContainerHeight / (getRowHeight() + vgap);
        }

        if (isFitContentAllRadioBtns()) {
            initRadioBtnsWithFitContent(availableContainerWidth, availableComponentWidth, availableRows);
        } else {
            initRadioBtnsWithFixedWidth(availableContainerWidth, availableComponentWidth, availableRows);
        }

        revalidate();
        repaint();
    }

    @Override
    public void setInputValue(Object inputValue) {
        if (inputValue == null) {
            clear();
            return;
        }

        if (this.valueRange.contains(inputValue)) {
            final JRadioButton radioBtn = this.valueAndRadioBtnHashMap.get(inputValue);
            radioBtn.setSelected(true);
            this.selectedBtn = radioBtn;
        } else {
            final MessageLoader messLoader = MessageLoader.getInstance();
            throw new IllegalArgumentException(messLoader.getMessage("fradiofield.inputvalue.invalid"));
        }
    }

    @Override
    public Object getInputValue() {
        if (this.selectedBtn == null) {
            return null;
        }
        return this.radioBtnAndValueHashMap.get(this.selectedBtn);
    }

    @Override
    public void clear() {
        this.buttonGroup.clearSelection();
        this.selectedBtn = null;
    }

    private void loadChildComponentsSizeWithFitContent() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;

        final int hGap = MAIN_LAYOUT.getHgap();

        final int maxChildComponentWidth = availableWidth - MAIN_LAYOUT.getHgap();

        final FontMetrics fontMetrics = this.component.getFontMetrics(this.component.getFont());

        int rowWidth = availableWidth;

        for (final Object value : this.valueRange) {
            final String text = value.toString();
            int radioBtnWidth = fontMetrics.stringWidth(text);

            if (radioBtnWidth > maxChildComponentWidth) {
                radioBtnWidth = maxChildComponentWidth;
            }

            this.childComponentSizeMap.put()
        new Dimension(radioBtnWidth, getRowHeight())
            RadioButton radioBtn = new RadioButton(text);

            add(radioBtn);
            this.buttonGroup.add(radioBtn);

            rowWidth = rowWidth - hgap - radioBtnWidth;
            if (rowWidth < 0) {
                if (availableRows < 0) {
                    final MessageLoader messLoader = MessageLoader.getInstance();
                    throw new IllegalArgumentException(messLoader.getMessage("fradiofield.size.invalid"));
                }
                rowWidth = availableContainerWidth - hgap - radioBtnWidth;
            }
        }
    }

    private void loadChildComponentsSizeWithFixedSize() {

    }


    @Override
    protected void loadChildComponentsSize() {
        if (isFitContentAllRadioBtns()) {
            loadChildComponentsSizeWithFitContent();
        } else {
            loadChildComponentsSizeWithFixedSize();
        }
    }

    @Override
    protected void setNotResizableChildComponents() {

    }

    @Override
    public void setValue(Object value) {

    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void clear() {

    }
}

class RadioButton extends SimpleComponent {

    public RadioButton(String text) {
        this.component = new JRadioButton(text);
    }

    public void addToButtonGroup(ButtonGroup buttonGroup) {
        buttonGroup.add((JRadioButton) this.component);
    }
}