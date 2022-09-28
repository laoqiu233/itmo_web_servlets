package io.dmtri.formmanagers;

public class RadioInputElement extends MultipleChoiceInputElement {
    public RadioInputElement(double ...choices) {
        super(choices, InputElementType.radio);
    }
}
