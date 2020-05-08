package com.games.tetris;

import com.common.Sup;
import com.common.DataMatrix;
import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;

public class Tetris extends AbstractApplication {

    public static final DataMatrix CONTENT = new DataMatrix(12, 24);

    private int activeShapeX, activeShapeY;

    public Tetris() {

    }

    public void initTetris() {
        Engine engine = new Engine(this);
        engine.setSize(CONTENT.getWidth() * 10, CONTENT.getHeight() * 10);
        engine.start();
    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {

    }

    public void leftShape(DataMatrix shape) {

    }

    public void downShape(DataMatrix shape) {
        if (canDownShape(shape)) {
            CONTENT.insertDataMatrixAt(shape.emptyClone(), activeShapeX, activeShapeY);
            CONTENT.insertDataMatrixAt(shape, activeShapeX++, activeShapeY++);
        }
    }

    public boolean canDownShape(DataMatrix shape) {
        return Sup.inBounds(CONTENT, shape) && !Sup.hasCollisionOn(CONTENT, shape, Sup.BOTTOM);
    }

    //chamar depois que colidir na base
    private void updateFullLines() {
        boolean full = false;
        for (int y = 0; y < CONTENT.getHeight(); y++) {
            for (int x = 0; x < CONTENT.getWidth(); x++) {
                full = CONTENT.getValueAt(x, y) != DataMatrix.EMPTY;
            }
            if (full) {
                if (y > 0) {
                    CONTENT.insertDataMatrixAt(CONTENT.select(0, 0, CONTENT.getWidth() - 1, y - 1), 0, 1);
                }  //else: Tetris acabou?
            }
        }
    }
}
