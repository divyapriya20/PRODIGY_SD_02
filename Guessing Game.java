import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI {
    private int randomNumber;
    private int attempts;

    public GuessingGameGUI() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;

        JFrame frame = new JFrame("Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setResizable(false);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("guess.jpg"); 
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setOpaque(false);
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Welcome to the Guessing Game!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(titleLabel);

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionLabel.setForeground(Color.YELLOW);
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(instructionLabel);

        JTextField guessField = new JTextField(10);
        guessField.setMaximumSize(new Dimension(200, 30));
        guessField.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setBackground(new Color(0, 153, 255));
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.setBorder(BorderFactory.createRaisedBevelBorder());
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        gamePanel.add(guessButton);

        JLabel feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 14));
        feedbackLabel.setForeground(Color.YELLOW);
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedbackLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        gamePanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        gamePanel.add(feedbackLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    attempts++;
                    if (guess < 1 || guess > 100) {
                        feedbackLabel.setText(" Please enter a number between 1 and 100.");
                    } else if (guess < randomNumber) {
                        feedbackLabel.setText(" Too low! Try again.");
                    } else if (guess > randomNumber) {
                        feedbackLabel.setText(" Too high! Try again.");
                    } else {
                        feedbackLabel.setText(" Congratulations!! You guessed it in " + attempts + " attempts! ");
                        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        feedbackLabel.setForeground(Color.GREEN);
                        guessButton.setEnabled(false);

                        JOptionPane.showMessageDialog(frame, "****** YOU WON! ******\nIt took you " + attempts + " attempts.", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText(" Invalid input. Please enter a number.");
                }
                guessField.setText("");
            }
        });

        backgroundPanel.add(gamePanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GuessingGameGUI();
    }
}
