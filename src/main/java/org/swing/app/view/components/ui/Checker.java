package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JCheckBox;

public class Checker extends SimpleComponent {

    protected Checker(boolean checked) {
        this.component = JComponentFactory.createJCheckBox();
        setChecked(checked);
    }

    public void setChecked(boolean checked) {
        ((JCheckBox) this.component).setSelected(checked);
    }

    public boolean isChecked() {
        return ((JCheckBox) this.component).isSelected();
    }
}
