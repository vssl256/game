import java.io.IOException;

public class Console {

    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException| InterruptedException e) {
            e.printStackTrace();
        }
    }

	public static void cursorSet(int y, int x) {
		System.out.print("\033[" + y + ";" + x + "H");
	}
    
	public static void rowClear() {
		System.out.print("\033[2K"+"\n"+"\033[2K");
	}

}
