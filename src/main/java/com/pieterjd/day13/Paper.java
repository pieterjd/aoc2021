package com.pieterjd.day13;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Paper {
    List<Point> dots = new ArrayList<>();


    public void addPoint(Point p) {
        dots.add(p);
    }

    public Paper foldOnRow(int row) {
        Paper topHalf = new Paper();
        stream().filter(p -> p.getRow() < row).forEach(p -> topHalf.addPoint(p));

        int nrRowsToFold = getMaxRowNr() - row;
        for (int i = 1; i <= nrRowsToFold; i++) {
            int rowToFold = row + i;
            for (int j = 0; j < getNrCols(); j++) {
                Point pointToFold = getPoint(rowToFold, j);
                if (pointToFold != null && pointToFold.isDot()) {

                    Point p = topHalf.getPoint(row-i, j);
                    if (p != null) {
                        p.setDot(true);
                    } else {
                        p = new Point(row - i, j, true);
                        topHalf.addPoint(p);
                    }


                }

            }
        }
        return topHalf;
    }

    public long nrOfDots() {
        return dots.stream().filter(p -> p.isDot()).count();
    }

    public Paper apply(FoldInstruction fi) {
        System.out.println(fi);
        return fi.isRowFold() ? foldOnRow(fi.getValue()) : foldOnCol(fi.getValue());
    }

    public Paper foldOnCol(int col) {
        Paper leftHalf = new Paper();
        stream().filter(p -> p.getCol() < col).forEach(p -> leftHalf.addPoint(p));

        int nrColsToFold = getMaxColNr() - col;
        for (int i = 0; i < getNrRows(); i++) {
            for (int j = 1; j <= nrColsToFold; j++) {
                int colToFold = col + j;
                Point pointToFold = getPoint(i, colToFold);
                if (pointToFold != null && pointToFold.isDot()) {
                    Point p = leftHalf.getPoint(i, col - j);
                    if (p != null) {
                        p.setDot(true);
                    } else {
                        p = new Point(i, col - j, true);
                        leftHalf.addPoint(p);
                    }
                }

            }
        }
        return leftHalf;
    }

    private boolean containsPoint(int row, int col) {
        return getPoint(row, col) != null;
    }

    public Point getPoint(int row, int col) {
        return stream().filter(p -> p.getRow() == row && p.getCol() == col).findAny().orElse(null);
    }

    public boolean addAll(Collection<? extends Point> c) {
        return dots.addAll(c);
    }

    public Stream<Point> stream() {
        return dots.stream();
    }

    public String toString() {
        String notDotChar = " ";
        int nrCols = getNrCols();
        int nrRows = getNrRows();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                Point p = getPoint(i, j);
                if (p != null) {
                    sb.append(p.isDot() ? "#" : notDotChar);
                }
                else sb.append(notDotChar);
                //else System.out.println(String.format("NPE for point at row %d col %d", i, j));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int getNrCols() {
        return getMaxColNr() == -1 ? 0 : getMaxColNr() + 1;
    }

    private int getNrRows() {
        return getMaxRowNr() == -1 ? 0 : getMaxRowNr() + 1;
    }

    private int getMaxRowNr() {
        int nrRows = dots.stream()
                .mapToInt(p -> p.getRow())
                .max().orElse(-1);
        return nrRows;
    }

    private int getMaxColNr() {
        int nrRows = dots.stream()
                .mapToInt(p -> p.getCol())
                .max().orElse(-1);
        return nrRows;
    }

    public void fillWithEmptyDots() {
        System.out.println(String.format("filling %d by %d with empty dots", getNrRows(), getNrCols()));
        for (int i = 0; i < getNrRows(); i++) {
            for (int j = 0; j < getNrCols(); j++) {
                if (!containsPoint(i, j)) {
                    Point blank = new Point(i, j, false);
                    addPoint(blank);
                }
            }
        }
    }
}
