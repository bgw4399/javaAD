package AD_Project;
import java.util.Scanner;

public class bingo {
    public static void main(String[] args) {
        final int SIZE = 5;
        int x = 0, y = 0, num = 0;
        int totalCnt = 0;
        int cnt = 0;

        int[][] bingo = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

// �迭�� ��� ��Ҹ� 1���� SIZE*SIZE������ ���ڷ� �ʱ�ȭ
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bingo[i][j] = i * SIZE + j + 1;
            }
        }

// �迭�� ����� ���� �ڼ��´�.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                x = (int) (Math.random() * SIZE);
                y = (int) (Math.random() * SIZE);

                // bingo[i][j]�� ���Ƿ� ���õ� ��(bingo[x][y])�� �ٲ۴�.
                int tmp = bingo[i][j];
                bingo[i][j] = bingo[x][y];
                bingo[x][y] = tmp;
            }
        }
        // ������ ���
        System.out.println("    # Bingo Board #\n");
        do {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (bingo[i][j] == 0) {
                        System.out.printf("%-5s", "*");
                    } else
                        System.out.printf("%-5d", bingo[i][j]);
                }
                System.out.println();
            } // ���� ī��Ʈ
            System.out.println("--------------------------");
            System.out.printf("1~%d�� ���ڸ� �Է��ϼ���. > ", SIZE * SIZE);
            String tmp = scanner.nextLine(); // ȭ�鿡�� �Է¹��� ������ tmp�� ����
            num = Integer.parseInt(tmp); // �Է¹��� ���ڿ�(tmp)�� ���ڷ� ��ȯ

            // �Է¹��� ���ڿ� ���� ���ڰ� ����� ��Ҹ� ã�Ƽ� 0�� ����
            outer: for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (bingo[i][j] == num) {
                        bingo[i][j] = 0;
                        break outer;
                        // 2�� �ݺ��� ������
                    }
                }
            }
            if (totalCnt == 1) {
                System.out.println("    Total: 1 ����");
                totalCnt = 0;
            } else if (totalCnt == 2) {
                System.out.println("    Total: 2 ����");
                totalCnt = 0;
            } else if (totalCnt == 3) {
                System.out.println("    Total: 3 ����");
                totalCnt = 0;
            } else if (totalCnt == 4) {
                System.out.println("    Total: 4 ����");
                totalCnt = 0;
            } else if (totalCnt == 5) {
                totalCnt = 0;
                System.out.println("       ���� �ϼ�!");
                System.out.println("    *������ �����մϴ�*\n");
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (bingo[i][j] == 0)
                        cnt++;
                }
                if (cnt == 5)
                    totalCnt++;
                cnt = 0;

                for (int j = 0; j < SIZE; j++) {
                    if (bingo[j][i] == 0)
                        cnt++;
                }
                if (cnt == 5)
                    totalCnt++;
                cnt = 0;
            } // ���� �밢��üũ
            if ((bingo[0][0] == 0) && (bingo[1][1] == 0) && (bingo[2][2] == 0) && (bingo[3][3] == 0)
                    && (bingo[4][4] == 0))
                totalCnt++;
            if ((bingo[0][4] == 0) && (bingo[1][3] == 0) && (bingo[2][2] == 0) && (bingo[3][1] == 0)
                    && (bingo[4][0] == 0))
                totalCnt++;

        } while (totalCnt <= 5);
        scanner.close();
    }

}
