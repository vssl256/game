import java.util.Scanner;

public class Player {
    static int hp = 100;
    public static int hpGet() {
        return hp;
    }
    public static int hpSet(int dif) throws InterruptedException {
        hp+=dif;
        if (hp <= 0) deathScreen();
        return hp;
    }
    public static void deathScreen() throws InterruptedException {
        ClearConsole.init();
        System.out.println("You died. Retry? (y/n):");
        Scanner sc = new Scanner(System.in);
        if (sc.next().charAt(0)=='y') Map.Start();
        else System.exit(0);
    }
}
