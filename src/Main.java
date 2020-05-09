import com.common.DataMatrix;
import com.common.MeasureTime;
import com.common.Misc;
import com.games.tetris.ShapeProvider;

public class Main {

    public static DataMatrix board, shape;

    public static void main(String[] args) {
        board = new DataMatrix(10, 12);
        spawnShape(2);
    }

    public static void spawnShape(int ID) {
        shape = ShapeProvider.createShape(ID);
        board.insertDataMatrixAt(shape, (board.getWidth() / 2) - (shape.getWidth() / 2), 0, false);
    }

    static void moveShape() {
        DataMatrix sel = board.select(shape.getX(), shape.getY(), shape.getX() + (shape.getWidth() - 1), shape.getY() + (shape.getHeight() - 1));
        DataMatrix blank = new DataMatrix(sel.getWidth(), sel.getHeight());
        board.insertDataMatrixAt(blank, shape.getX(), shape.getY(), true);
        sel.setY(sel.getY() + 1);
        board.insertDataMatrixAt(sel, sel.getX(), sel.getY(), false);
        shape.copy(sel);
    }

    public static boolean canMoveDown(){
        return Misc.inBounds(board, shape) && !Misc.hasCollisionOn(board, shape, Misc.BOTTOM);
    }
}
