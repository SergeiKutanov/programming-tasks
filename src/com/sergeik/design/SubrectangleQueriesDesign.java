package com.sergeik.design;

/**
 * Implement the class SubrectangleQueries which receives a rows x cols rectangle as a matrix of integers in
 * the constructor and supports two methods:
 *
 * 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
 *
 * Updates all values with newValue in the subrectangle whose upper left coordinate is (row1,col1) and bottom
 * right coordinate is (row2,col2).
 * 2. getValue(int row, int col)
 *
 * Returns the current value of the coordinate (row,col) from the rectangle.
 */
public class SubrectangleQueriesDesign {

    public static void main(String[] args) {
        SubrectangleQueries subrectangleQueries = new SubrectangleQueries(new int[][] {
                {1,2,1},
                {4,3,4},
                {3,2,1},
                {1,1,1}
        });
        assert 1 == subrectangleQueries.getValue(0,2);
        subrectangleQueries.updateSubrectangle(0,0,3,2,5);
        assert 5 == subrectangleQueries.getValue(0,2);
        assert 5 == subrectangleQueries.getValue(3,1);
        subrectangleQueries.updateSubrectangle(3,0,3,2,10);
        assert 10 == subrectangleQueries.getValue(3,1);
        assert 5 == subrectangleQueries.getValue(0,2);
    }

    static class SubrectangleQueries {

        int[][] data;

        public SubrectangleQueries(int[][] rectangle) {
            data = rectangle;
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            for (int r = row1; r <= row2; r++)
                for (int c = col1; c <= col2; c++)
                    data[r][c] = newValue;
        }

        public int getValue(int row, int col) {
            return data[row][col];
        }
    }

}
