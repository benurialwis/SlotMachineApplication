package slotmachine;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Statistics {

    JLabel lblMatchTxt, lblMatchVal, lblWonMTxt, lblWonMVal, lblWonCTxt, lblWonCVal, lblLostMTxt,
            lblLostMVal, lblLostCTxt, lblLostCVal, lblAvgTxt, lblAvgVal;

    /**
     * A GridLayout of 2 columns and 6 rows, is used for the Statistic Window to
     * make it more readable and user friendly Labels are used to show the texts
     * and numeric details making it easy to implement Save Statistics button is
     * at the bottom of the window making it more user friendly
     */
    public void creatStatWindow(int matchCount, int winCount, int lostCount, int creditsWon, int creditsLost, String av) {
        JFrame stats = new JFrame();
        

        stats.setDefaultCloseOperation(stats.DISPOSE_ON_CLOSE);
        stats.setSize(450, 300);
        stats.setResizable(true);
        

        JPanel sts = new JPanel();
        sts.setLayout(new GridLayout(8, 2, 10, 10));
        stats.add(sts);

        lblMatchTxt = new JLabel("Number of Matches:");
        sts.add(lblMatchTxt);

        lblMatchVal = new JLabel(Integer.toString(matchCount));
        sts.add(lblMatchVal);

        lblWonMTxt = new JLabel("Matches Won: ");
        sts.add(lblWonMTxt);

        lblWonMVal = new JLabel(Integer.toString(winCount));
        sts.add(lblWonMVal);

        lblWonCTxt = new JLabel("Credits Won: ");
        sts.add(lblWonCTxt);

        lblWonCVal = new JLabel(Integer.toString(creditsWon));
        sts.add(lblWonCVal);

        lblLostMTxt = new JLabel("Matches Lost: ");
        sts.add(lblLostMTxt);

        lblLostMVal = new JLabel(Integer.toString(lostCount));
        sts.add(lblLostMVal);

        lblLostCTxt = new JLabel("Credits Lost: ");
        sts.add(lblLostCTxt);

        lblLostCVal = new JLabel(Integer.toString(creditsLost));
        sts.add(lblLostCVal);

        lblAvgTxt = new JLabel("Average: ");
        sts.add(lblAvgTxt);

        lblAvgVal = new JLabel(av);
        sts.add(lblAvgVal);

        JPanel pnl = new JPanel();
        sts.add(pnl);

        JButton btnSave = new JButton("Save");
        pnl.add(btnSave);
        
        stats.setVisible(true);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                writeData(matchCount, winCount, lostCount, creditsWon, creditsLost, av);
            }
        });
    }

    /*write data into text file*/
    public void writeData(int matchCount, int winCount, int lostCount, int creditsWon, int creditsLost, String avg) {
        DateFormat df = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        String fileName = date + ".txt";
        try {
            File f = new File(System.getProperty("user.home") + "/Desktop");
            File file = new File(f, fileName);//create a text file
            file.createNewFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {//write data line by line

                bw.write(date);
                bw.newLine();
                bw.write("\tNumber of Matches:" + matchCount);
                bw.newLine();
                bw.write("\tMatches Won: " + winCount);
                bw.newLine();
                bw.write("\tCredits Won: " + creditsWon);
                bw.newLine();
                bw.write("\tMatches Lost: " + lostCount);
                bw.newLine();
                bw.write("\tCredits Lost: " + creditsLost);
                bw.newLine();
                bw.write("\tAverage: " + avg);

                bw.flush();
                bw.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error Saving File!", "Message",
                        JOptionPane.INFORMATION_MESSAGE);

            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error Saving File!", "Message",
                    JOptionPane.INFORMATION_MESSAGE);

        }

    }

}
