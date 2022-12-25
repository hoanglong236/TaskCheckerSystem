package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

public class TextAreaWrapper extends InputComponentWrapper<String> {

    public TextAreaWrapper(String labelText, String initValue) {
        super();
        init(labelText, initValue);
    }

    @Override
    protected void initLabelField(String labelText) {
        this.labelField = UIComponentFactory.createLabelArea(labelText);
    }

    private void initInputField(String initValue) {
        this.inputField = InputComponentFactory.createTextArea(initValue);
    }

    private void init(String labelText, String initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
