package com.common;

public class Sup {

    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    //todo: diagonals?
    //todo: fix TOP
    public static boolean hasCollisionOn(DataMatrix a, DataMatrix b, int where) {
        if (where == RIGHT || where == LEFT || where == BOTTOM){
            int xFact = where == RIGHT ? 1 : where == LEFT ? -1 : 0;
            int yFact = where == TOP ? -1 : where == BOTTOM ? 1 : 0;

            for (int y = 0; y < b.getHeight(); y++) {
                for (int x = 0; x < b.getWidth(); x++) {
                    if (b.getValueAt(x, y) == DataMatrix.EMPTY) {
                        int valueInA = a.getValueAt(x + b.getX(), y + b.getY());
                        if (valueInA != DataMatrix.EMPTY) {
                            return true;
                        }
                    } else { //is not empty
                        if (b.getValueAt(x + xFact, y + yFact) == DataMatrix.VOID) {
                            int valueInA = a.getValueAt(x + b.getX() + xFact, y + b.getY() + yFact);
                            if (valueInA != DataMatrix.EMPTY) {
                                return true;
                            }
                        }
                    }
                }
            }
        } else { //escolheu TOP
            throw new RuntimeException("TOP NOT WORKING YET!");
        }

        return false;
    }

    public static boolean inBounds(DataMatrix a, DataMatrix b) {
        return (b.getX() >= 0 && b.getX() + (b.getWidth() - 1) < a.getWidth()) &&
                (b.getY() >= 0 && b.getY() + (b.getHeight() - 1) < a.getHeight());
    }
}
