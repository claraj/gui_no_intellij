package snake_stat_database;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

/**
Provides data for th JTable in SnakeGUI
 */
public class SnakeDataModel extends AbstractTableModel {


    LinkedList<String> snakes;

    SnakeDataModel()  {
        //create and initialize the LinkedList

        snakes = new LinkedList<>();
        snakes.add("Cobra");
        snakes.add("Python");
        snakes.add("Boa Constrictor");
    }

    @Override
    public int getRowCount() {
        return snakes.size();   //one table row per item in list
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        //The first row should display the row number
        //the second row should display the name of a snake

        switch (columnIndex) {

            case 0: {
                return rowIndex;   //number the
            }

            case 1: {
                return snakes.get(rowIndex);
            }

            default: {
                return null;    //this shouldn't happen, but just in case
            }
        }
    }
}
