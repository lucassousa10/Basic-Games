package com.games.tetris;

import com.common.DataMatrix;
import com.common.Misc;
import com.engine.Engine;

import static com.games.tetris.MainTetris.CONTENT;

public class TetrisShape {

    private DataMatrix matrix;

    public TetrisShape(int shapeID) {
        matrix = ShapeMatrixProvider.getNew(shapeID);
    }

    public void update(Engine engine, float deltaTime) {

    }

    public boolean moveToRight() {
        if (canMoveTo(Misc.RIGHT)) {
            CONTENT.insertDataMatrixAt(matrix.emptyClone(), matrix.getX(), matrix.getY());
            matrix.setX(matrix.getX() + 1);
            CONTENT.insertDataMatrixAt(matrix, matrix.getX(), matrix.getY());
            return true;
        }

        return false;
    }

    public boolean moveToLeft() {
        if (canMoveTo(Misc.LEFT)) {
            CONTENT.insertDataMatrixAt(matrix.emptyClone(), matrix.getX(), matrix.getY());
            matrix.setX(matrix.getX() - 1);
            CONTENT.insertDataMatrixAt(matrix, matrix.getX(), matrix.getY());
            return true;
        }

        return false;
    }

    public boolean moveToDown() {
        if (canMoveTo(Misc.BOTTOM)) {
            CONTENT.insertDataMatrixAt(matrix.emptyClone(), matrix.getX(), matrix.getY());
            matrix.setY(matrix.getY() + 1);
            CONTENT.insertDataMatrixAt(matrix, matrix.getX(), matrix.getY());
            return true;
        }

        return false;
    }

    public boolean canMoveTo(int direction) {
        return Misc.inBounds(CONTENT, matrix) &&
                !Misc.hasCollisionOn(CONTENT, matrix, direction);
    }

    public DataMatrix getMatrix() {
        return matrix;
    }

    public void setMatrix(DataMatrix matrix) {
        this.matrix = matrix;
    }
}
