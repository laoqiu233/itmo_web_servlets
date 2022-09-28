package io.dmtri.formmanagers;

import com.google.gson.Gson;

public abstract class AbstractInputElement implements InputElement {
    private final InputElementType type;

    public AbstractInputElement(InputElementType type) {
        this.type = type;
    }

    @Override
    public InputElementType getType() {
        return type;
    }
    
    @Override
    public String renderJSON() {
        Gson g = new Gson();
        return g.toJson(this);
    }
}