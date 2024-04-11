import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AmusementPark extends JFrame {

    //initialization class
    Initialization init;

    //initialing graphics components
    JFrame frame = new JFrame("Amusement Park");
    JPanel gamePanel;
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
    boolean isP1Turn = true;
    Card level1Card;
    Card level2Card;
    Card level3Card;
    int cardWidth = 110; //ratio should 1/1.4
    int cardHeight = 154;



    AmusementPark() {
        startGame();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //frame.setSize(500,300);
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


        //pick2Button = new JButton("I want 2 coins with the same color");
        //pick3Button = new JButton("I want 3 coins with different colors");

        //twoBtn();
        //threeBtn();

        frame.setVisible(true);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(51, 77, 104));
        frame.add(gamePanel);



        //pick2Button.setFocusable(false);
        //buttonPanel.add(pick2Button);
        //pick3Button.setFocusable(false);
        //buttonPanel.add(pick3Button);
        //frame.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void startGame() {
        p1 = new Player();
        p2 = new Player();
        init = new Initialization();
        drawLevel1();
    }
    public void twoBtn () {
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
    }
    public void threeBtn () {
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
                            if (isP1Turn) {
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        if (greenBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);
                                        }
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                }
                            }
                            else {
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        if (greenBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                        }
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                }
                            }
                            isP1Turn = !isP1Turn;
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
    }
    public void drawLevel1() {
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    for (int i = init.level1.size() - 1, j=0; i > init.level1.size() - 5; i--, j++) {
                        level1Card = init.level1.get(i);
                        Image level1Img = new ImageIcon(getClass().getResource(level1Card.getImage1Path())).getImage();
                        g.drawImage(level1Img, 20 + (cardWidth + 5)*j, 320, cardWidth, cardHeight, null);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
