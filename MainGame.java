package AD_Project;
import java.util.Scanner;

class GamePlayer{
    String name; // �÷��̾� �̸�
    int dice, Score = 0;
    boolean finish = false; // �÷��̾ ���������� �����ߴ��� Ȯ���ϴ� ����
    boolean isWin; // �÷��̾ �¸��ߴ��� Ȯ���ϴ� ����
    int dicePosition; // �ֻ����� ���� ��ġ�� �����ϴ� ����
}
public class MainGame {
    public static void main(String[] args) {

        GamePlayer player1 = new GamePlayer();
        GamePlayer player2 = new GamePlayer();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1�� �̸��� �Է����ּ���: ");
        player1.name = scanner.nextLine();

        System.out.println("Player 2�� �̸��� �Է����ּ���: ");
        player2.name = scanner.nextLine();

        System.out.println("������ �����մϴ�.");


        // ���� ����
        while(!player1.finish || !player2.finish){ // �� �� �� ���� ���������� ������ ������ �ݺ�
            System.out.println("�ֻ����� �����ϴ�.");
            dice();

            if(player1.isWin){ // �÷��̾� 1�� �¸����� ���
                System.out.println(player1.name + "���� �¸��ϼ̽��ϴ�.");
                player1.Score += winnerScoreUp(player1.isWin);
                player2.Score += winnerScoreUp(player2.isWin);
            }
            else if(player2.isWin){ // �÷��̾� 2�� �¸����� ���
                System.out.println(player2.name + "���� �¸��ϼ̽��ϴ�.");
                player2.Score += winnerScoreUp(player2.isWin);
                player1.Score += winnerScoreUp(player1.isWin);
            }
        }


    }
    public static void dice() {
        int dice = (int) (Math.random() * 3) + 1;
        System.out.println("�ֻ����� �����ּ���.");
        System.out.println("�ֻ����� ���ڴ� " + dice + "�Դϴ�.");
        System.out.println(dice+"ĭ �̵��մϴ�.");
    }
    public static int winnerScoreUp(boolean win) {
        if (win) { // �̴ϰ��� �¸�
            return 10;
        } else{ // �̴ϰ��� �й�
            return 5;
        }
    }
    public void scoreChange(String p1name, String p2name, int p1score, int p2score) {
        System.out.println(p1name+"��(��)"+p2name+"�� ������ ����Ǿ����ϴ�.");
        System.out.println(p1name+"�� ����: "+p2score);
        System.out.println(p2name+"�� ����: "+p1score);

    }
    public void positionChange(String p1name, String p2name) {
        System.out.println(p1name+"��(��)"+p2name+"�� ��ġ�� ����Ǿ����ϴ�.");
    }

    public void bonusScore(){
        System.out.println("�̴ϰ��� ���� 2�� Ư���� ȹ���ϼ̽��ϴ�.");
        System.out.println("���� �����ϴ� 1���� �̴ϰ��� ������ 2��� �����մϴ�.");

    }
    public void miniGame(){
        System.out.println("�̴ϰ����� �����մϴ�.");


    }
    public void backward(){
        System.out.println("1ĭ �ڷ� �̵��մϴ�.");
    }
}
