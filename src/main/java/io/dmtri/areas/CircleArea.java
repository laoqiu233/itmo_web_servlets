package io.dmtri.areas;

import io.dmtri.models.Point;

/**
 * Describes a circular area with radius R centered in the origin.
 */
public class CircleArea extends AbstractArea {
    private final double r;

    public CircleArea(double r) {
        this.r = r;
    }

    @Override
    public boolean checkPoint(Point point) {
        return point.x() * point.x() + point.y() * point.y() <= point.r() * point.r() * r * r;
    }
}
