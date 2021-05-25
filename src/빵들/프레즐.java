package 빵들;

import javax.swing.*;
import java.awt.*;

public class 프레즐 extends 빵{     // 왜 상속 받아야되지? 그냥 보기 좋으라고 구분지어둠. 의미는 모르겠음
                                   // 내가 필드 멤버변수만 상속받았지만 메소드가 늘어나면 빵에서 관리하면되서 편함.

    public Image 프레즐 = new ImageIcon("src/이미지/프레즐2.png").getImage();

    public int x = 700;
    public int y = 300;
    public int 갯수 = 6;
    public int 가격 = 2400;

    int width = 프레즐.getWidth(null);
    int height = 프레즐.getHeight(null);
}

