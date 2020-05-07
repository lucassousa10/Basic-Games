import com.common.DataMatrix;
import com.games.tetris.Shape;

public class Main {

    public static void main(String[] args) {
        DataMatrix matrix = new DataMatrix(3, 5, new int[]{
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        });

        matrix.insertDataMatrixAt(Shape.SQUARE, 0, 0);
        System.out.println(matrix);
    }
}
