package todo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ToDoList extends JPanel {

    private JLabel instructionsLabel;
    private JTextField newToDoTextField;
    private JList<String> toDoList;
    private JScrollPane toDoListScrollPane;

    private DefaultListModel<String> listModel;     // A JList needs a model to provide data

    private JButton addToDoButton;

    ToDoList() {

        addComponents();   //Create components and add to this JPanel
        addListeners();    //Move event listener configuration into separate method, keep things tidier .

    }


    private void addComponents(){

        instructionsLabel = new JLabel("Type a new to do item in the box. Click on an item in the list to remove it");

        newToDoTextField = new JTextField();
        toDoList = new JList<String>();
        toDoListScrollPane = new JScrollPane(toDoList);

        addToDoButton = new JButton("Add new to do item");


        listModel = new DefaultListModel<String>();
        // Create a listModel. The list starts empty, so no data to add yet.
        // When you add data to the list, you actually need to add it to the list's * model *.

        //Configure the JList to use this model as its data source.
        toDoList.setModel(listModel);

        //Layout - main JFrame has BorderLayout

        setLayout(new BorderLayout());

        //Create a panel for the controls, use BoxLayout to arrange vertically
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
        controlsPanel.add(instructionsLabel);
        controlsPanel.add(newToDoTextField);
        controlsPanel.add(addToDoButton);

        //Add controlsPanel and the list scroll pane to the main JPanel
        add(controlsPanel, BorderLayout.NORTH);

        add(toDoListScrollPane, BorderLayout.CENTER);

    }



    private void addListeners() {

        //Need to listen for button clicked,
        //Read the text in the text box and add this to the list.

        addToDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newToDo = newToDoTextField.getText();
                newToDo = newToDo.trim(); //remove whitespace

                //Check to see if the JTextField is empty - if so, ignore.
                if (newToDo.length() == 0) {
                    return;
                }

                listModel.addElement(newToDo);  //Add new item to the JList's MODEL.
                newToDoTextField.setText("");  //Clear the JTextField
            }
        });

        //And, listen for the list being clicked on, which should remove the selected item.

        toDoList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //What was selected?
                int selectedIndex = toDoList.getSelectedIndex(); //Ask the LIST what is selected
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);  //Remove this item from the MODEL.
                }
            }
        });
    }


    public static void main(String[] args) {

        JFrame todoFrame = new JFrame();

        todoFrame.setPreferredSize(new Dimension(500, 500));
        todoFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ToDoList mainPanel = new ToDoList();
        todoFrame.add(mainPanel);
        todoFrame.pack();

        todoFrame.setVisible(true);

    }

}
