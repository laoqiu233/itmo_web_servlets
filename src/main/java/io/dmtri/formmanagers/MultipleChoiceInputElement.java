package io.dmtri.formmanagers;

public class MultipleChoiceInputElement extends AbstractInputElement {
    private final double[] choices;

    public MultipleChoiceInputElement(double[] choices, InputElementType type) {
        super(type);
        this.choices = choices;
    }

    public double[] getChoices() {
        return choices;
    }
}
