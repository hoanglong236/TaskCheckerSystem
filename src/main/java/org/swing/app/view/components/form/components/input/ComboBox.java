package org.swing.app.view.components.form.components.input;

import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.form.components.InputComponent;

import javax.swing.JComboBox;
import java.util.Set;

public class ComboBox extends SimpleComponent implements InputComponent {

    private Set<String> valueRange;

    public ComboBox(Set<String> valueRange, String initValue) {
        super();
        if (valueRange == null || valueRange.isEmpty()) {
            final MessageLoader messageLoader = MessageLoader.getInstance();
            throw new IllegalArgumentException(messageLoader.getMessage("..."));
        }
        final String[] valueRangeArray = this.valueRange.toArray(new String[0]);
        this.component = JComponentFactory.createJComboBox(valueRangeArray);
        this.valueRange = valueRange;
        setValue(initValue);
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        if (!(value instanceof String)) {
            throw new IllegalArgumentException();
        }
        if (!this.valueRange.contains(value)) {
            throw new IllegalArgumentException();
        }
        ((JComboBox) this.component).setSelectedItem(value);
    }

    @Override
    public Object getValue() {
        return ((JComboBox) this.component).getSelectedItem();
    }

    @Override
    public void clear() {
        final String firstValueInRange = this.valueRange.iterator().next();
        ((JComboBox) this.component).setSelectedItem(firstValueInRange);
    }
}
