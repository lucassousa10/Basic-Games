package com.games.tetris;

import com.common.DataMatrix;
import com.common.Sup;
import com.engine.Engine;
import com.engine.Renderer;
import com.engine.entities.ApplicationObject;

import static com.games.tetris.MainTetris.CONTENT;

public class TetrisShape extends ApplicationObject {

    private DataMatrix matrix;

    public TetrisShape(int shapeID) {
        matrix = ShapeMatrixProvider.getNew(shapeID);
    }


    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {

    }

    public boolean moveToRight() {
        if (canMoveTo(Sup.RIGHT)){
            CONTENT.insertDataMatrixAt(matrix.emptyClone(), matrix.getX(), matrix.getY());
            matrix.setX(matrix.getX() + 1);
            CONTENT.insertDataMatrixAt(matrix, matrix.getX(), matrix.getY());
            return true;
        }

        return false;
    }

    public boolean moveToLeft() {
        if (canMoveTo(Sup.LEFT)){
            CONTENT.insertDataMatrixAt(matrix.emptyClone(), matrix.getX(), matrix.getY());
            matrix.setX(matrix.getX() - 1);
            CONTENT.insertDataMatrixAt(matrix, matrix.getX(), matrix.getY());
            return true;
        }

        return false;
    }

    public boolean moveToDown() {
        if (canMoveTo(Sup.BOTTOM)) {
            CONTENT.insertDataMatrixAt(matrix.emptyClone(), matrix.getX(), matrix.getY());
            matrix.setY(matrix.getY() + 1);
            CONTENT.insertDataMatrixAt(matrix, matrix.getX(), matrix.getY());
            return true;
        }

        return false;
    }

    public boolean canMoveTo(int direction) {
        return Sup.inBounds(CONTENT, matrix) &&
                !Sup.hasCollisionOn(CONTENT, matrix, direction);
    }

    public DataMatrix getMatrix() {
        return matrix;
    }

    public void setMatrix(DataMatrix matrix) {
        this.matrix = matrix;
    }
}
