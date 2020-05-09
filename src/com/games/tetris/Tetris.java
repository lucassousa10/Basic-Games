package com.games.tetris;

import com.common.DataMatrix;
import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Input;
import com.engine.Renderer;

import java.awt.*;

import static com.common.Misc.*;

public class Tetris extends AbstractApplication {

    public static final DataMatrix BOARD = new DataMatrix(12, 24);
    public static final int UNITY = 10;
    public static final int WIDTH = BOARD.getWidth() * UNITY, HEIGHT = BOARD.getHeight() * UNITY;

    private DataMatrix activeShape;

    private float timeBuffer = 0f;
    private float speed = 1f;
    private boolean gameOver = false;

    public Tetris() {
        spawnShape();
    }

    public static void initTetrisGame() {
        Engine engine = new Engine(new Tetris());
        engine.setSize(WIDTH, HEIGHT);
        engine.setScale(2.5f);
        engine.start();
    }

    @Override
    public void update(Engine engine, float deltaTime) {
        if (!gameOver) {
            int userDirection = Input.isLeftDown() ? LEFT : Input.isRightDown() ? RIGHT : -1;
            if (userDirection != -1) {
                if (canMoveTo(userDirection)) moveShapeTo(userDirection);
            }

            timeBuffer += deltaTime;
            if (timeBuffer >= (Input.isDown() ? .09f : speed)) {
                if (canMoveTo(BOTTOM)) {
                    moveShapeTo(BOTTOM);
                } else {
                    if (!spawnShape()) {
                        gameOver = true;
                    }
                }

                timeBuffer = 0f;
            }
        }
    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        for (int y = 0; y < BOARD.getHeight(); y++) {
            for (int x = 0; x < BOARD.getWidth(); x++) {
                if (BOARD.getValueAt(x, y) != DataMatrix.EMPTY) {
                    renderer.fillRect(x * UNITY, y * UNITY, UNITY - 1, UNITY - 1, Color.YELLOW.getRGB());
                    renderer.drawRect(x * UNITY, y * UNITY, UNITY, UNITY, Color.DARK_GRAY.getRGB());
                }
            }
        }

//        if (gameOver){
//            renderer.drawText("PERDEU", 0, 0, Color.WHITE.getRGB(), new Font("/com/ui/fonts/arial12.png"));
//        }
    }

    public boolean spawnShape() {
        activeShape = ShapeProvider.createShape();
        activeShape.setX((BOARD.getWidth() / 2) - (activeShape.getWidth() / 2));
        activeShape.setY(0);

        boolean freeToSpawn = false;
        for (int v : BOARD.select(activeShape.getX(), activeShape.getY(), activeShape.getX() + (activeShape.getX() - 1), activeShape.getY() + (activeShape.getHeight() - 1)).getData()) {
            if (v != DataMatrix.EMPTY) {
                freeToSpawn = false;
                break;
            } else {
                freeToSpawn = true;
            }
        }

        BOARD.insertDataMatrixAt(activeShape, activeShape.getX(), activeShape.getY(), freeToSpawn);
        speed -= .09f;
        return freeToSpawn;
    }

    public void moveShapeTo(int where) {
        int xFact = where == RIGHT ? 1 : where == LEFT ? -1 : 0;
        int yFact = where == TOP ? -1 : where == BOTTOM ? 1 : 0;

        DataMatrix sel = BOARD.select(activeShape.getX(), activeShape.getY(), activeShape.getX() + (activeShape.getWidth() - 1), activeShape.getY() + (activeShape.getHeight() - 1));
        DataMatrix blank = new DataMatrix(sel.getWidth(), sel.getHeight());
        BOARD.insertDataMatrixAt(blank, activeShape.getX(), activeShape.getY(), true);

        sel.setX(sel.getX() + xFact);
        sel.setY(sel.getY() + yFact);

        BOARD.insertDataMatrixAt(sel, sel.getX(), sel.getY(), false);
        activeShape.copy(sel);
    }

    public boolean canMoveTo(int direction) {
        return inBounds(BOARD, activeShape) && !hasCollisionOn(BOARD, activeShape, direction);
    }
}
