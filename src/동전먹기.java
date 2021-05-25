import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class 동전먹기 extends Thread {

    private Image bufferImage;                      // 다음과 같이 버퍼 이미지 객체와 화면의 이미지를 얻어올 그래픽 객체를 생성.
    private Graphics screenGraphic;

    private Image backgroundImage = new ImageIcon("C:\\Users\\한\\Desktop\\잡\\바탕화면 그림\\벼농사2.jpg").getImage();   // 배경,
    // 플레이어, 코인 이미지를 불러오기(절대경로)
    private Image player = new ImageIcon("C:\\Users\\한\\Desktop\\잡\\바탕화면 그림\\농부2.jpg").getImage();
    private Image coin = new ImageIcon("C:\\Users\\한\\Desktop\\잡\\바탕화면 그림\\밀2.png").getImage();

    private int playerX, playerY;
    private int playerWidth = player.getWidth(null);
    private int playerHeight = player.getHeight(null);
    private int coinX, coinY;
    private int coinWidth = coin.getWidth(null);
    private int coinHeight = coin.getHeight(null);

    public int score = 0;
    public boolean is밀가루;


    private boolean up, down, left, right;                  // 변수를 불린으로 한 이유 : 불린이 아닐 경우 키 두개의 입력을 못 받아들임.
                                                            // (오른쪽 대각선, 왼쪽 대각선 등)




    @Override
    public void run() {                           // 생성자

        Init();
        while (true) {
            try {
                Thread.sleep(20);               // 대기시간 없이 계속 반복하면 무리가 갈 수도 있으므로 살짝의 대기시간을 줌.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            keyProcess();
            crashCheck();                           // 얘도 반복문 안에 넣어줌
        }

    }

    public void Init() {

        score = 0;                            // 스코어 리셋

        playerX = (500 - playerWidth)/2;      // 플레이어 정중앙에 위치
        playerY = (500 - playerHeight)/2;     // 플레이어 정중앙에 위치

        coinX = (int)(Math.random()*(501-playerWidth));              // int형으로 변환해서 소숫점 버리기.
        coinY = (int)(Math.random()*(501-playerHeight-30))+30;       // 코인위치 랜덤으로 지정하기  // 왜 저걸 곱하는지 모르겠음.
                                                                     // 저 30은 이미지 사이즈인가?

    }


    public void keyProcess() {                                              // 이동 메소드

        int 이속 = 10;

        if (up && playerY - 이속 > 30) playerY-=이속;                           //??
        if (down && playerY + playerHeight + 이속 < 500) playerY+=이속;         //??
        if (left && playerX - 이속 > 0) playerX-=이속;                          //??    // 3-->15로 하니까 속도가 빨라짐
        if (right && playerX +playerWidth + 이속 < 500) playerX+=이속;          //??    // 플레이어 가로가 3만큼 이동한다는 뜻 같음

    }

    public void crashCheck() {              // 플레이어와 코인이 닿았을 때 점수 획득을 구현        // 이해 안감

       if (playerX+playerWidth > coinX && coinX+coinWidth> playerX && playerY+playerHeight > coinY && coinY+coinHeight > playerY) {

           score += 100;                                                 // 점수 100점
           coinX = (int)(Math.random()*(501-playerWidth));               // 코인 다른 위치로 변경.
           coinY = (int)(Math.random()*(501-playerHeight-30))+30;
       }

    }


    public void 미니게임Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        g.drawImage(backgroundImage, 0, 0, null);        // 사장이미지를 변수x,y에 그려넣기
        g.drawImage(player, 200, 200, null);        // 사장이미지를 변수x,y에 그려넣기
        g.drawImage(coin, 500, 500, null);        // 사장이미지를 변수x,y에 그려넣기
    }



}