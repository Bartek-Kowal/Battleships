package com.company;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board extends Parent {
    public VBox rows = new VBox();          // each row is a vertical box
    private boolean enemyBoard = false;     // flag whose board
    public int ships = 5;

    public Board(boolean enemyBoard, EventHandler<? super MouseEvent> handler) {    //handler used for mouseclick on squares
        this.enemyBoard = enemyBoard;
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Square square = new Square(x, y, this);
                square.setOnMouseClicked(handler);
                row.getChildren().add(square);      // "populating" row with squares
            }
            rows.getChildren().add(row);            // adding row to the list of rows
                                                    // getting 10x10 board
        }
        getChildren().add(rows);                    // adding rows to the children to display window with javaFX
    }

    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;

            if (ship.vertical) {                            //VERTICAL ORIENTATION
                for (int i = y; i < y + length; i++) {
                    Square square = getSquare(x, i);
                    square.ship = ship;                     // assigning part of a ship to the square
                    if (!enemyBoard) {                      // flag to make sure we place ships on our board, not enemy
                        square.setFill(Color.WHITE);
                        square.setStroke(Color.GREEN);
                    }
                }
            } else {                                        //HORIZONTAL ORIENTATION
                for (int i = x; i < x + length; i++) {
                    Square square = getSquare(i, y);
                    square.ship = ship;
                    if (!enemyBoard) {
                        square.setFill(Color.WHITE);
                        square.setStroke(Color.GREEN);
                    }
                }
            }
            return true;        //ship placed -> true
        }
        return false;           //ship didn't place -> false
    }

    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.type;

        if (ship.vertical) {                                //VERTICAL ORIENTATION
                        //checks for validity and emptiness//
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i)) {                  //if not ValidPoint return false -> not being able to place ship there
                    return false;
                }

                Square square = getSquare(x, i);            //if it is obtaining the square(x, i)
                if (square.ship != null) {                  //checking if there is a part of a ship placed in that square (!=null tells that there's a part of ship in that square)
                    return false;
                }
                        //checks for neighbors//
                for (Square neighbour : getNeighbors(x, i)) {       //checking all neighbors
                    if (!isValidPoint(x, i)) {
                        return false;
                    }

                    if (neighbour.ship != null) {
                        return false;
                    }
                }
            }
        } else {                                            //HORIZONTAL ORIENTATION
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y)) {
                    return false;
                }

                Square square = getSquare(i, y);
                if (square.ship != null) {
                    return false;
                }

                for (Square neighbour : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y)) {
                        return false;
                    }

                    if (neighbour.ship != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Square[] getNeighbors(int x, int y) {
        Point2D[] point2DS = new Point2D[] {            //creating points with coordinates around square
                new Point2D(x-1, y),                 //placing them inside the array of points
                new Point2D(x+1, y),
                new Point2D(x, y-1),
                new Point2D(x, y+1),
                new Point2D(x+1,y+1),
                new Point2D(x-1,y+1),
                new Point2D(x+1,y-1),
                new Point2D(x-1,y-1)
        };
        List<Square> neighbors = new ArrayList<Square>();                    //list of neighbors for storing squares

        for(Point2D p : point2DS) {                                     //for all 8 points
            if(isValidPoint(p)) {                                       //checking if they are valid
                neighbors.add(getSquare((int)p.getX(), (int)p.getY())); //if they are we adding them to the neighbors list
            }
        }
        return neighbors.toArray(new Square[0]);                        //returning list as a array
    }

    public Square getSquare(int x, int y) {
        return (Square)((HBox)rows.getChildren().get(y)).getChildren().get(x);      // lots of conversions because Square returns nodes
        // 1.getting children of the row
        // 2.casting them to HBox so that we can get y and x coordinates
        // 3.casting to Square and return
    }

    //method overloading (two same names for methods but with different types of parameters)//

    private  boolean isValidPoint(Point2D point2D) {
        return isValidPoint(point2D.getX(), point2D.getY());
    }

    private boolean isValidPoint(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;        //it has to be inside the board
    }

}