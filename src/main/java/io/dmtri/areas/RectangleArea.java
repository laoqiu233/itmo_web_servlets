package io.dmtri.areas;

import io.dmtri.models.Point;

public class RectangleArea extends AbstractArea {
    private double width;
    private double height;

    public RectangleArea(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean checkPoint(Point point) {
        return Math.abs(point.x()) < width * point.r() / 2 && Math.abs(point.y()) < height * point.r() / 2;
    }
}
