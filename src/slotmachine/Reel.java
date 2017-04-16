
package slotmachine;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Reel extends Thread {

    public JLabel lblImage;
    public JPanel pnlImage;
    public int rand;
    public boolean spin;
    Symbol[] images = new Symbol[6];

    public Reel(JLabel lbl, JPanel pnl) {
        this.lblImage = lbl;
        this.pnlImage = pnl;

    }

    public Reel() {

    }

    public JLabel getLblImage() {
        return lblImage;
    }

    public void setLblImage(JLabel lblImage) {
        this.lblImage = lblImage;
    }

    public JPanel getPnlImage() {
        return pnlImage;
    }

    public void setPnlImage(JPanel pnlImage) {
        this.pnlImage = pnlImage;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = getRandomNumber();
    }

    public void setImageArray() {

        images[0] = new Symbol(new ImageIcon("src/Images/cherry.png", "Cherry"), 2);
        images[1] = new Symbol(new ImageIcon("src/Images/lemon.png", "Lemon"), 3);
        images[2] = new Symbol(new ImageIcon("src/Images/plum.png", "Plum"), 4);
        images[3] = new Symbol(new ImageIcon("src/Images/watermelon.png", "Watermelon"), 5);
        images[4] = new Symbol(new ImageIcon("src/Images/bell.png", "Bell"), 6);
        images[5] = new Symbol(new ImageIcon("src/Images/redseven.png", "Red Seven"), 7);

    }

    public int calculate(int r1, int y, int winCredit) {
        winCredit = images[r1].getValue() * y;
        return winCredit;
    }

    public int getRandomNumber() {
        int r = (int) (Math.random() * images.length);
        return r;
    }

    public void spin(JLabel lbl) {
        rand = getRandomNumber();
        lbl.setIcon(new ImageIcon(images[rand].getImage().getImage()
                .getScaledInstance(pnlImage.getWidth() - 10, pnlImage
                        .getHeight() - 10, Image.SCALE_DEFAULT)));
    }

    Timer timer;

    @Override
    public void run() {
        setImageArray();
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spin(lblImage);
            }
        });
        timer.start();
    }

    public void stopSpin() {
        synchronized (this) {
            spin = false;
            try {
                join();
                timer.stop();

            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Something went wrong!", "Message",
                        JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }
}
