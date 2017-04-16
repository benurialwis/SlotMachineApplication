package slotmachine;

import javax.swing.ImageIcon;

public class Symbol implements ISymbol {

    private ImageIcon image;
    private int value;

    Symbol(ImageIcon image, int value) {
        setImage(image);
        setValue(value);
    }

    public Symbol() {
    }

    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public int compareTwoImages(int r1, int r2, int r3) {
        if (r1 == r2 && r1 == r3) {
            return 0;
        } else if (r1 == r2 || r1 == r3 || r2 == r3) {
            return 1;
        } else {
            return 2;
        }
    }

}
