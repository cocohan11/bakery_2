package 빵들;

import javax.swing.*;
import java.awt.*;

public class 바게트 extends 빵{

    public Image 바게트 = new ImageIcon("src/이미지/바게트2.png").getImage();

    public int x = 500;
    public int y = 300;

    public int 갯수 = 3;
    public int 가격 = 2800;

    int width = 바게트.getWidth(null);
    int height = 바게트.getHeight(null);
}
