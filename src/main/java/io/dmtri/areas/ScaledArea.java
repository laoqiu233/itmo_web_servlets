package io.dmtri.areas;

import io.dmtri.models.Point;

/**
 * Scales an area with the specified multipliers
 */
public class ScaledArea extends AbstractArea {
    private final double mulX;
    private final double mulY;
    private final Area decoratedArea;

    public ScaledArea(double mulX, double mulY, Area decoratedArea) {
        this.mulX = mulX;
        this.mulY = mulY;
        this.decoratedArea = decoratedArea;
    }

    @Override
    public boolean checkPoint(Point point) {
        return decoratedArea.checkPoint(new Point(point.x() / mulX, point.y() / mulY, point.r()));
    }
}
