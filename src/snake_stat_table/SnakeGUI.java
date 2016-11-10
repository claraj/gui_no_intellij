package snake_stat_table;

import javax.swing.*;
import java.awt.*;


public class SnakeGUI extends JPanel{

    JTable snakeTable;

    SnakeGUI() {

        addComponents();

        SnakeDataModel dataModel = new SnakeDataModel();
        snakeTable.setModel(dataModel);

    }

    private void addComponents() {

        snakeTable = new JTable();

        //Use BorderLayout and add component in center so table takes up all available space

        setLayout(new BorderLayout());

        add(snakeTable, BorderLayout.CENTER);
    }
    

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        SnakeGUI gui = new SnakeGUI();
        frame.add(gui);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.pack();
        frame.setVisible(true);

    }
}