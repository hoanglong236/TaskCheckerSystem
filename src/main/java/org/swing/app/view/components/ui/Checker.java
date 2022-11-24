package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;

import javax.swing.JCheckBox;

public class Checker extends SimpleComponent {

    public Checker(boolean checked) {
        this.component = JCOMPONENT_FACTORY.createJCheckBox();
        setChecked(checked);
    }

    public void update(boolean checked) {
        setChecked(checked);
    }

    private void setChecked(boolean checked) {
        ((JCheckBox) this.component).setSelected(checked);
    }

    public boolean isChecked() {
        return ((JCheckBox) this.component).isSelected();
    }
}
