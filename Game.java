package com.company;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Random;

public class Game extends Parent{
    private boolean runningPhase = false;   //if false -> phase of placing ships
                                            //if true ->  phase of shooting
    private int shipsToPlace = 5;
    private Board playerBoard, enemyBoard;
    private boolean enemyTurn = false;

    private Random random = new Random();

    public Parent gameplay() {
        BorderPane borderPane = new BorderPane();                   //using panes for placing things manually
        borderPane.setPrefSize(600,800);

        borderPane.setRight(new Text("RIGHT PANEL: CONTROLS"));

        //handlers for boards//

        enemyBoard = new Board(true, event -> {             //supplying a handler that is a child of MouseEvent (can be any MouseEvent),
                                                                        //waiting for click
            if(!runningPhase) {                                         //we don't want to do anything with enemyBoard before the game starts
                return;
            }

            Square square = (Square) event.getSource();                 //obtaining a source of the event which is the clicked square
            if(square.wasShot) {                                        //if the square was already shot -> do nothing
                return;
            }

            enemyTurn = !square.shoot();                                //we shot the square and get result from the shot
                                                                        //if we shot a part of ship that returns true
                                                                        //negate that, so we actually get false
                                                                        //and that allows to shoot again

            if(enemyBoard.ships == 0) {                                 //WIN
                System.out.println("YOU WIN!");
                System.exit(0);
            }

            if(enemyTurn) {
                enemyMove();
            }
        });

        playerBoard = new Board(false, event -> {
           if(runningPhase) {
               return;
           }

            Square square = (Square) event.getSource();
           if(playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), square.x, square.y)) {        //checking if we can place ship in clicked square
                                                                                                                                            //Mouse left click -> placing vertically
                                                                                                                                            //Mouse right click -> placing horizontally
               if(--shipsToPlace == 0) {            //firstly decrease number of ships, then compare the number to 0
                   startGame();
               }
           }
        });

        VBox vBox = new VBox(50, enemyBoard, playerBoard);  //50 means distance between boards
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);                             //positioning
        
        return borderPane;
    }

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Square square = playerBoard.getSquare(x, y);
            if(square.wasShot) {

                continue;
            }

            enemyTurn = square.shoot();         //if true computer will take another shoot

            if(playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private void startGame() {
        //placing enemy ships
        int type = 5;

        while(type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if(enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {       //if Math.random returns less than 0.5 ship is placed vertically,
                type --;                                                                       //otherwise ship is placed horizontally
            }
        }
        runningPhase = true;            //signify that the setup phase has ended -> starting shooting phase
    }
}
