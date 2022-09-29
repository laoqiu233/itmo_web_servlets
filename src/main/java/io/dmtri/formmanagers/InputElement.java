package io.dmtri.formmanagers;

public interface InputElement {
    public String renderJSON();
    public InputElementType getType();
    public boolean validate(double v);
}
