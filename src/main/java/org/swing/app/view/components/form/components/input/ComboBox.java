package org.swing.app.view.components.form.components.input;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.form.components.InputComponent;

import javax.swing.JComboBox;
import java.util.Optional;
import java.util.Set;

public class ComboBox extends SimpleComponent implements InputComponent<String> {

    private Set<String> valueRange;

    public ComboBox(Set<String> valueRange) {
        super();
        if (valueRange == null || valueRange.isEmpty() || valueRange.contains(null)) {
            throw new IllegalArgumentException();
        }
        final String[] valueRangeArray = valueRange.toArray(new String[0]);
        this.sourceComponent = JComponentFactory.createJComboBox(valueRangeArray);
        this.valueRange = valueRange;
    }

    @Override
    public void setValue(String value) {
        if (value == null) {
            clear();
            return;
        }
        if (!this.valueRange.contains(value)) {
            throw new IllegalArgumentException();
        }
        ((JComboBox<?>) this.sourceComponent).setSelectedItem(value);
    }

    @Override
    public Optional<String> getValue() {
        final Object selectedItem = ((JComboBox<?>) this.sourceComponent).getSelectedItem();
        if (selectedItem == null) {
            return Optional.empty();
        }
        return Optional.of(selectedItem.toString());
    }

    @Override
    public void clear() {
        final String firstValueInRange = this.valueRange.iterator().next();
        ((JComboBox<?>) this.sourceComponent).setSelectedItem(firstValueInRange);
    }
}
