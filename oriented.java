package AD_Project;
import java.util.*;
class Entity{
    int x, y;
    Entity(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class GoodEntity extends Entity {
    GoodEntity(int x, int y) {
        super(x, y);
    }
}
class BadEntity extends Entity {
    BadEntity(int x, int y) {
        super(x, y);
    }
}
class GameTest {
    Entity[][] entities;
    GameTest() {
        Scanner sc = new Scanner(System.in);
        this.entities = new Entity[2][8];
        for(int i = 0;i < 2;i++) {
            int good = 0;
            int bad = 0;
            for(int j = 0; j < 8;) {
                String ans = sc.next();
                if(ans.equals("good")) {
                    this.entities[i][j] = new GoodEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
                    good++;
                    j++;
                    if(good==5) {
                        System.out.println("���� ���� ������ 4���Դϴ�. �ٽ� �Է����ּ���");
                        j --;
                        good--;
                        continue;
                    }
                }else if(ans.equals("bad")) {
                    this.entities[i][j] = new BadEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
                    bad++;
                    j++;
                    if(bad==5) {
                        System.out.println("���� ���� ������ 4���Դϴ�. �ٽ� �Է����ּ���");
                        j--;
                        bad--;
                        continue;
                    }
                }else {
                    j++;
                    System.out.println("bad�� good���θ� �Է��ϼž��մϴ�. �ٽ� �Է����ּ���.");
                    j--;
//				this.entities[i][j] = new Entity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4, goods[i][j]);
//				if() {
//					this.entities[i][j] = new GoodEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
//				} else {
//					this.entities[i][j] = new BadEntity(j % 4 + 1, i == 0 ? j / 4 : 4 + j / 4);
                    // this.entities[i][j] instanceof GoodEntity -> good
                    // this.entities[i][j] instanceof BadEntity -> bad
                }
//				System.out.print("(" + this.entities[i][j].x + ", " + this.entities[i][j].y + ") ");
            }
            for(int k=0; k<16; k++) {
                System.out.println();
            }
            System.out.println("�� ��° �÷��̾ ��Ʈ�� ���Ͻ� �����Դϴ�.");
        }
    }
}

class Board{
    GameTest game;
    String blue = "\u001B[0m";
    String red = "\u001B[31m";
	String exit = "\u001B[0m";
    String Ghost = ":  o  ";
    String Ghost_blue = ":  "+blue+"o  ";
    String noGhost = ":     ";
    String border = "-------------------------------------";
    Board(GameTest game) {
        this.game = game;
    }
    void getBoard() {
        System.out.println(border);
        int [][] board = new int [7][7];
        for(int i = 0;i < 2;i++) {
            for(int j = 0; j < 8;j++) {
                board[game.entities[i][j].y][game.entities[i][j].x] = 1;
            }
        }
        for(int i =0; i < 6; i++) {
            if(i== 0 || i==5)
                System.out.print(":  <- ");
            for(int j =0; j < 6; j++) {
                if(i==0 && j==0 || i==0 && j==5 || i==5&& j==5 || i==5 && j==0)
                    continue;
                if(board[i][j] == 1){
                    System.out.print(Ghost);
                } else {
                    System.out.print(noGhost);
                }
            }
            if(i== 0 || i==5)
                System.out.print(":  -> ");
            System.out.print(": \n");
            System.out.println(border);
        }
    }
}
class Ghost_move {
    String move;
    GameTest game;
    static int cnt;
    int blue_cnt;
    int red_cnt;
    Ghost_move(GameTest game) {
        this.game = game;
    }

    void Check(int c) {
        for (int i = 0; i < 8; i++) {
            if (game.entities[cnt % 2][c - 1].x == game.entities[(cnt + 1) % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[(cnt + 1) % 2][i].y) {
            	if (game.entities[(cnt + 1) % 2][i] instanceof GoodEntity) {
                    System.out.println("���� �����̾����ϴ�!");
                } else {
                    if (cnt % 2 == 0) {
                        blue_cnt++;
                        System.out.println("���� �����̾����ϴ�!" + blue_cnt + "���� ��ҽ��ϴ�.");
                    } else {
                        red_cnt++;
                        System.out.println("���� �����̾����ϴ�!" + red_cnt + "���� ��ҽ��ϴ�.");
                    }
                }
                game.entities[(cnt + 1) % 2][i] = new GoodEntity(6, 6);
                break;
            } else if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
            		System.out.println("�ٸ� ���� �ֽ��ϴ�. �ٽ� �������ּ���.");
            	}
        }
    }

    void Move() {
        Scanner sc = new Scanner(System.in);
        cnt++;
        boolean ans = false;
        System.out.print("� ������ �������� ���ڸ� �Է��ϼ��� : ");
        int c = sc.nextInt();
        if (game.entities[cnt % 2][c - 1].x == 6) {
            System.out.println("�̹� ���� ���Դϴ�. �ٽ� �������ּ���.");
            cnt--;
        } else {
            System.out.print("wasd�� ������ ������ ������ �Է����ּ��� : ");
            move = sc.next();
            if (cnt % 2 == 0) {
                if (move.equals("w")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c-1].y+1 == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].y + 1 == 6) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    }else if(ans){
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    }else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity) {
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        } else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        Check(c);
                    }
                } else if (move.equals("a")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x-1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].x -1 == -1) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    }else if(ans){
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else if (move.equals("s")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y-1 == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].y - 1 == -1) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    }else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        Check(c);
                    }
                } else if (move.equals("d")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x+1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].x + 1 == 6) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else {
                    System.out.println("wasd�θ� �Է��ϼž��մϴ�. �ٽ� �Է����ּ���.");
                    cnt--;
                }
            } else {
                if (move.equals("w")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y-1 == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].y - 1 == -1) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y - 1);
                        Check(c);
                    }
                } else if (move.equals("a")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x-1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].x - 1 == -1) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x - 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else if (move.equals("s")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y+1 == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].y + 1 == 6) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity) {
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        } else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x, game.entities[cnt % 2][c - 1].y + 1);
                        Check(c);
                    }
                } else if (move.equals("d")) {
                	for(int i= 0; i<8; i++) {
                    	if(game.entities[cnt % 2][c - 1].x+1 == game.entities[cnt % 2][i].x && game.entities[cnt % 2][c - 1].y == game.entities[cnt % 2][i].y && i != c-1) {
                    		ans = true;
                    		break;
                    		}
                	}
                    if (game.entities[cnt % 2][c - 1].x + 1 == 6) {
                        System.out.println("�� �� ���� ���Դϴ�. �ٽ� �Է����ּ���.");
                        cnt--;
                    } else if(ans) {
                    	System.out.println("�ٸ� ���� �ֽ��ϴ�.");
                    	cnt--;
                    } else {
                        if (game.entities[cnt % 2][c - 1] instanceof BadEntity)
                            game.entities[cnt % 2][c - 1] = new BadEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        else
                            game.entities[cnt % 2][c - 1] = new GoodEntity(game.entities[cnt % 2][c - 1].x + 1, game.entities[cnt % 2][c - 1].y);
                        Check(c);
                    }
                } else {
                    System.out.println("wasd�θ� �Է��ϼž��մϴ�. �ٽ� �Է����ּ���.");
                    cnt--;
                }
            }
        }
    }
}
public class oriented {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameTest game = new GameTest();
        Board b = new Board(game);
        Ghost_move g = new Ghost_move(game);
        b.getBoard();
        boolean cnt = true;
        while (cnt) {
            g.Move();
            b.getBoard();
            for (int i = 0; i < 8; i++) {
                if (g.blue_cnt == 4 || (game.entities[1][i] instanceof GoodEntity &&game.entities[1][i].x == 0 && game.entities[1][i].y == 0) || (game.entities[1][i] instanceof GoodEntity&&game.entities[1][i].x == 5 && game.entities[1][i].y == 0)) {
                    System.out.println("������ �¸�!");
                    cnt = false;
                    break;
                } else if (g.red_cnt == 4 || (game.entities[1][i] instanceof GoodEntity&&game.entities[0][i].x == 0 && game.entities[0][i].y == 5) || (game.entities[0][i] instanceof GoodEntity&&game.entities[0][i].x == 5 && game.entities[0][i].y == 5)) {
                    System.out.println("����� �¸�!");
                    cnt = false;
                    break;
                }
            }
        }
    }
}