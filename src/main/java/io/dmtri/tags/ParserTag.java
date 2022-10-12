package io.dmtri.tags;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class ParserTag extends SimpleTagSupport {
    private String input;
    private boolean ignoreCase;

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public boolean getIgnoreCase() {
        return ignoreCase;
    }

    @Override
    public void doTag() throws JspException, IOException {
        StringWriter sw = new StringWriter();
        getJspBody().invoke(sw);
        String body = sw.getBuffer().toString();

        int ans = 0;
        if (input != null && input.length() != 0) {
            if (ignoreCase) {
                body = body.toLowerCase();
                input = input.toLowerCase();
            }
            ans = countSubstring(body, input);
        }
        
        getJspContext().getOut().write("Result: " + ans);
    }

    private int countSubstring(String s, String q) {
        final int m = q.length();
        int ans = 0;

        for (int i = 0; i < s.length() - m; i++) {
            if (s.substring(i, i + m).equals(q)) ans++;
        }

        return ans;
    }
}
