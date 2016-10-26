package dog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Notice that this class extends JPanel
public class DogGUI extends JPanel{

    //Global variables for GUI components
    private JLabel dogNameLabel;
    private JLabel dogAgeLabel;

    private JTextField dogNameTextField;
    private JTextField dogAgeTextField;
    private JCheckBox puppyCheckBox;
    private JButton addDogToListButton;
    private JScrollPane listScrollPane;
    private JList<Dog> dogJList;
    private JButton deleteDogButton;
    private JButton quitButton;

    DefaultListModel<Dog> dogListModel;

    //Configure your GUI components in the constructor
    DogGUI() {

        //Set up GUI components here

        addComponents();
        configureListeners();
    }

    private void addComponents() {

        dogNameLabel = new JLabel("Dog's name");
        dogAgeLabel = new JLabel("Dog's age");

        dogNameTextField = new JTextField();
        dogAgeTextField = new JTextField();

        puppyCheckBox = new JCheckBox("Puppy?");

        addDogToListButton = new JButton("Add");

        //Create a scroll pane, and add the list to it.
        //This keeps the list within the size of the scroll pane.
        //otherwise, the list gets larger with every element added, and the
        //GUI window gets larger too. With a scroll pane, if there are more elements
        //than fit in the scroll pane, then scroll bars appear.
        dogJList = new JList<Dog>();
        listScrollPane = new JScrollPane(dogJList);

        deleteDogButton = new JButton("Delete");

        quitButton = new JButton("Quit");

        dogListModel = new DefaultListModel<Dog>();

        dogJList.setModel(dogListModel);

        //Configure JList to only allow user to select one item at a time
        //Default is multiple selections.

        dogJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Add some examples, remove for real program
        dogListModel.add(0, new Dog("Jess", 4, false));
        dogListModel.add(0, new Dog("Blue", 1, true));

        //Layout of components can be tricky. By default, components are laid out in a horizontal line, one after the other.
        // If that's not what you want, you'll need to create a layout manager to manage the position and size of your components.
        // This GUI uses a combination of two Layout managers.
        //BorderLayout arranges components in the north, south, east, west, or center.
        //BoxLayout can arrange components in vertical or horizontal stack(s).

        //To get the desired layout,
        //Create a BorderLayout for the whole JPanel.
        //Create a JPanel for the data entry fields - the JTextFields, JLabels, JCheckBox and use BoxLayout to arrange these components
        //The BoxLayout.Y_AXIS parameter specifies a vertical stack. See docs for more on
        //using BoxLayout.
        //And create another JPanel for the Quit and Delete buttons, Use another BoxLayout to arrange these buttons in a horizontal line.


        //The window as a whole uses a BorderLayout.
        BorderLayout layoutManager = new BorderLayout();
        setLayout(layoutManager);

        //Create a new panel for the data entry components. Use a BoxLayout to manage
        //the layout of these components
        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new BoxLayout(dataEntryPanel, BoxLayout.Y_AXIS));

        //Add the components to dataEntryPanel...
        dataEntryPanel.add(dogNameLabel);
        dataEntryPanel.add(dogNameTextField);
        dataEntryPanel.add(dogAgeLabel);
        dataEntryPanel.add(dogAgeTextField);
        dataEntryPanel.add(puppyCheckBox);
        dataEntryPanel.add(addDogToListButton);

        //And then add dataEntryPanel to the main JPanel window, in the north (at the top)
        add(dataEntryPanel, BorderLayout.NORTH);

        //Add the scroll pane in the middle. By default, center components will take up all of the remaining
        //space in the GUI window, so we don't need to set the size.
        add(listScrollPane, BorderLayout.CENTER);   //Which contains the dogJList

        //And make another panel to contain the quit and delete buttons
        //this uses a horizontal BoxLayout
        //Add components to this JPanel...
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(deleteDogButton);
        buttonPanel.add(quitButton);
        //And then add the JPanel to the South, or bottom, of the window.
        add(buttonPanel, BorderLayout.SOUTH);


    }

    private void configureListeners() {
        addDogToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read data from JTextFields and JCheckBox
                String dogName = dogNameTextField.getText();

                //Validation - make sure user enters something for the name. Trim whitespace and then check length is more than 0
                if (dogName.trim().length() == 0) {
                    JOptionPane.showMessageDialog(DogGUI.this, "Please enter a name");
                    return;
                }

                double dogAge;

                // Basic validation - check that age is a positive number
                try {
                    dogAge = Double.parseDouble(dogAgeTextField.getText());
                    if (dogAge < 0) {
                        JOptionPane.showMessageDialog(DogGUI.this, "Enter a positive number for age");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(DogGUI.this, "Enter a number for age");
                    return;
                }

                boolean isPuppy = puppyCheckBox.isSelected();

                // Create Dog and add to JList's model
                Dog newDog = new Dog(dogName, dogAge, isPuppy);
                dogListModel.addElement(newDog);

                //And clear the two JTextFields
                dogNameTextField.setText("");
                dogAgeTextField.setText("");

            }
        });

        deleteDogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask the JLIST what Dog is selected
                // Notice since we've used generic types, setSelectedValue returns a Dog.
                // Without generic types, it would return an Object, and we'd have to cast it.
                Dog toDelete = dogJList.getSelectedValue();
                // Delete this Dog from the MODEL
                dogListModel.removeElement(toDelete);
            }
        });


        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }


    public static void main(String[] args) {

        //Create a frame - the GUI window
        JFrame dogFrame = new JFrame("Pet survey");
        //configure the JFrame / GUI window
        //dogFrame.setSize(350, 200);
        dogFrame.setVisible(true);
        dogFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        dogFrame.setPreferredSize(new Dimension(400, 300));

        //Create an object from this class - it's a JPanel,  which is a GUI component
        DogGUI panel = new DogGUI();

        //And add it to the JFrame
        dogFrame.add(panel);

        dogFrame.pack();

    }
}