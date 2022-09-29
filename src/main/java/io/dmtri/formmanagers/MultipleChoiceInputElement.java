package io.dmtri.formmanagers;

import java.util.stream.Stream;

public class MultipleChoiceInputElement extends AbstractInputElement {
    private final double[] choices;

    public MultipleChoiceInputElement(double[] choices, InputElementType type) {
        super(type);
        this.choices = choices;
    }

    public double[] getChoices() {
        return choices;
    }

    @Override
    public boolean validate(double v) {
        for (double choice : choices) {
            if (v == choice) return true;
        }
        return false;
    }
}
