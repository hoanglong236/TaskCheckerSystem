package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

public class YesNoOptionChooserWrapper extends InputComponentWrapper<Boolean> {

    public YesNoOptionChooserWrapper(String labelText, boolean initValue) {
        super();
        init(labelText, initValue);
    }

    private void initInputField(boolean initValue) {
        this.inputField = InputComponentFactory.createYesNoOptionChooser(initValue);
    }

    private void init(String labelText, boolean initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
