import 빵들.바게트;

import javax.swing.*;
import java.awt.*;

public class 레시피 {

    Image 레시피 = new ImageIcon("src/이미지/레시피2.png").getImage();
    바게트 바 = new 바게트();

    int x = 300;
    int y = 50;

    int width = 레시피.getWidth(null);
    int height = 레시피.getHeight(null);

    int x끝 = x+width;
    int y끝 = y+height;

    String 바게트레시피 = 바.밀g+"g";

}
