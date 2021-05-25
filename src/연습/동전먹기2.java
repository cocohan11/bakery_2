/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class 동전먹기2 extends JFrame {

    private Image bufferImage;                      // 다음과 같이 버퍼 이미지 객체와 화면의 이미지를 얻어올 그래픽 객체를 생성.
    private Graphics screenGraphic;

    private Image backgroundImage = new ImageIcon("C:\\Users\\한\\Desktop\\잡\\바탕화면 그림\\사막600.jpg").getImage();   // 배경, 플레이어, 코인 이미지를 불러오기(절대경로)
    private Image player = new ImageIcon("C:\\Users\\한\\Desktop\\잡\\바탕화면 그림\\파인애플.jpg").getImage();
    private Image coin = new ImageIcon("C:\\Users\\한\\Desktop\\잡\\바탕화면 그림\\coin.png").getImage();

    private int playerX, playerY;
    private int playerWidth = player.getWidth(null);
    private int playerHeight = player.getHeight(null);
    private int coinX, coinY;
    private int coinWidth = coin.getWidth(null);
    private int coinHeight = coin.getHeight(null);

    public int score;

    private boolean up, down, left, right;                  // 변수를 불린으로 한 이유 : 불린이 아닐 경우 키 두개의 입력을 못 받아들임.
                                                            // (오른쪽 대각선, 왼쪽 대각선 등)




    public 동전먹기2() {                          // 생성자

        setTitle("동전 먹기 게임");               // 제목
        setVisible(true);                       // 보이기 여부
        setSize(500,500);           // 창 크기
        setLocationRelativeTo(null);            // null을 넣으면 실행 시 창이 화면 가운데에 뜨게 됨.
        setResizable(false);                    // 창 크기 조절 가능 여부
        setDefaultCloseOperation(HIDE_ON_CLOSE);  // 이 코드를 작성해야 창을 닫을 시 프로세스도 종료하게 됨.
        addKeyListener(new KeyAdapter() {                       //키누를 때 이벤트하는 메소드인가봄...(?) 이미 있는 메소드를 갖다쓰는 것 같은데.
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:             // w:위, s:아래, a:왼, d:오      // 누를 때마다 true값으로 바꿔줌
                        up = true;
                        break;
                    case KeyEvent.VK_S:
                        down = true;
                        break;
                    case KeyEvent.VK_A:
                        left = true;
                        break;
                    case KeyEvent.VK_D:
                        right = true;
                        break;
                }
            } //~keyPressed()

            public void keyReleased(KeyEvent e) {       // 키를 뗐을 때 실행할 메소드
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:             // w:위, s:아래, a:왼, d:오      // 뗄 때마다 false값으로 바꿔줌
                        up = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;
                }
            } //~keyReleased()

        });
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



    public void paint(Graphics g) {                                // 더블 버퍼링을 이용하여 버퍼 이미지를 통해 화면의 깜빡임 최소화.

        bufferImage = createImage(500,500);                 // 화면 크기의 버퍼 이미지를 생성해주고 getGraphics()를 통해 그래픽을 받아옴.
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);           // 다시 screenDraw를 호출하고 이 버퍼 이미지를 화면서 그려주는 작업이 반복.
        g.drawImage(bufferImage, 0,0,null);

    }

    public void screenDraw(Graphics g) {                                 // 출력

        g.drawImage(backgroundImage, 0,0,null);        // 배경 출력
        g.drawImage(player, playerX, playerY,null);         // 플레이어 출력
        g.drawImage(coin, coinX, coinY,null);               // 코인 출력
        g.setColor(Color.orange);                                   // Score 글자 색
        g.setFont(new Font("Arial", Font.BOLD, 40));      // Score 글꼴, 두께, 사이즈
        g.drawString("SCORE : "+score, 30, 80);            // Score 출력 문구
        this.repaint();                                             // 이걸 써줘야 작동함  // but, 너무 깜빡거림

    }

    void run() {

    }

*/
/*    public static void main(String[] args) {

        new 동전먹기();                              // 한베이커리.메인 메소드에 예제.동전먹기 객체를 생성함으로써 생성자를 호출.
    }*//*

}*/
