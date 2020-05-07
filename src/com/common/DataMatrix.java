package com.common;

public class DataMatrix {

    public static final int EMPTY = 0;
    public static final int SOLID = 1;

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
        this.clearSolidAllData();
    }

    public void clearSolidAllData(){
        for (int i = 0; i < data.length; i++) {
            if (data[i] != EMPTY) data[i] = EMPTY;
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public void rotate(){
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
        width = aux.width;
        height = aux.height;
        System.arraycopy(aux.data, 0, data, 0, aux.data.length);
    }

    public void insertDataMatrixAt(DataMatrix m, int x, int y){
        for (int yy = 0; yy < m.height; yy++) {
            for (int xx = 0; xx < m.width; xx++) {
                setValueAt(m.getValueAt(xx, yy), x + xx, y + yy);
            }
        }
    }

    public DataMatrix select(int fromX, int fromY, int toX, int toY){
        //TODO: add checagens params e W e H do res sao validos
        DataMatrix res = new DataMatrix((toX - fromX) + 1, (toY - fromY) + 1);

        for (int y = 0; y < res.height; y++) {
            for (int x = 0; x < res.width; x++) {
                res.setValueAt(getValueAt(x + fromX, y + fromY), x, y);
            }
        }

        return res;
    }

    public int getValueAt(int x, int y){
        if (!coordInBounds(x, y)) return -1;
        return data[x + y * width];
    }

    public void setValueAt(int value, int x, int y){
        if (!coordInBounds(x, y)) return;
        data[x + y * width] = value;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean coordInBounds(int x, int y){ return x >= 0 & x < width && y >= 0 & y < height; }

    public int[] dataCopy(){
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, copy.length);
        return copy;
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

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < data.length; i++) {
            //noinspection StringConcatenationInLoop
            s += " ";
            s += data[i] >= 0 ? " " + data[i] : data[i];
            if ((i + 1) % width == 0) s += "\n";
        }

        return "DataMatrix{" +
                "width=" + width +
                ", height=" + height +
                ", \ndata=\n" + s +
                '}';
    }

    /*
    String s = "";

        for (int i = 0; i < data.length; i++) {
            //noinspection StringConcatenationInLoop
            s += " ";
            s += data[i] >= 0 ? " " + data[i] : data[i];
            if ((i + 1) % width == 0) s += "\n";
        }

        return s;
     */
}
