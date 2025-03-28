import java.io.FileNotFoundException;
import java.util.Scanner;

public class Player {
    static int hp = 100;
    public static int hpGet() throws FileNotFoundException, InterruptedException {
        if (hp <= 0) deathScreen();
        return hp;
    }
    public static int hpSet(int dif) throws InterruptedException, FileNotFoundException {
        hp+=dif;
        if (hp <= 0) deathScreen();
        return hp;
    }
    public static void deathScreen() throws InterruptedException, FileNotFoundException {
        ClearConsole.init();
        System.out.println("You died. Retry? (y/n):");
        Scanner sc = new Scanner(System.in);
        if (sc.next().charAt(0)=='y') {
            hpSet(100);
            JSON.loadMap("/maps/map1.json");
            Map.Start();
        }
        else System.exit(666);
    }
}
