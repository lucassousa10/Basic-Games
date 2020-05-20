package com.util;

import java.util.Arrays;

/**
 * Basic wrapper for a 1-dimension array acting as
 * a two dimensional array (2D matrix).
 *
 * Also, this class offer some casual methods.
 */
public class Matrix {

    public static final int EMPTY = 0;
    public static final int VOID = -1;

    private int x, y;
    private int width, height;
    private int[] data = null;

    /**
     * Creates a new Matrix that will have the passed width, height
     * and data.
     *
     * @param width the width that this matrix should have
     * @param height the height that this matrix should have
     * @param data the main data to this matrix
     */
    public Matrix(int width, int height, int[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    /**
     * Creates a new Matrix that will have the passed width and height.
     * This will automatically sets all the data content to EMPTY values.
     *
     * @param width the width that this matrix should have
     * @param height the height that this matrix should have
     */
    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        data = new int[width * height];
        clear();
    }

    /**
     * Creates a new matrix based on the param object.
     * This constructor will copy all the fields of parameter.
     *
     * @param matrix the base object to offer the new values to this matrix.
     */
    public Matrix(Matrix matrix) {
        this.copy(matrix);
    }

    /**
     * Rotates the entire matrix 90º degree.
     *
     * @param times the number of times to apply a single rotation.
     */
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

    /**
     * Gets part of the matrix.
     *
     * @param offX0 the starting X coordinate to clip this object
     * @param offY0 the starting Y coordinate to clip this object
     * @param offX1 the end X coordinate to clip this object
     * @param offY1 the end Y coordinate to clip this object
     *
     * @return a new instance of {@code Matrix} class with the extracted values.
     */
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

    /**
     * Sets a part of the matrix to some other.
     *
     * Takes another matrix and includes its values in THIS object.
     * Also, offer overriding option. If true, any existing value
     * in THIS object will be replaced for the other which came from
     * param. If false, only values that are different of EMPTY will
     * be replaced.
     *
     * @param m the matrix to be inserted
     * @param offX the offset coordinate X to place the {@code m}
     * @param offY the offset coordinate Y to place the {@code m}
     * @param override true to replace everything, false to replace only NOT EMPTIES.
     */
    public void insert(Matrix m, int offX, int offY, boolean override) {
        int xa = Math.max(offX, 0);
        int ya = Math.max(offY, 0);
        int nw = width < m.width + offX ? (m.width - offX + 1) : m.width;
        int nh = height < m.height + offY ? (m.height - offY + 1) : m.height;

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

    /**
     * Sets all attributes of the parameter to this current
     * instance.
     *
     * @param m the object that carries the new values for this object.
     */
    public void copy(Matrix m) {
        this.width = m.width;
        this.height = m.height;
        this.x = m.x;
        this.y = m.y;
        if (this.data == null) this.data = new int[m.data.length];
        System.arraycopy(m.data, 0, this.data, 0, m.data.length);
    }

    /**
     * Sets the content of the passed coordinate with the parameter
     * value. Note that this first checks if coords are in bounds.
     *
     * @param x the X coordinate to replace the content.
     * @param y the Y coordinate to replace the content.
     * @param value the new value to live in the passed coordinates.
     */
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

    /**
     * Checks if the coordinate in params are inside the bounds
     * of this Matrix.
     *
     * @param x the X coordinate to check.
     * @param y the Y coordinate to check.
     * @return true if the values are inside the bounds, false if not.
     */
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