package io.dmtri.attemptsmanagers;

import java.util.List;

import io.dmtri.models.PointAttempt;

public interface AttemptsManager {
    public List<PointAttempt> getAttempts();
    public void clearAttempts();
    public void addAttempt(PointAttempt attempt);
    public int getAttemptsCount();
}
