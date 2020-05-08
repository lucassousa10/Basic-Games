import com.common.DataMatrix;
import com.common.Sup;
import com.games.tetris.Shape;

public class Main {

    public static void main(String[] args) {
        DataMatrix matrix = new DataMatrix(4, 5, new int[]{
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        });

        DataMatrix s = Shape.getNew(Shape.SQUARE_ID);

        matrix.insertDataMatrixAt(s, 2, 2);
        matrix.insertDataMatrixAt(Shape.getNew(Shape.SQUARE_ID), 0, 3);
        System.out.println(matrix);

        System.out.println(Sup.hasCollisionOn(matrix, s, Sup.TOP));
    }
}
