import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AmusementPark extends JFrame {

    //initialization class
    Initialization init;

    //initialing graphics components
    JFrame frame = new JFrame("Amusement Park");
    JPanel gamePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton pick3Button;
    JButton pick2Button;
    JRadioButton blackBtn;
    JRadioButton blueBtn;
    JRadioButton greenBtn;
    JRadioButton redBtn;
    JRadioButton whiteBtn;
    JCheckBox blackBox;
    JCheckBox blueBox;
    JCheckBox greenBox;
    JCheckBox redBox;
    JCheckBox whiteBox;
    ButtonGroup group2;
    Player p1;
    Player p2;


    AmusementPark() {
        startGame();

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose(); // Close the frame
                }
            }
        });


        pick2Button = new JButton("I want 2 coins with the same color");
        pick3Button = new JButton("I want 3 coins with different colors");

        pick2Button.addActionListener(new ActionListener() { //when the pick2Button is pressed:
            public void actionPerformed(ActionEvent e) {
                buttonPanel.removeAll();

                JPanel radioPanel = new JPanel();
                blackBtn = new JRadioButton("Black");
                blackBtn.setFocusable(false);
                radioPanel.add(blackBtn);
                blueBtn = new JRadioButton("Blue");
                blueBtn.setFocusable(false);
                radioPanel.add(blueBtn);
                greenBtn = new JRadioButton("Green");
                greenBtn.setFocusable(false);
                radioPanel.add(greenBtn);
                redBtn = new JRadioButton("Red");
                redBtn.setFocusable(false);
                radioPanel.add(redBtn);
                whiteBtn = new JRadioButton("White");
                whiteBtn.setFocusable(false);
                radioPanel.add(whiteBtn);

                group2 = new ButtonGroup();
                group2.add(blackBtn);
                group2.add(blueBtn);
                group2.add(greenBtn);
                group2.add(redBtn);
                group2.add(whiteBtn);

                buttonPanel.add(radioPanel);

                frame.revalidate();
                frame.repaint();
            }
        });


        pick3Button.addActionListener(new ActionListener() { //when the pick3Buttons is pressed:
            public void actionPerformed(ActionEvent e) {
                buttonPanel.removeAll();

                JPanel checkBoxPanel = new JPanel();
                blackBox = new JCheckBox("Black");
                blackBox.setFocusable(false);
                checkBoxPanel.add(blackBox);
                blueBox = new JCheckBox("Blue");
                blueBox.setFocusable(false);
                checkBoxPanel.add(blueBox);
                greenBox = new JCheckBox("Green");
                greenBox.setFocusable(false);
                checkBoxPanel.add(greenBox);
                redBox = new JCheckBox("Red");
                redBox.setFocusable(false);
                checkBoxPanel.add(redBox);
                whiteBox = new JCheckBox("White");
                whiteBox.setFocusable(false);
                checkBoxPanel.add(whiteBox);

                int maxSelections = 3;
                ItemListener itemListener = new ItemListener() {
                    private int selectedCount = 0;

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        JCheckBox source = (JCheckBox) e.getSource();
                        if (source.isSelected()) {
                            selectedCount++;
                        } else {
                            selectedCount--;
                        }

                        // Disable other checkboxes when 3 are selected
                        for (Component component : checkBoxPanel.getComponents()) {
                            if (component instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) component;
                                checkBox.setEnabled(selectedCount < maxSelections || checkBox.isSelected());
                            }
                        }
                        if (selectedCount == maxSelections) {
                            if(blackBox.isSelected() && blueBox.isSelected() && greenBox.isSelected()) {
                                System.out.println("1");
                            }
                            else if(blackBox.isSelected() && blueBox.isSelected() && redBox.isSelected()) {

                            }
                            else if(blackBox.isSelected() && blueBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(blackBox.isSelected() && greenBox.isSelected() && redBox.isSelected()) {

                            }
                            else if(blackBox.isSelected() && greenBox.isSelected() && whiteBox.isSelected()) {


                            }
                            else if(blackBox.isSelected() && redBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(blueBox.isSelected() && greenBox.isSelected() && redBox.isSelected()) {


                            }
                            else if(blueBox.isSelected() && greenBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(blueBox.isSelected() && redBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(greenBox.isSelected() && redBox.isSelected() && whiteBox.isSelected()) {

                            }
                        }
                    }
                };

                blackBox.addItemListener(itemListener);
                blueBox.addItemListener(itemListener);
                greenBox.addItemListener(itemListener);
                redBox.addItemListener(itemListener);
                whiteBox.addItemListener(itemListener);

                buttonPanel.add(checkBoxPanel);

                frame.revalidate();
                frame.repaint();
            }
        });

        frame.setVisible(true);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(51, 77, 104));
        frame.add(gamePanel);

        pick2Button.setFocusable(false);
        buttonPanel.add(pick2Button);
        pick3Button.setFocusable(false);
        buttonPanel.add(pick3Button);
        frame.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void startGame() {
        p1 = new Player();
        p2 = new Player();
        init = new Initialization();
    }
}
