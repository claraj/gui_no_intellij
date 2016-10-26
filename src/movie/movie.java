package movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;



//Notice that this class extends JPanel
public class movie extends JPanel{

    //Global variables for GUI components
    private JCheckBox catCheckBox;
    private JCheckBox fishCheckBox;
    private JCheckBox dogCheckBox;
    private JButton quitButton;
    private JLabel instructionsLabel;
    private JLabel surveyResultsLabel;

    private boolean fish, cat, dog;

    //Configure your GUI components in the constructor
    movie() {

        //Set up GUI components here
        instructionsLabel = new JLabel("Select the pets you have");

        catCheckBox = new JCheckBox("Cat");
        fishCheckBox = new JCheckBox("Fish");
        dogCheckBox = new JCheckBox("Dog");

        surveyResultsLabel = new JLabel("Results here");

        quitButton = new JButton("Quit");

        //and then add the components to your GUI
        add(instructionsLabel);
        add(catCheckBox);
        add(dogCheckBox);
        add(fishCheckBox);

        add(surveyResultsLabel);
        add(quitButton);

        //And, add listeners to all components that need to respond to user events
        catCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cat = catCheckBox.isSelected();
                updateResults();
            }
        });

        dogCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                dog = dogCheckBox.isSelected();
                updateResults();
            }
        });

        fishCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fish = fishCheckBox.isSelected();
                updateResults();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Show 'are you sure' dialog box

                int quit = JOptionPane.showConfirmDialog(movie.this,
                        "Are you sure you want to quit?", "Quit?",
                        JOptionPane.OK_CANCEL_OPTION);

                //Check which option user selected, quit if user clicked ok
                if (quit == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });



    }

    private void updateResults() {

        String hasDog = dog ? "a dog" : "no dogs";
        String hasCat = cat ? "a cat" : "no cats";

        String hasFish;
        if (fish == true) {
            hasFish = "a fish";
        } else {
            hasFish = "no fish";
        }

        String results = "You have " + hasCat + ", " + hasDog + " and " + hasFish;
        surveyResultsLabel.setText(results);

    }


    public static void main(String[] args) {

        //Create a frame - the GUI window
        JFrame petSurveyFrame = new JFrame("Pet survey");
        //configure the JFrame / GUI window
        petSurveyFrame.setSize(350, 200);
        petSurveyFrame.setVisible(true);
        petSurveyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        petSurveyFrame.setPreferredSize(new Dimension(400, 300));

        //Create an object from this class - it's a JPanel,  which is a GUI component
//        movie.movie surveyGUI = new movie.movie();
//
//        //And add it to the JFrame
//        petSurveyFrame.add(surveyGUI);
//
//        petSurveyFrame.pack();

    }
}