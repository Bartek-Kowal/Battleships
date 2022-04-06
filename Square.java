package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
    public int x, y;
    public Ship ship = null;            // NULL means that square doesn't contain a ship part
    public boolean wasShot = false;
    private Board board;                //board is parent of square

    public Square(int x, int y, Board board) {
        super(30,30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

//    public static Square getSquare(int x, int y) {
//        return (Square)((HBox)rows.getChildren().get(y)).getChildren().get(x);
//    }

    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);

        if(ship!=null) {                    //if ship is not pointing at NULL that means in that square is some part of ship
            ship.hit();                     //hp--
            setFill(Color.RED);
            if(!ship.isAlive()) {
                this.board.ships--;
                setFill(Color.DARKRED);

                for (Square neighbors : this.board.getNeighbors(x, y)) {
                    neighbors.setFill(Color.BLACK);
                }
            }
            return true; //we hit the ship
        }
        return false; //miss
    }

}
