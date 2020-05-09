package com.common;

public class DataMatrix {

    public static final int EMPTY = 0;
    public static final int VOID = -1;

    private int x, y;
    private int width, height;
    private int[] data;

    public DataMatrix(int width, int height, int[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    public DataMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        data = new int[width * height];
        this.clearAllSolidData();
    }

    public void clearAllSolidData() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != EMPTY) data[i] = EMPTY;
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public void rotate() {
        //create temps
        int nWidth = height;
        int nHeight = width;
        DataMatrix aux = new DataMatrix(nWidth, nHeight, dataCopy());

        //get rotated values
        for (int y = 0; y < aux.width; y++) {
            for (int x = 0; x < aux.height; x++) {
                aux.setValueAt(getValueAt(x, y), nWidth - 1 - y, x);
            }
        }

        //update current object
        this.copy(aux);
    }

    public void insertDataMatrixAt(DataMatrix m, int x, int y, boolean overrideExistingSolids) {
        m.setX(x);
        m.setY(y);
        for (int yy = 0; yy < m.height; yy++) {
            for (int xx = 0; xx < m.width; xx++) {
                if (!overrideExistingSolids) {
                    if (m.getValueAt(xx, yy) == EMPTY) {
                        if (getValueAt(m.getX() + xx, m.getY() + yy) == EMPTY) {
                            setValueAt(m.getValueAt(xx, yy), m.getX() + xx, m.getY() + yy);
                        }
                    } else {
                        setValueAt(m.getValueAt(xx, yy), m.getX() + xx, m.getY() + yy);
                    }
                } else {
                    setValueAt(m.getValueAt(xx, yy), m.getX() + xx, m.getY() + yy);
                }
            }
        }
    }

    public DataMatrix select(int fromX, int fromY, int toX, int toY) {
        if (!coordInBounds(fromX, fromY) && !coordInBounds(toX, toY))
            return null;
        DataMatrix res = new DataMatrix(Math.abs(toX - fromX) + 1, Math.abs(toY - fromY) + 1);
        res.setX(fromX);
        res.setY(fromY);
        for (int y = 0; y < res.height; y++) {
            for (int x = 0; x < res.width; x++) {
                res.setValueAt(getValueAt(x + fromX, y + fromY), x, y);
            }
        }
        return res;
    }

    public int getValueAt(int x, int y) {
        if (!coordInBounds(x, y)) return VOID;
        return data[x + y * width];
    }

    public void setValueAt(int value, int x, int y) {
        if (!coordInBounds(x, y)) return;
        data[x + y * width] = value;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean coordInBounds(int x, int y) {
        return x >= 0 & x < width && y >= 0 & y < height;
    }

    public int[] dataCopy() {
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, copy.length);
        return copy;
    }

    public DataMatrix emptyClone() {
        return new DataMatrix(width, height);
    }

    public void copy(DataMatrix dm) {
        x = dm.x;
        y = dm.y;
        width = dm.width;
        height = dm.height;
        data = new int[dm.data.length];
        System.arraycopy(dm.data, 0, data, 0, data.length);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @SuppressWarnings("StringConcatenationInLoop")
    @Override
    public String toString() {
        String s = "|";

        for (int i = 0; i < width; i++) {
            s += "---";
        }

        s += "|\n";

        for (int i = 0; i < data.length; i++) {
            s += " ";
            if (data[i] != EMPTY) {
                s += " @";
            } else {
                s += "  ";
            }

            if ((i + 1) % width == 0) s += " |\n";
        }

        for (int i = 0; i < width; i++) {
            s += "---";
        }

        s += "-|";

        return "DataMatrix{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", \ndata=\n" + s +
                '}';
    }
}
