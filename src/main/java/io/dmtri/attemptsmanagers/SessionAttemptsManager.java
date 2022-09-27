package io.dmtri.attemptsmanagers;

import java.util.LinkedList;
import java.util.List;

import io.dmtri.models.PointAttempt;
import jakarta.servlet.http.HttpSession;

public class SessionAttemptsManager implements AttemptsManager {
    private static final String ATTRIBUTE_NAME = "io.dmtri.attempts";
    private final HttpSession session;
    private final List<PointAttempt> attempts = new LinkedList<>();

    public SessionAttemptsManager(HttpSession session) {
        this.session = session;

        // Safely casts list stored in session
        try {
            List <?> temp = (List<?>) session.getAttribute(ATTRIBUTE_NAME);
            if (temp != null) {
                for (Object o : temp) {
                    if (o instanceof PointAttempt) {
                        attempts.add((PointAttempt) o);
                    }
                }
            }
        } catch (ClassCastException e) {
            // Ignored
        }
        
        saveToSession();
    }

    @Override
    public List<PointAttempt> getAttempts() {
        return attempts;
    }

    @Override
    public void clearAttempts() {
        attempts.clear();
        saveToSession();
    }

    @Override
    public void addAttempt(PointAttempt attempt) {
        attempts.add(attempt);
        saveToSession();
    }

    @Override
    public int getAttemptsCount() {
        return attempts.size();
    }

    private void saveToSession() {
        session.setAttribute(ATTRIBUTE_NAME, attempts);
    }
}
