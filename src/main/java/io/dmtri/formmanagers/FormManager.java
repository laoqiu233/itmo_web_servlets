package io.dmtri.formmanagers;

import com.google.gson.Gson;

public class FormManager {
    private final InputElement xInput;
    private final InputElement yInput;
    private final InputElement rInput;

    public FormManager(InputElement xInput, InputElement yInput, InputElement rInput) {
        this.xInput = xInput;
        this.yInput = yInput;
        this.rInput = rInput;
    }

    public String renderJSON() {
        Gson g = new Gson();
        return g.toJson(this);
    }

    public InputElement xInput() {
        return xInput;
    }

    public InputElement yInput() {
        return yInput;
    }

    public InputElement rInput() {
        return rInput;
    }
}
