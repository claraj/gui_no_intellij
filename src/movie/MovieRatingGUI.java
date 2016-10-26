package movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MovieRatingGUI extends JPanel {

    private JLabel nameLabel, ratingLabel;
    private JComboBox<String> ratingComboBox;
    private JTextField nameTextField;
    private JButton submitButton;


    MovieRatingGUI() {

        nameLabel = new JLabel("Enter name of movie");
        ratingLabel = new JLabel("Enter rating");
        nameTextField = new JTextField();
        ratingComboBox = new JComboBox<>();
        submitButton = new JButton("Submit");

        configureButton();
        configureRatingsComboBox();

        //Use a GridLayout to arrange components

        setLayout(new GridLayout(3, 2));   //3 rows, 2 columns

        add(nameLabel);
        add(nameTextField);
        add(ratingLabel);
        add(ratingComboBox);
        add(submitButton);


    }


    // Add ratings 1 to 5 to the JComboBox.
    private void configureRatingsComboBox() {

        for (int x = 1 ; x <= 5 ; x++ ) {
            if (x == 1) {
                ratingComboBox.addItem(x + " star");
            } else {
                ratingComboBox.addItem(x + " stars");
            }
        }

    }

    private void configureButton() {

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Read name of movie
                String movieName = nameTextField.getText();
                //What rating did the user select? Need to convert to expected type
                String rating = ratingComboBox.getSelectedItem().toString();

                String result = "You gave " + movieName + "  " + rating;
                //Show result in dialog box
                JOptionPane.showMessageDialog(MovieRatingGUI.this, result);
            }
        });
    }


    public static void main(String[] args) {

        JFrame movieFrame = new JFrame();

        movieFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MovieRatingGUI mainPanel = new MovieRatingGUI();
        movieFrame.add(mainPanel);
        movieFrame.pack();

        movieFrame.setVisible(true);


    }
}

