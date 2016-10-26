package starting_salary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

//Notice that this class extends JPanel
public class Salary extends JPanel{


    private JLabel jobCategoryLabel, jobSubCategoryLabel, jobSelectedLabel, salaryLabel;
    private JComboBox<String> subCategoryComboBox;
    private JComboBox<String> categoryComboBox;

    //These HashMaps will hold data displayed by the JComboBoxes, and the resultLabel
    private static HashMap<String, String[]> categories = new HashMap<String, String[]>();
    private static HashMap<String, Integer> salaries = new HashMap<String, Integer>();


    //Configure your GUI components in the constructor
    Salary() {

        configureComponents();
        addEventListeners();

    }


    private void configureComponents() {
        jobCategoryLabel = new JLabel("Select your job category");
        jobSubCategoryLabel = new JLabel("Select sub-category");
        jobSelectedLabel = new JLabel("Salary data...");     // Placeholder
        salaryLabel = new JLabel("...displayed here");      //Placeholder
        categoryComboBox = new JComboBox<>();
        subCategoryComboBox = new JComboBox<>();

        configureComboBoxes();

        //Use a GridLayout for this program. Lay components in a grid with specified rows and columns.

        setLayout(new GridLayout(3, 2));  //3 rows and 2 columns

        // The items added will be added to the grid from the top left,
        // filling the number of rows and columns specified.
        add(jobCategoryLabel);
        add(jobSubCategoryLabel);
        add(categoryComboBox);
        add(subCategoryComboBox);
        add(jobSelectedLabel);
        add(salaryLabel);


    }

    private void configureComboBoxes() {

        //Add the keys in the categories HashMap to the first JComboBox, categoryComboBox.

        for (String cat : categories.keySet()) {
            categoryComboBox.addItem(cat);
        }

        //For the currently selected category, add its sub categories to the subCategoryComboBox

        // What is currently selected?
        String selectedCategory = categoryComboBox.getSelectedItem().toString();

        // Get the subcategories for this selection
        String[] subCategories = categories.get(selectedCategory);

        for (String subCat : subCategories) {
            subCategoryComboBox.addItem(subCat);
        }

    }

    private void addEventListeners() {

        // Selection made from first JComboBox for job category
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Clear the subCategoryCombobox
                // Update with new subcategories, based on the selected category

                subCategoryComboBox.removeAllItems();

                String selectedCategory = categoryComboBox.getSelectedItem().toString();

                String[] subCategories = categories.get(selectedCategory);

                for (String subCat : subCategories) {
                    subCategoryComboBox.addItem(subCat);
                }

            }
        });



        subCategoryComboBox.addActionListener(new ActionListener() {

            //Selection made from the second JComboBox, for the subcategory
            @Override
            public void actionPerformed(ActionEvent e) {
                //Retrieve and display salary data for this subcategory. If nothing is selected, this returns null
                String subCat = (String)subCategoryComboBox.getSelectedItem();

                //Must check to see if something was selected, or get NullPointerException.
                if (subCat != null) {
                    int salary = salaries.get(subCat);
                    jobSelectedLabel.setText("You selected " + subCat);
                    salaryLabel.setText("Typical starting salary $" + salary);
                }

                //Another way to check is to use the getSelectedIndex() method. This returns -1 if nothing is selected
                //This is perhaps less useful here because we need to know what was selected, not where it is in the list.
                //Example code...
                /*
                if (subCategoryComboBox.getSelectedIndex() != -1) {
                    String subCat = subCategoryComboBox.getSelectedItem().toString();
                    int salary = salaries.get(subCat);
                    String result = "A typical starting salary for a " + subCat + " is about $" + salary;
                    resultLabel.setText(result);
                }
                */

            }
        });
    }


    private static void addSalaryData() {

        categories.put("Software Developer", new String[] { "C# Developer", "Java Developer", "Python Developer" } );
        categories.put("Network Administrator", new String[] { "Linux Admin", "Windows Admin" } );

        salaries.put("C# Developer", 50000);
        salaries.put("Java Developer", 50000);
        salaries.put("Python Developer", 47000);
        salaries.put("Linux Admin", 45000);
        salaries.put("Windows Admin", 45000);

    }


    public static void main(String[] args) {

        addSalaryData();  //configure the HashMaps that provide data to the JComboBoxes

        //Create a frame - the GUI window
        JFrame salaryFrame = new JFrame("Salary Calculator");
        //configure the JFrame / GUI window
        salaryFrame.setVisible(true);
        salaryFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create an object from this class - it's a JPanel,  which is a GUI component
        Salary salaryPanel = new Salary();

        //And add it to the JFrame
        salaryFrame.add(salaryPanel);

        salaryFrame.pack();

        salaryFrame.setVisible(true);
    }
}