package com.company;
import javax.swing.*;

public class GameBoard extends JFrame{

    DragShipPanel dragShipPanel = new DragShipPanel();

    ///////////////////////////////////////////////////////

    private int size = 10;
    private int field_size = 34;

    private JButton gameFieldGUI[][] = new JButton[10][10];
    private JPanel gamePanel = new JPanel();

    private GameField gameField;


    public GameBoard(GameField gameField)
    {
        this.add(dragShipPanel);
        this.setTitle("Battleships");
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /////////////////////////////////////////////////



    }
}
