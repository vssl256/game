import java.io.IOException;

public class ClearConsole {
    public static void init() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException| InterruptedException e) {
            e.printStackTrace();
        }
    }
}
