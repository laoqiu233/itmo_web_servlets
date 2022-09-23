package io.dmtri.areas;

import io.dmtri.models.Point;

public interface Area {
    boolean checkPoint(Point point);
    boolean[][] generateBitmap(int resolution);
}