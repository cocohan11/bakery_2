import 빵들.바게트;
import 빵들.빵;
import 빵들.프레즐;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class 게임 extends Thread{         //쓰레드라서 상속해줌

    private int delay = 20;             // 게임의 딜레이, 딜레이마다 증가할 cnt를 선언.
    private long pretime;
    private int cnt;                    // i처럼 쓰이는 관용적 변수인듯? count약어인가
                                        // 이 cnt가 이벤트 발생 주기를 컨트롤하는 변수가 될 것임.
    public boolean is작업실;            // 베이커리게임 클래스에 변수 만들려고했는데 메소드를 만들어도 에러나서 여기에 만듦
    public boolean is밀가루;            // 밀가루 미니게임 시작
    public boolean is밀가루끝;            // 밀가루 미니게임 끝
    public boolean is레시피;            // 레시피 펼치기

    public final int SCREEN_WIDTH = 1280;           // 다른클래스에서 사용할 변수   // 창 크기
    public final int SCREEN_HEIGHT = 720;           // 변경 못 하게 final로 함.

    public int score;

    // 미니게임 플레이어

    public Image player = new ImageIcon("src/이미지/농부2.png").getImage();
    public int playerX, playerY;
    public int playerWidth = player.getWidth(null);
    public int playerHeight = player.getHeight(null);

    // 미니게임 코인(벼)
    public Image coin = new ImageIcon("src/이미지/밀2.png").getImage();
    public int coinX, coinY;
    public int coinWidth = coin.getWidth(null);
    public int coinHeight = coin.getHeight(null);


    메인 메 = new 메인();
    손님 손 = new 손님();             // arraylist안의 내용을 접근하기위해 다음 변수 선언하기.
    포스기 포 = new 포스기();
    빵 빵 = new 빵();
    바게트 바게 = new 바게트();
    프레즐 프레 = new 프레즐();
    선반 선반 = new 선반();
    문 문 = new 문();
    작업문_오 작업문_오 = new 작업문_오();
    작업문_왼 작업문_왼 = new 작업문_왼();
    카펫 카펫 = new 카펫();
    밀가루 밀 = new 밀가루();
    동전먹기 동 = new 동전먹기();
    화덕 화 = new 화덕();
    레시피 레 = new 레시피();
    빈책 책 = new 빈책();
    /*동전먹기 동 = new 동전먹기();*/
    /*작업실 작업실 = new 작업실();*/
    /*public 베이커리게임 베겜 = new 베이커리게임();*/    //<< 에러뜸

    String P경로 = "src/이미지/사장.png";
    private Image 사장 = new ImageIcon(P경로).getImage();      // 사장이미지 경로 가져옴
    public int 사장X, 사장Y;                                       // 사장 변수 정하기
    public int 사장Width = 사장.getWidth(null);            // 사장이미지가져왔잖아. 그 너비를 불러오고 변수에 넣음.
    public int 사장Height = 사장.getHeight(null);
    public int 사장Speed = 10;                                     // 키 입력을 한 번 받을 때 이동하는 거리
    private int 사장Hp = 30;

    private boolean up,down,left,right;                             // 방향키 변수 선언

    private ArrayList<손님> 손님들 = new ArrayList<손님>();       // 손님을 담을 arraylist를 만들어주기

/*    public int 손x = 170;
    public int 손y = 0-손.height;*/


    @Override
    public void run() {                                  // 스레드 시작시 실행될 내용

        cnt = 0;                                        // cnt와 사장위치 초기화
        사장X = 1000;
        사장Y = 500;

        Init();                                 // 플레이어, 코인위치
        while (true) {            // cnt를 앞서 설정한 delay 밀리초가 지날 때마다 증가시키기(왜?)
                                  // 단순하게 sleep(delay);를 해줄 수도 있지만
                                  // 좀 더 정확한 주기를 위해
                                  // 현재시간 - cnt가 증가하기 전 시간 < delay 일 경우
                                  // 그 차이만큼 Thread에 sleep을 주겠음
                                  // ???????????

            pretime = System.currentTimeMillis();                   // 현재시간(?)
            if (System.currentTimeMillis() - pretime < delay) {
                try {
                    Thread.sleep(delay - System.currentTimeMillis() + pretime);
                    keyProcess();                                   // 메소드 삽입
                    손님AppearProcess();
                    손님MoveProcess();
                    작업실();                                        // 작업실이 반복적으로 나와야 됨
                    crashCheck();
                    /*작업실();*/                                    // 반복해야되서 여기 집어넣음

                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } // ~while
    } // ~ run()


    public void Init() {

        if (is밀가루) {                                // 미니게임 시작// 사장 그림 바꾸려고 if넣음
//            score = 0;                            // 스코어 리셋이 안되네? >> 미니게임끝()에 초기화

            playerX = (SCREEN_WIDTH - playerWidth) / 2;      // 플레이어 정중앙에 위치
            playerY = (SCREEN_HEIGHT - playerHeight) / 2;     // 플레이어 정중앙에 위치

            coinX = (int) (Math.random() * (SCREEN_WIDTH + 1 - playerWidth));              // int형으로 변환해서 소숫점 버리기.
            coinY = (int) (Math.random() * (SCREEN_HEIGHT - playerHeight - 30)) + 30;       // 코인위치 랜덤으로 지정하기  // 왜 저걸 곱하는지 모르겠음.
            // 저 30은 이미지 사이즈인가?

        }
    }

    private void keyProcess() {                 // 화면에서 사장이 안나가는 선에서 사장값 조정.

        if (is밀가루) {

            System.out.println("keyProcess");
            P경로 = "src/이미지/농부2.jpg";      // 여기 넣어도 소용없음 (?지금도?)

            int 이속 = 20;

            if (up && 사장Y - 이속 > 30) 사장Y-=이속;                           //??
            if (down && 사장Y + 사장Height + 이속 < SCREEN_HEIGHT) 사장Y+=이속;         //??
            if (left && 사장X - 이속 > 0) 사장X-=이속;                          //??    // 3-->15로 하니까 속도가 빨라짐
            if (right && 사장X +사장Width + 이속 < SCREEN_WIDTH) 사장X+=이속;          //??    // 플레이어 가로가 3만큼 이동한다는 뜻 같음

        } else {

            if (up && 사장Y - 사장Speed > 0) 사장Y -= 사장Speed;
            if (down && 사장Y + 사장Height + 사장Speed < SCREEN_HEIGHT) 사장Y += 사장Speed;
            if (left && 사장X - 사장Speed > 0) 사장X -= 사장Speed;
            if (right && 사장X + 사장Width - 사장Speed * 2 < SCREEN_WIDTH) 사장X += 사장Speed;
            // 사장speed*2 해준 이유: 사장 이미지 폭을 최대한 오른쪽으로 가게하려고고
        }
   }


    public void crashCheck() {              // 플레이어와 코인이 닿았을 때 점수 획득을 구현        // 이해 안감

        if (사장X+사장Width > coinX && coinX+coinWidth> 사장X && 사장Y+사장Height > coinY && coinY+coinHeight > 사장Y) {

            score += 100;                                                 // 점수 100점
            coinX = (int)(Math.random()*(SCREEN_WIDTH-playerWidth));               // 코인 다른 위치로 변경.
            coinY = (int)(Math.random()*(SCREEN_HEIGHT-playerHeight-30))+30;

            System.out.println("crashCheck~~~~~~~~~");
        }

    }


    private void 손님AppearProcess() {        // 손님을 주기적으로 출현시키는 메소드

        if (cnt % 80 == 0) {                        // 손님 간격!!!!!!

            if (Math.random() >= 0.8) {                     // 손님 올 확률 80%

                손 = new 손님(170, 0 - 손.height);        // 100,20위치에서 손님 객체 생성
                손님들.add(손);                      // 생성된 손님을 arraylist에 추가
            }
        }
    }


    private void 손님MoveProcess() {

        for (int i=0; i<손님들.size(); i++) {      // arraylist안의 요소에 접근.
            손 = 손님들.get(i);                    // get()메소드는 리턴값이있어서 변수에 대입
            손.move();                            // move메소드 호출
        }

    }

    public void 작업실() {

        // 사장이 오른쪽 벽에 닿았을 때 작업실은 true가 됨.
        if (사장X + 사장Width + 사장Speed >= SCREEN_WIDTH && 사장Y + 사장Height >= 작업문_오.y+50) {
            // +30은 미세조정

            is작업실 = true;           // true되면 베이커리게임에서 screenDraw가 달라짐
            사장X = 20;
        }
        밀가루();
        화덕();
        레시피();
    }

    public void 밀가루() {
        if (밀.x <= 사장X && 밀.x끝 >= 사장X && 밀.y끝 - 10 >= 사장Y) {        //접촉
            is밀가루 = true;                    // 10초뒤에 미니게임 끝내기
//            score = 0;                       // 여기도 안 되네; >> 반복되는 곳이잖아
            System.out.println("밀가루");
        }
    } //~레가루()


    public void 화덕() {
        if (화.x <= 사장X && 화.x끝 >= 사장X && 화.y끝 - 10 >= 사장Y) {

            System.out.println("score :" + score);
            System.out.println("화덕ㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
        }
    }

    public void 레시피() {
        if (레.x <= 사장X && 레.x끝 >= 사장X && 레.y끝 -10 >= 사장Y) {
            System.out.println("score :" + score);
            System.out.println("레시피ㅣㅣㅣㅣㅣㅣㅣㅣㅣ");
            is레시피 = true;
        }
    }

    ///////////////////////////////////  draw  ///////////////////////////////////////////////

    public void 게임Draw(Graphics g) {           // 게임에 그려질 요소들 d
                                                // 앞으로 만들 게임 안의 요소들을 그려주는 메소드는 전부 여기 넣음.
        매장문Draw(g);
        선반Draw(g);
        사장Draw(g);
        손님Draw(g);
        포스기Draw(g);
        빵Draw(g);

    }


    public void 작업실Draw(Graphics g) {           // 작업실 들어갈 때 보여줄 이미지

        사장Draw(g);
        작업문Draw(g);
        밀가루Draw(g);
        화덕Draw(g);
        레시피Draw(g);
        빈책Draw(g);

    }

    public void 미니게임Draw(Graphics g) {           // 작업실 들어갈 때 보여줄 이미지

        사장Draw(g);
//        농부Draw(g);
        벼Draw(g);
        g.setColor(Color.orange);                                   // Score 글자 색
        g.setFont(new Font("Arial", Font.BOLD, 40));      // Score 글꼴, 두께, 사이즈
        g.drawString("SCORE : "+score, 30, 80);

    }


    public void 벼Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        g.drawImage(coin, coinX, coinY, null);        // 사장이미지를 변수x,y에 그려넣기
    }

    public void 빈책Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        if (is레시피) {
            g.drawImage(책.빈책, 책.x, 책.y, null);        // 사장이미지를 변수x,y에 그려넣기
            g.setColor(Color.darkGray);
            g.setFont(new Font("Arial", Font.BOLD, 35));     // 글꼴, 두께, 사이즈
            g.drawString("<ingredients>", 책.x+50, 책.y+100);             // 출력 문구
            g.drawString("- baguette : "+레.바게트레시피, 책.x+50, 책.y+150);             // 출력 문구


        }
    }

    public void 화덕Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        g.drawImage(화.화덕, 화.x, 화.y, null);        // 사장이미지를 변수x,y에 그려넣기
    }

    public void 레시피Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        g.drawImage(레.레시피, 레.x, 레.y, null);        // 사장이미지를 변수x,y에 그려넣기
    }

    public void 작업문Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        g.drawImage(작업문_왼.작업문, 작업문_왼.x, 작업문_왼.y, null);        // 사장이미지를 변수x,y에 그려넣기
    }


    public void 사장Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        if (is밀가루) {

            g.drawImage(player, 사장X, 사장Y, null);        // 사장이미지를 변수x,y에 그려넣기

        } else {

            g.drawImage(사장, 사장X, 사장Y, null);        // 사장이미지를 변수x,y에 그려넣기

        }
    }


    public void 밀가루Draw(Graphics g) {                // 사장에 관한 요소를 그릴 사장Draw 메소드를 만들고

        g.drawImage(밀.밀가루, 밀.x, 밀.y, null);        // 사장이미지를 변수x,y에 그려넣기
    }


    public void 손님Draw(Graphics g) {            // 손님을 그려줄 메소드 만들어주기

        Image 이미지자리;

        for (int i=0; i<손님들.size(); i++) {
            손 = 손님들.get(i);

            if (손.빵든손님) {                       // 손님 손에 빵들.빵 쥐어주기
                이미지자리 = 손.바게트든손님;

            } else if (손.왼쪽보는빵든손님){
                이미지자리 = 손.왼쪽보는바게트든손님;       // 의도대로 안 됐음;

            } else {
                이미지자리 = 손.걷는사람;
            }
            g.drawImage(이미지자리, 손.x, 손.y,null);  // 선언했던 클래스의 필드 x,y를 이용해 손님을 그려줌
        }
    }

    public void 포스기Draw(Graphics g) {

        g.drawImage(포.책상, 포.책상x, 포.책상y, null);
        g.drawImage(포.계산대, 포.계산대x, 포.계산대y, null);
    }

    public void 빵Draw(Graphics g) {

        g.drawImage(바게.바게트, 바게.x, 바게.y, null);
        g.drawImage(프레.프레즐, 프레.x, 프레.y, null);
    }

    public void 선반Draw(Graphics g) {

        g.drawImage(선반.선반, 선반.선반x, 선반.선반y, null);
    }

    public void 매장문Draw(Graphics g) {

        g.drawImage(문.정문, 문.x, 문.y, null);
        g.drawImage(작업문_오.작업문, 작업문_오.x, 작업문_오.y, null);
        g.drawImage(카펫.카펫, 카펫.x, 카펫.y, null);
    }


    // private 변수의 경우, 객체를 통한 직접적인 접근을 못하므로 setter를 만들어줌.
    // 왜 setter를 만들지(??)

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
