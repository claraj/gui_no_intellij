package visa_card_validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditCardValidatorGUI extends JPanel {

    private JTextField cardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JLabel validateMessageLabel;
    private JLabel instructionsLabel;

    CreditCardValidatorGUI() {

        cardNumberTextField = new JTextField();
        instructionsLabel = new JLabel("Enter card number");
        validateButton = new JButton("Validate");
        quitButton = new JButton("Quit");
        validateMessageLabel = new JLabel("Valid or not valid?");

        //configure layout manager to arrange components vertically
        BoxLayout layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layoutManager);

        //And add components
        add(instructionsLabel);
        add(cardNumberTextField);
        add(validateMessageLabel);
        add(validateButton);
        add(quitButton);

        //Listener for validate button
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ccNumber = cardNumberTextField.getText();
                boolean valid = isVisaCreditCardNumberValid(ccNumber);
                if (valid) {
                    validateMessageLabel.setText("Credit card number is valid");
                } else {
                    validateMessageLabel.setText("Credit card number is NOT valid");
                }

            }
        });

        //and for quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Show 'are you sure' dialog box
                int quit = JOptionPane.showConfirmDialog(CreditCardValidatorGUI.this,
                        "Are you sure you want to quit?", "Quit?",
                        JOptionPane.OK_CANCEL_OPTION);

                //Check which option user selected, quit if user clicked ok
                if (quit == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }


    private boolean isVisaCreditCardNumberValid(String cc) {
        if (!cc.startsWith("4")) {
            return false;
        }

        if (cc.length() != 16) {
            return false;
        }


        int sum = 0;

        for (int i = 0; i < 16 ; i++ ) {
            int thisDigit = Integer.parseInt((cc.substring(i, i+1)));
            if (i % 2 == 1) {
                sum = sum + thisDigit;
            } else {
                int doubled = thisDigit * 2;
                if (doubled > 9 ) {
                    int toAdd = 1 + (doubled % 10);
                    sum = sum + toAdd;
                } else {
                    sum = sum + (thisDigit * 2);
                }
            }
        }

        if (sum % 10 == 0) {
            return true;
        }

       return false;
    }

    public static void main(String[] args) {

        //Create a frame - the GUI window
        JFrame ccValidatorFrame = new JFrame("Credit Card Validator");

        //configure the JFrame / GUI window
        ccValidatorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create an object from this class - it's a JPanel,  which is a GUI component
        CreditCardValidatorGUI ccGui = new CreditCardValidatorGUI();

        //And add it to the JFrame
        ccValidatorFrame.add(ccGui);

        //Size the frame to fit the components it contains
        ccValidatorFrame.pack();

        //And show the window
        ccValidatorFrame.setVisible(true);


    }
}
