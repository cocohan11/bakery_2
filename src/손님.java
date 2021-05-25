import 빵들.바게트;
import 빵들.빵;

import javax.swing.*;
import java.awt.*;

public class 손님 {               // 손님 클래스 생성 // 위치 정보, 체력 등 담음

    Image 걷는사람 = new ImageIcon("src/이미지/걷는사람4.png").getImage();
    Image 바게트든손님 = new ImageIcon("src/이미지/바게트든손님.png").getImage();
    Image 왼쪽보는바게트든손님 = new ImageIcon("src/이미지/왼쪽보는바게트든손님.png").getImage();
    public 빵 빵 = new 빵();
    public 바게트 바게 = new 바게트();
    public 포스기 계 = new 포스기();
    public 베이커리게임 베;


    public boolean 빵든손님 = false;
    public boolean 왼쪽보는빵든손님 = false;
    int x,y;
    int width = 걷는사람.getWidth(null);
    int height = 걷는사람.getHeight(null);
    int hp = 10;
    int speed = 5;


    public 손님(int x, int y) {               // 위치정보를 매개변수로 받는생성자 만듦
        this.x = x;
        this.y = y;
    }

    public 손님() {}

    public void move() {                    // 손님을 움직이게 할 move 메소드도 정의

        int 가로시작 = 170;
        int 가로끝 = 1050;
        int 세로시작 = 20;
        int 세로끝 = 440;
        /*빵든손님 = false;*/

        System.out.println("하하하");
        if (x == 가로시작 && y <= 세로시작) 아래();
        if (x == 가로시작 && y == 세로시작) x += 1;                 // 겹쳐서 조금 엇나가게함
        if (x >= 가로시작+1 && x <= 가로끝 && y <= 세로시작) 오른쪽();
        if (x >= 가로끝 && y <= 세로끝) 아래();
        if (x <= 가로끝 + speed && y > 세로끝) 왼쪽();
        if (x < 100) 위쪽();

        if (x>=바게.x && x<= 바게.x+speed && y<=300 && 바게.갯수>0) {     // 저 구역 지날 때

            바게.갯수 -= 1;               // (빵들.빵.바게트x + 빵들.빵.바게트width)/2
            /*베.매출 += 빵들.빵.바게트가격;*/
            빵든손님 = true;           // 걷는사람 이미지를 바게트든손님 이미지로 바꾸고싶음



        }
        if (y<=계.계산대y+speed && y>계.계산대y && x<200) {        // 저 구역 지날 때
            베.매출 += 바게.가격;
        }

/*        if (x == 170 && y <= 20) 아래();
        if (x == 170 && y == 20) x += 1;                 // 겹쳐서 조금 엇나가게함
        if (x >= 171 && x <= 1050 && y <= 20) 오른쪽();
        if (x >= 1050 && y <= 450) 아래();
        if (x <= 1050 + speed && y > 450) 왼쪽();
        if (x < 100) 위쪽();*/


    } //~move()

    public void 오른쪽() {

        this.x += speed;
    }

    public void 아래() {

        this.y += speed;
    }

    public void 왼쪽() {

        this.x -= speed;
    }

    public void 위쪽() {

        this.y -= speed;
    }

    public void x정지() {

        this.y += 0;
    }

    public void y정지() {

        this.y += 0;
    }



}
