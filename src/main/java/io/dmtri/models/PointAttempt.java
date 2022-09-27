package io.dmtri.models;

/**
 * Stores the time taken to process the request in ms, the attempt time in ms, and result of the attempt.
 */
public record PointAttempt(Point point, long attemptTime, double processTime, boolean success) {}