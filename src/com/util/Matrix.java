package com.util;

import java.util.Arrays;

public class Matrix {

    public static final int EMPTY = 0;
    public static final int VOID = -1;

    private int x, y;
    private int width, height;
    private int[] data = null;

    public Matrix(Matrix matrix) {
        this.copy(matrix);
    }

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        data = new int[width * height];
        clear();
    }

    public Matrix(int width, int height, int[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    public void rotate(int times) {
        for (int i = 0; i < times; i++) {
            //creates temps
            int nw = height;
            int nh = width;
            Matrix aux = new Matrix(this);
            aux.width = nw;
            aux.height = nh;

            //gets rotated values
            for (int y = 0; y < aux.width; y++) {
                for (int x = 0; x < aux.height; x++) {
                    aux.set(this.get(x, y), nw - 1 - y, x);
                }
            }

            //updates current object
            this.copy(aux);
        }
    }

    public void insert(Matrix m, int targetX, int targetY, boolean override) {
        int xa = Math.max(targetX, 0);
        int ya = Math.max(targetY, 0);
        int nw = width < m.width + targetX ? (m.width - targetX + 1) : m.width;
        int nh = height < m.height + targetY ? (m.height - targetY + 1) : m.height;

        if (nw <= 0 || nh <= 0) return;

        for (int y = 0; y < nh; y++) {
            for (int x = 0; x < nw; x++) {
                if (override) {
                    this.set(x + xa, y + ya, m.get(x, y));
                } else {
                    if (this.get(x + xa, y + ya) == EMPTY) {
                        this.set(x + xa, y + ya, m.get(x, y));
                    }
                }
            }
        }
    }

    public Matrix select(int offX0, int offY0, int offX1, int offY1) {
        int xa = Math.max(offX0, 0);
        int ya = Math.max(offY0, 0);
        int xb = Math.min(offX1, width);
        int yb = Math.min(offY1, height);

        Matrix res = new Matrix((Math.abs(xb - xa) + 1), (Math.abs(yb - ya) + 1));
        if (res.data.length > 0) {
            for (int y = 0; y < res.height; y++) {
                for (int x = 0; x < res.width; x++) {
                    res.set(x, y, this.get(x + xa, y + ya));
                }
            }
        }

        return res;
    }

    public void copy(Matrix m) {
        this.width = m.width;
        this.height = m.height;
        this.x = m.x;
        this.y = m.y;
        if (this.data == null) this.data = new int[m.data.length];
        System.arraycopy(m.data, 0, this.data, 0, m.data.length);
    }

    public void set(int x, int y, int value) {
        if (inBounds(x, y)) {
            data[x + y * width] = value;
        }
    }

    public int get(int x, int y) {
        if (inBounds(x, y)) {
            return data[x + y * width];
        }

        return VOID;
    }

    public boolean inBounds(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    public void clear() {
        clear(EMPTY);
    }

    //not recommended use with different value of EMPTY
    public void clear(int clearValue) {
        Arrays.fill(data, clearValue);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                res.append(get(x, y)).append(" ");
            }
            res.append("\n");
        }

        return res.toString();
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

    public static void main(String[] args) {
        Matrix m = new Matrix(1, 4);
        System.out.println(m);
        System.out.println();
        m.rotate(1);
        System.out.println(m);
    }
}