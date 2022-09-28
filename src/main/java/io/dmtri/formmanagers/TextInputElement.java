package io.dmtri.formmanagers;

public class TextInputElement extends AbstractInputElement {
    private final double min;
    private final double max;

    public TextInputElement(double min, double max) {
        super(InputElementType.text);

        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
