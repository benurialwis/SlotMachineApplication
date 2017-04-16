package slotmachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.*;

public class SlotMachine {
    
    public JFrame frame;
    public JPanel panelLeft, panelRight, panelGame, panelReels, panelSpin, panelInfo,
            panelCredit, panelBet, panelBtns, panelReel1, panelReel2, panelReel3;
    public JButton btnSpin, btnReset, btnStats, btnAddCoin, btnBetOne, btnBetMax;
    public JLabel lblCrediTxt, lblBetTxt, lblCreditVal, lblBetVal, lblInfoL1, lblInfoL2,
            lblReel1, lblReel2, lblReel3;
    public static int creditVal = 10;
    public static int betVal = 0;
    public int r1, r2, r3;
    public int winCredit = 0;
    public static int matchCount, winCount, lostCount, creditsWon, creditsLost;
    public float avg;
    String av;
    Statistics st = new Statistics();
    Symbol sy = new Symbol();
    Reel reel = new Reel();
    Reel reel1, reel2, reel3;
    DecimalFormat df = new DecimalFormat("#.00");


    /* Create JFrame and JPanels */
    public void setLayout() {
        frame = new JFrame("Slot Machine");
        frame.setLayout(new GridLayout(1, 2, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setResizable(true);
        frame.setVisible(true);
        
        panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(2, 1, 10, 5));
        frame.add(panelLeft);
        
        panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(3, 1, 10, 5));
        frame.add(panelRight);

        /* Panels in Left*/
        panelGame = new JPanel();
        panelGame.setLayout(new GridLayout(2, 1, 10, 5));
        panelLeft.add(panelGame);
        
        panelReels = new JPanel();
        panelReels.setLayout(new GridLayout(1, 3, 5, 5));
        panelReels.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelReels.setEnabled(false);
        panelGame.add(panelReels);
        
        panelSpin = new JPanel();
        panelGame.add(panelSpin);
        
        panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(3, 1, 5, 5));
        panelInfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),
                "Game Info"));
        panelLeft.add(panelInfo);

        /* Panels in Right*/
        panelCredit = new JPanel();
        panelCredit.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),
                "Credit Area"));
        panelCredit.setLayout(new GridLayout(2, 1, 5, 5));
        panelRight.add(panelCredit);
        
        panelBet = new JPanel();
        panelBet.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),
                "Bet Area"));
        panelBet.setLayout(new GridLayout(2, 1, 5, 5));
        panelRight.add(panelBet);
        
        panelBtns = new JPanel();
        panelRight.add(panelBtns);

        /* Panels inside panelReels*/
        panelReel1 = new JPanel();
        panelReel1.setBorder(new LineBorder(Color.black, 3));
        panelReels.add(panelReel1);
        
        panelReel2 = new JPanel();
        panelReel2.setBorder(new LineBorder(Color.black, 3));
        panelReels.add(panelReel2);
        
        panelReel3 = new JPanel();
        panelReel3.setBorder(new LineBorder(Color.black, 3));
        panelReels.add(panelReel3);
        
        panelReels.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent m) {
                super.mouseClicked(m);
                try{
                reel1.stopSpin();
                reel2.stopSpin();
                reel3.stopSpin();
                btnBetMax.setEnabled(true);
                btnBetOne.setEnabled(true);
                btnAddCoin.setEnabled(true);
                btnSpin.setEnabled(true);
                btnReset.setEnabled(true);
                btnStats.setEnabled(true);
                panelReels.setEnabled(false);
                showResults();
                }catch(NullPointerException e){
                   JOptionPane.showMessageDialog(null, "Click on Spin!", "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
    }

    /* Add Buttons */
    public void addButtons() {
        btnSpin = new JButton("Spin");
        panelSpin.add(btnSpin);
        btnSpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (betVal == 0) {
                    JOptionPane.showMessageDialog(null, "Place a Bet!", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    reel1 = new Reel(lblReel1, panelReel1);
                    reel2 = new Reel(lblReel2, panelReel2);
                    reel3 = new Reel(lblReel3, panelReel3);
                    reel1.start();
                    reel2.start();
                    reel3.start();
                    btnBetMax.setEnabled(false);
                    btnBetOne.setEnabled(false);
                    btnAddCoin.setEnabled(false);
                    btnSpin.setEnabled(false);
                    btnReset.setEnabled(false);
                    btnStats.setEnabled(false);
                    panelReels.setEnabled(true);
                    matchCount++;
                }
            }
        });
        
        btnReset = new JButton("Reset");
        panelBtns.add(btnReset);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                creditVal += betVal;
                betVal = 0;
                lblBetVal.setText(Integer.toString(betVal));
                lblCreditVal.setText(Integer.toString(creditVal));
            }
        });
        
        btnStats = new JButton("Statistics");
        panelBtns.add(btnStats);
        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                calcAverage();
                if (matchCount > 0) {
                    st.creatStatWindow(matchCount, winCount, lostCount, creditsWon, creditsLost, av);
                }
            }
        });
        
        btnAddCoin = new JButton("Add Coin");
        panelCredit.add(btnAddCoin);
        btnAddCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                creditVal += 1;
                
                lblCreditVal.setText(Integer.toString(creditVal));
            }
        });
        
        btnBetOne = new JButton("Bet One");
        panelBet.add(btnBetOne);
        btnBetOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (creditVal > 0) {
                    if (betVal == 3) {
                        JOptionPane.showMessageDialog(null, "Maximum Bet is 3!", "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        betVal += 1;
                        lblBetVal.setText(Integer.toString(betVal));
                        creditVal -= 1;
                        lblCreditVal.setText(Integer.toString(creditVal));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough Cedit!", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        btnBetMax = new JButton("Bet Max");
        panelBet.add(btnBetMax);
        btnBetMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (creditVal >= 3) {
                    if (betVal == 3) {
                        JOptionPane.showMessageDialog(null, "Maximum Bet is 3!", "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        creditVal += betVal;
                        betVal = 3;
                        lblBetVal.setText(Integer.toString(betVal));
                        creditVal -= 3;
                        lblCreditVal.setText(Integer.toString(creditVal));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough Cedit!", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        frame.setVisible(true);
    }

    /* Add Labels */
    public void addLabels() {
        lblCrediTxt = new JLabel("Credits: ");
        panelCredit.add(lblCrediTxt);
        
        lblCreditVal = new JLabel();
        lblCreditVal.setText(Integer.toString(creditVal));
        panelCredit.add(lblCreditVal);
        
        lblBetTxt = new JLabel("Bet: ");
        panelBet.add(lblBetTxt);
        
        lblBetVal = new JLabel();
        lblBetVal.setText(Integer.toString(betVal));
        panelBet.add(lblBetVal);
        
        lblInfoL1 = new JLabel();
        lblInfoL1.setText(" ");
        panelInfo.add(lblInfoL1);
        
        lblInfoL2 = new JLabel();
        lblInfoL2.setText(" ");
        panelInfo.add(lblInfoL2);
        
        lblReel1 = new JLabel();
        panelReel1.add(lblReel1);
        
        lblReel2 = new JLabel();
        panelReel2.add(lblReel2);
        
        lblReel3 = new JLabel();
        panelReel3.add(lblReel3);
        
    }

    /* Show Game Results*/
    public void showResults() {
        reel.setImageArray();
        r1 = reel1.getRand();
        r2 = reel2.getRand();
        r3 = reel3.getRand();
        int k = sy.compareTwoImages(r1, r2, r3);
        switch (k) {
            case 0:
                winCredit = reel.calculate(r1, 3, winCredit);
                creditVal += winCredit;
                lblInfoL1.setText("You Win!");
                lblInfoL2.setText("Credits won: " + winCredit);
                winCount++;
                creditsWon += winCredit;
                break;
            case 1:
                if (r1 == r2 || r1 == r3) {
                    winCredit = reel.calculate(r1, 2, winCredit);
                    creditVal += winCredit;
                } else if (r2 == r3) {
                    winCredit = reel.calculate(r2, 2, winCredit);
                    creditVal += winCredit;
                }
                lblInfoL1.setText("You Win!");
                lblInfoL2.setText("Credits won: " + winCredit);
                winCount++;
                creditsWon += winCredit;
                break;
            default:
                lblInfoL1.setText("You Lose!");
                lblInfoL2.setText("Credits won: 0");
                lostCount++;
                creditsLost += betVal;
                break;
        }
        lblCreditVal.setText(Integer.toString(creditVal));
        betVal = 0;
        lblBetVal.setText(Integer.toString(betVal));
        //panelReels.setEnabled(false);
    }

    /* Calculate credit Average */
    public void calcAverage() {
        if (matchCount > 0) {
            avg = (creditsWon - creditsLost) / matchCount;
            av = df.format(avg);
        } else {
            JOptionPane.showMessageDialog(null, "Zero matches played !", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        
        SlotMachine slotMachine = new SlotMachine();
        
        slotMachine.setLayout();
        slotMachine.addLabels();
        slotMachine.addButtons();
        
    }
    
}
