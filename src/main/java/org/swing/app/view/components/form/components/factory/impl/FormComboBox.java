package org.swing.app.view.components.form.components.factory.impl;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.form.components.FormInputComponent;

import javax.swing.JComboBox;
import java.util.Set;

class FormComboBox<E> extends SimpleComponent implements FormInputComponent {

    private Set<E> valueRanges;

    public FormComboBox(Set<E> valueRanges, E value) {
        super();
        if (valueRanges == null || valueRanges.isEmpty()) {
            final MessageLoader messageLoader = MessageLoader.getInstance();
            throw new IllegalArgumentException(messageLoader.getMessage("..."));
        }
        final Object[] valueRangeArray = valueRanges.toArray();
        this.component = new JComboBox<>(valueRangeArray);
        this.valueRanges = valueRanges;
        setValue(value);
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        try {
            if (this.valueRanges.contains(value)) {
                ((JComboBox) this.component).setSelectedItem(value);
            } else {
                MessageLoader messLoader = MessageLoader.getInstance();
                throw new IllegalArgumentException(messLoader.getMessage("..."));
            }
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object getValue() {
        return ((JComboBox) this.component).getSelectedItem();
    }

    @Override
    public void clear() {
        final Object firstValueInRanges = this.valueRanges.iterator().next();
        ((JComboBox) this.component).setSelectedItem(firstValueInRanges);
    }
}
