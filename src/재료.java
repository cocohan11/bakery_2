import javax.swing.*;
import java.awt.*;

public class 재료 {

    String 이름;
    int 최대재고;
    int 현재고;


} //~class

class 밀가루 extends 재료 {

    Image 밀가루 = new ImageIcon("src/이미지/밀가루2.png").getImage();

    String 이름 = "밀가루";
    int 최대재고 = 3000; //g단위
    int 현재고 = 0;   // 바꾸고싶으면 생성자로 초기화하기
    int x = 300;
    int y = 30;

    int width = 밀가루.getWidth(null);
    int height = 밀가루.getHeight(null);

    int x끝 = x+width;
    int y끝 = y+height;




}
