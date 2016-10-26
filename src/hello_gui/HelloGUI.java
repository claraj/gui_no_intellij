package hello_gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HelloGUI extends JPanel{

    JButton button;
    JLabel helloLabel;

    HelloGUI() {

        addComponents();
        addListeners();

    }

    private void addComponents() {

        button = new JButton("Click me!");
        add(button);

        helloLabel = new JLabel("Hello", JLabel.CENTER);
        add(helloLabel);

    }

    private void addListeners() {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helloLabel.setText("Click!");
            }
        });

    }

    public static void main(String[] args) {

        JFrame helloFrame = new JFrame();
        HelloGUI gui = new HelloGUI();
        helloFrame.setSize(200, 200);
        helloFrame.add(gui);
        helloFrame.setVisible(true);

    }
}