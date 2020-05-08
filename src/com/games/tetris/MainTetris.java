package com.games.tetris;

import com.common.DataMatrix;
import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;

import java.awt.*;

import static com.games.tetris.ShapeMatrixProvider.*;

public class MainTetris extends AbstractApplication {

    public static final DataMatrix CONTENT = new DataMatrix(12, 24);
    public static final int MATRIX_UNITY_SIZE = 10;

    public MainTetris() {
        TetrisShape shape = new TetrisShape(randomShapeID());
        spawnShape(shape);
    }

    public static void initTetrisGame(float... scale) {
        MainTetris game = new MainTetris();
        Engine engine = new Engine(game);
        engine.setSize(CONTENT.getWidth() * MATRIX_UNITY_SIZE,
                CONTENT.getHeight() * MATRIX_UNITY_SIZE);
        engine.setScale(scale.length > 0 ? scale[0] : 1f);
        engine.start();
    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        //shape.render(engine, renderer);
        for (int y = 0; y < CONTENT.getHeight(); y++) {
            for (int x = 0; x < CONTENT.getWidth(); x++) {
                if (CONTENT.getValueAt(x, y) != DataMatrix.EMPTY) {
                    renderer.fillRect(x * MATRIX_UNITY_SIZE, y * MATRIX_UNITY_SIZE, MATRIX_UNITY_SIZE, MATRIX_UNITY_SIZE, Color.YELLOW.getRGB());
                    renderer.drawRect(x * MATRIX_UNITY_SIZE, y * MATRIX_UNITY_SIZE, MATRIX_UNITY_SIZE, MATRIX_UNITY_SIZE, Color.DARK_GRAY.getRGB());
                }
            }
        }
    }

    public void spawnShape(TetrisShape shape) {
        int gw = CONTENT.getWidth();
        int sw = shape.getMatrix().getWidth();
        CONTENT.insertDataMatrixAt(shape.getMatrix(), (gw / 2) - (sw / 2), 0);
    }

    //chamar depois que colidir na base
    private void removeFullRows() {
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
