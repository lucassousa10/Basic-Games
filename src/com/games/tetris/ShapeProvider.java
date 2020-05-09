package com.games.tetris;

import com.common.DataMatrix;

public class ShapeProvider {

    public static final int[][][] SHAPES = {
            {{1, 1, 1, 1}, {2, 2}},
            {{1, 1, 1, 1}, {4, 1}},
            {{1, 1, 1, 0, 1, 0}, {3, 2}},
            {{0, 1, 0, 1, 1, 1}, {2, 3}},
            {{1, 0, 1, 0, 1, 1}, {2, 3}},
            {{1, 1, 0, 0, 1, 1}, {3, 2}},
            {{0, 1, 1, 1, 1, 0}, {3, 2}}
    };

    public static DataMatrix createShape() {
        int ID = (int) Math.round(Math.random() * (SHAPES.length - 1));
        int rotation = (int) Math.round(Math.random() * 3);
        return createShape(ID, rotation);
    }

    public static DataMatrix createShape(int ID, int rotation) {
        DataMatrix m = new DataMatrix(SHAPES[ID][1][0], SHAPES[ID][1][1], SHAPES[ID][0]);
        if (ID != 0 && rotation > 0) {
            for (int i = 0; i < rotation; i++) {
                m.rotate();
            }
        }

        return m;
    }
}
