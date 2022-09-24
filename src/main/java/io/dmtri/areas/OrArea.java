package io.dmtri.areas;

import io.dmtri.models.Point;

/**
 * Returns true if any of the underlaying areas returned true.
 */
public class OrArea extends AbstractArea {
    private final Area[] decoratedAreas;

    public OrArea(Area ...areas) {
        this.decoratedAreas = areas;
    }

    @Override
    public boolean checkPoint(Point point) {
        for (Area area : decoratedAreas) {
            if (area.checkPoint(point)) return true;
        }
        return false;
    }
}
