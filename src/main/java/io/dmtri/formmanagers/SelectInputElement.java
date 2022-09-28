package io.dmtri.formmanagers;

public class SelectInputElement extends MultipleChoiceInputElement {
    public SelectInputElement(double ...choices) {
        super(choices, InputElementType.select);
    }
}
