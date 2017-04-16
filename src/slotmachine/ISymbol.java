package slotmachine;

import javax.swing.ImageIcon;

public interface ISymbol {

    void setImage(ImageIcon img);

    ImageIcon getImage();

    void setValue(int v);

    int getValue();
}
