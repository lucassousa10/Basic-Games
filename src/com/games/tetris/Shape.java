package com.games.tetris;

import com.common.DataMatrix;

public class Shape {

    public static final int SQUARE_ID = 0;
    public static final DataMatrix SQUARE = new DataMatrix(2, 2, new int[]{
            1, 1,
            1, 1});

    public static final int LINE_ID = 1;
    public static final DataMatrix LINE = new DataMatrix(4, 1, new int[]{
            1, 1, 1, 1});

    public static final int T_ID = 2;
    public static final DataMatrix T = new DataMatrix(3, 2, new int[]{
            1, 1, 1,
            0, 1, 0});

    public static final int L_A_ID = 3;
    public static final DataMatrix L_A = new DataMatrix(2, 3, new int[]{
            1, 0,
            1, 0,
            1, 1});

    public static final int L_B_ID = 4;
    public static final DataMatrix L_B = new DataMatrix(2, 3, new int[]{
            0, 1,
            0, 1,
            1, 1});

    public static final int S_A_ID = 5;
    public static final DataMatrix S_A = new DataMatrix(3, 2, new int[]{
            0, 1, 1,
            1, 1, 0});

    public static final int S_B_ID = 6;
    public static final DataMatrix S_B = new DataMatrix(3, 2, new int[]{
            1, 1, 0,
            0, 1, 1});

    private static final DataMatrix[] SHAPES = {
            SQUARE,
            LINE,
            T,
            L_A,
            L_B,
            S_A,
            S_B
    };

    public static DataMatrix getShapeByID(int ID) {
        return SHAPES[ID];
    }

    public static DataMatrix getRandomShape() {
        return getShapeByID((int) Math.floor(Math.random() * SHAPES.length));
    }
}
