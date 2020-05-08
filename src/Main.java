import com.common.DataMatrix;
import com.common.Sup;
import com.games.tetris.ShapeMatrixProvider;

public class Main {

    public static void main(String[] args) {
        DataMatrix matrix = new DataMatrix(4, 5);

        DataMatrix s = ShapeMatrixProvider.getNew(ShapeMatrixProvider.SQUARE_ID);

        matrix.insertDataMatrixAt(s, 0, 2);
        matrix.insertDataMatrixAt(ShapeMatrixProvider.getNew(ShapeMatrixProvider.SQUARE_ID), 2, 3);
        System.out.println(matrix);

        System.out.println(Sup.hasCollisionOn(matrix, s, Sup.RIGHT));
    }
}
