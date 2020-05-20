import com.util.Matrix;
import com.games.tetris.Tetris;

public class Main {

    public static Matrix board, shape;

    public static void main(String[] args) {
        //Tetris.initTetrisGame();

        /*
        board = new DataMatrix(10, 12);

        DataMatrix aux = ShapeProvider.createShape(2, 0);
        board.insertDataMatrixAt(aux, 0, 4, false);
        spawnShape();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (canMoveTo(BOTTOM)){
                        moveShapeTo(BOTTOM);
                    } else {
                        if (!spawnShape()){
                            System.out.println("JOGO ACABOU");
                            System.out.println(board);
                            System.exit(0);
                        }
                    }

                    System.out.println(board);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
         */
    }

    /*
    public static boolean spawnShape() {
        shape = ShapeProvider.createShape();
        shape.setX((board.getWidth() / 2) - (shape.getWidth() / 2));
        shape.setY(0);

        boolean freeToSpawn = false;
        for (int v : board.select(shape.getX(), shape.getY(), shape.getX() + (shape.getX() - 1), shape.getY() + (shape.getHeight() - 1)).getData()) {
            if (v != DataMatrix.EMPTY){
                freeToSpawn = false;
                break;
            } else {
                freeToSpawn = true;
            }
        }

        board.insertDataMatrixAt(shape, shape.getX(), shape.getY(), freeToSpawn);

        return freeToSpawn;
    }

    static void moveShapeTo(int where) {
        int xFact = where == RIGHT ? 1 : where == LEFT ? -1 : 0;
        int yFact = where == TOP ? -1 : where == BOTTOM ? 1 : 0;

        DataMatrix sel = board.select(shape.getX(), shape.getY(), shape.getX() + (shape.getWidth() - 1), shape.getY() + (shape.getHeight() - 1));
        DataMatrix blank = new DataMatrix(sel.getWidth(), sel.getHeight());
        board.insertDataMatrixAt(blank, shape.getX(), shape.getY(), true);

        sel.setX(sel.getX() + xFact);
        sel.setY(sel.getY() + yFact);

        board.insertDataMatrixAt(sel, sel.getX(), sel.getY(), false);
        shape.copy(sel);
    }

    public static boolean canMoveTo(int direction) {
        return inBounds(board, shape) && !hasCollisionOn(board, shape, direction);
    }
     */
}
