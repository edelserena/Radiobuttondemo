import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RadioButtonDemo - Java Swing Assignment
 * 
 * This app uses JRadioButtons to switch between different pet images.
 * I used BorderLayout to keep things clean.
 */
public class RadioButtonDemo extends JFrame {
    private JLabel imageLabel;
    private ButtonGroup group;

    public RadioButtonDemo() {
        // Set up the window basics
        setTitle("RadioButtonDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // West Panel: Vertical stack for our radio buttons
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        group = new ButtonGroup(); // Make sure only one is picked at a time!

        String[] pets = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
        for (String pet : pets) {
            JRadioButton radioButton = new JRadioButton(pet);
            radioButton.setActionCommand(pet);
            radioButton.addActionListener(new PetActionListener());
            group.add(radioButton);
            radioPanel.add(radioButton);
            
            // Start with Bird selected by default
            if (pet.equals("Bird")) {
                radioButton.setSelected(true);
            }
        }

        // Add the buttons panel to the WEST (left side)
        add(radioPanel, BorderLayout.WEST);

        // Center: Where the actual pet image goes
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        updateImage("Bird"); // Load initial image
        add(imageLabel, BorderLayout.CENTER);

        // Window size and position stuff
        pack();
        setSize(500, 400); // A bit more room for the images
        setLocationRelativeTo(null); // Center on screen
    }

    /**
     * Logic to load and resize the image so it actually fits in the window.
     * I added scaling to avoid images being too big or too small.
     */
    private void updateImage(String petName) {
        String fileName = petName.toLowerCase() + ".jpg";
        ImageIcon icon = new ImageIcon(fileName);
        
        // Let's check if we actually found the image
        if (icon.getImage() != null && icon.getIconWidth() > 0) {
            // Scale it down to fit reasonably
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImg));
            imageLabel.setText(""); // Clear "Image not found" text
        } else {
            // Something went wrong... probably a missing file
            imageLabel.setIcon(null);
            imageLabel.setText("Oops! Missing: " + fileName);
        }
    }

    /**
     * Inner class for handling the radio button clicks.
     * The JOptionPane pops up as soon as a pet is selected.
     */
    private class PetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String petName = e.getActionCommand();
            updateImage(petName); // Switch the pic
            
            // Pop-up for confirmation (Requirement 3)
            JOptionPane.showMessageDialog(RadioButtonDemo.this, "You selected the " + petName + "!");
        }
    }

    // Standard main method, used SwingUtilities just like in the labs
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RadioButtonDemo().setVisible(true);
        });
    }
}
