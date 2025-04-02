import java.io.IOException;
import java.util.Scanner;

public class Player {
    static int hp = 100;
    public static int hpGet() throws InterruptedException, IOException {
        if (hp <= 0) deathScreen();
        return hp;
    }
    public static int hpSet(int dif) throws InterruptedException, IOException {
        hp+=dif;
        if (hp <= 0) deathScreen();
        return hp;
    }
    public static void deathScreen() throws InterruptedException, IOException {
        Console.clear();
        System.out.println("You died. Retry? (y/n):");
        Scanner sc = new Scanner(System.in);
        if (sc.next().charAt(0)=='y') {
            hpSet(100);
            JSON.loadMap("/maps/map1.json");
            Map.Start();
        }
        else System.exit(666);
        sc.close();
    }
}
