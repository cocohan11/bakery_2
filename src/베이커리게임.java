import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class 베이커리게임 extends JFrame {        // JFrame 상속한다. // JFrame이란? 창이다.

    private Image bufferImage;                  // 사장캐릭터, 손님캐릭터 등의 움직임을 그릴 때 깜빡임을 없애기 위해 더블버퍼링이란 기법 사용.
    private Graphics screenGraphic;             // 미리 변수 선언.  // << 이 변수에 그림을 넣는다.

    private Image bakery = new ImageIcon("src/이미지/bakery4.png").getImage();          // getImage()를 해야 이미지를 불러오나 봄
    private Image 게임방법 = new ImageIcon("src/이미지/게임방법3.png").getImage();          // getImage()를 해야 이미지를 불러오나 봄
    private Image 타일 = new ImageIcon("src/이미지/타일.jpg").getImage();          // getImage()를 해야 이미지를 불러오나 봄(?)
    private Image 벼농사 = new ImageIcon("src/이미지/벼농사2.jpg").getImage();          // getImage()를 해야 이미지를 불러오나 봄(?)

    public boolean isBakery3, is게임방법, is타일;        // 불린변수들로 화면을 컨트롤함(?) true일 때 보이게끔 한다는건가?
    public static int 매출;                                            // 일매출 변수 // 화면 출력


    /*메인 메인 = new 메인();*/                       // 생성자의 setSize때문에 객체 만듦(static으로 변수 마구 만들기 싫어서)
    게임 겜 = new 게임();
    동전먹기 동 = new 동전먹기();
    밀가루 밀 = new 밀가루();


    public 베이커리게임() {                       // 생성자 (메인에서 호출할거임)

        setTitle("베이커리 게임");
        /*setUndecorated(true);    << 이거 x버튼 없어서 불편해*/
        setSize(겜.SCREEN_WIDTH, 겜.SCREEN_HEIGHT);         // 창 크기
        setResizable(false);                    // 창 크기조절 여부
        setLocationRelativeTo(null);            // null을 넣으면 실행 시 창이 화면 가운데에 뜨게 됨.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // 이 코드를 작성해야 창을 닫을 시 프로세스도 종료하게 됨.
        setVisible(true);                       // 보이기 여부
        setLayout(null);                        // ?

        init();


    } // ~생성자

    private void init() {                       // JFrame 클래스의 메소드인듯. 변경하면 안됨.  // inti메소드로 isBakery3만 초기화(왜?)

        isBakery3 = true;
        is게임방법 = false;
        is타일 = false;
        겜.is작업실 = false;

        addKeyListener(new KeyListener());      // 키 입력받는다고 호출. 이 코드를 써야 키가 먹힘

    }

    private void 게임시작() {                    // 엔터 누르고 게임방법 화면 나오게하려면 베이커리 화면은 false로 안 보이게하고
                                                // 게임방법 창 true으로 보이게함

        isBakery3 = false;
        겜.is작업실 = false;
        is게임방법 = true;


        Timer 로딩타이머 = new Timer();              // 이게 뭐하는 역할일까?
        TimerTask 로딩테스크 = new TimerTask() {     // 게임설명 화면에서 3초후에 게임화면으로 넘어가도록 하려고 타이머와 타이머테스크 만듦
            @Override
            public void run() {                     // run 안에 실행할 내용을 쓰면 됨.
                is게임방법 = false;
                겜.is작업실 = false;
                is타일 = true;

                /*작업실(); */                         // 여기다 넣으면 될까? 아냐 반복해야돼
            }
        };

        로딩타이머.schedule(로딩테스크, 1000);

        겜.start();                                  // 스레드 시작작

    }



/*    public void 작업실() {

        // 사장의x위치가 벽에 닿았을 때 작업실은 true가 됨.

        if (겜.사장X + 겜.사장Width + 겜.사장Speed >= 메인.SCREEN_WIDTH) {
            isBakery3 = false;
            is게임방법 = false;
            is타일 = false;
            is작업실 = true;
        }
        System.out.println("작업실");

    }*/





    public void paint(Graphics g) {             // paint라는 메소드는 JFrame의 메소드인가봄. 내가 변경할 수 있는 이름이 아님
                                                // 따로 페인트 메소드를 불러오지 않았는데도 알아서 메소드를 쓴 것 처럼 되네. 디버깅해도 그냥 지나감

        bufferImage = createImage(겜.SCREEN_WIDTH, 겜.SCREEN_HEIGHT);       // 이미 생성자가 정해져있네. 가로세로  // 위의 변수에 값 넣음
        screenGraphic = bufferImage.getGraphics();    // 버퍼이미지 변수에서 사이즈를 정했다면 스크린그래픽변수에서는 저 사이즈의 그림을 가져온다.
        screenDraw(screenGraphic);          // 타일 이미지를 가져온다.
        g.drawImage(bufferImage, 0,0,null);     // paint메소드에 버퍼이미지를 만들고 이를 화면에 뿌려줌으로써 깜빡임을 최소화.

    }

    public void screenDraw(Graphics g) {        // 이 메소드에서는 필요한 요소를 그려줄 것이다.

        if (isBakery3) {
            g.drawImage(bakery, 0, 0, null);       // 베이커리 이미지  // x좌표 똑똑하게 width구해서 400을 다른 변수로 수정하고싶음
        }
        if (is게임방법) {
            g.drawImage(게임방법, 0, 0, null);          // 각 화면 변수가 true일 때마다 다른 화면을 그려주기 위해 조건을 추가함.
        }
        if (is타일) {
            g.drawImage(타일, 0, 0, null);
            겜.게임Draw(g);                                           // 게임화면일 때 게임Draw메소드 실행
            g.setColor(Color.darkGray);
            g.setFont(new Font("Arial", Font.BOLD, 40));     // 글꼴, 두께, 사이즈
            g.drawString("money : "+매출, 30, 690);             // 출력 문구
        }
        if (겜.is작업실) {                                                // 작업실이 true인 경우 이 코드가 구현

            isBakery3 = false;                                          // 메소드를 못 써서 여기에 불린넣음
            is게임방법 = false;
            is타일 = false;
            /*g.drawImage(작업실, 0,0,null);*/

            g.drawImage(타일, 0, 0, null);
            겜.작업실Draw(g);                                           // 사장만 등장
            g.setColor(Color.darkGray);
            g.setFont(new Font("Arial", Font.BOLD, 20));     // 글꼴, 두께, 사이즈
            g.drawString("flour : "+밀.현재고+"/"+밀.최대재고, 밀.x+30, 밀.y+밀.height+20);             // 출력 문구a


            if (겜.사장X - 겜.사장Speed <= 0) {                        // 왼쪽 벽에 부딪힌다면

                isBakery3 = false;
                is게임방법 = false;
                is타일 = true;
                겜.is작업실 = false;

                겜.사장X = 1100;

            }
        }
        if (동.is밀가루) {

            isBakery3 = false;
            겜.is작업실 = false;
            is게임방법 = false;

            g.drawImage(타일, 0, 0, null);
            /*동.미니게임Draw(g);*/

        }

        this.repaint();                         // 이 코드를 써야 이미지가 출력된댔음

                                                // 왼) OPEN 클릭하고 오) 재고파악 클릭하게 하고싶음. 우선 key입력받는 것 부터 하자.
    }

    class KeyListener extends KeyAdapter {          // esc 눌렀을 때 꺼지는 기능 구현  // 키 움직임을 받아줄 KeyListener 클래스 생성

        @Override                                   // 오버라이드 왜 하지(?) 오버라이드 안 써도 상속받으면 그냥 그 클래스의 메소드 쓸 수 있는거 아닌감
        public void keyPressed(KeyEvent e) {        // 키를 눌렀을 때 메소드 // 키 이벤트 클래스

            switch (e.getKeyCode()) {               // 조건문

                case KeyEvent.VK_W:
                    겜.setUp(true);
                    break;
                case KeyEvent.VK_S:
                    겜.setDown(true);
                    break;
                case KeyEvent.VK_A:
                    겜.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    겜.setRight(true);
                    break;

                case KeyEvent.VK_ENTER:
                    if (isBakery3) 게임시작();
                    break;

                case KeyEvent.VK_ESCAPE:           // esc 누른다면)
                    System.exit(0);           // 프로그램 종료
                    break;
            }

        }

        public void keyReleased(KeyEvent e) {        // 키를 뗐을 때 메소드 // 키 이벤트 클래스

            switch (e.getKeyCode()) {

                case KeyEvent.VK_W:
                    겜.setUp(false);
                    break;
                case KeyEvent.VK_S:
                    겜.setDown(false);
                    break;
                case KeyEvent.VK_A:
                    겜.setLeft(false);
                    break;
                case KeyEvent.VK_D:
                    겜.setRight(false);
                    break;
            }

        }

    }







} // ~class 베이커리게임
