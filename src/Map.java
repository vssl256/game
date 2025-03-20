import java.util.Scanner;

public class Map {
		static Scanner sc = new Scanner(System.in);

		static int x = 66;
		static int y = 11;
		static int movx = x/2;
		static int movy = y/2;
		static int prevmovx = x/2;
		static int prevmovy = y/2;
		static int vector = -1;

		static char[][] map = new char[y][x];
		static char[][] mapOverlay = new char[y][x];
		static boolean rep = true;

	public static void createMap() {
		for (int cy = 0; cy < y; cy++) {
			for (int cx = 0; cx < x; cx++) {
				map[cy][cx] = '▫';
				if ((cy<1||cx<1)||(cy>y-2||cx>x-2)) map[cy][cx] = '■';
			}
		}
		mapOverlay = new char[y][x];
    	for (int cy = 0; cy < y; cy++) {
        	System.arraycopy(map[cy], 0, mapOverlay[cy], 0, x);
    	}
	}
	public static void getMap() {
		for (int cy = 0; cy < y; cy++) {
			for (int cx = 0; cx < x; cx++) {
				mapOverlay[prevmovy][prevmovx] = map[movy][movx];
				if (cx == movx && cy == movy) mapOverlay[cy][cx] = '▲';
				System.out.print(mapOverlay[cy][cx]+" ");
			}
			System.out.println();
		} 
	}
    public static void Start() {
		createMap();
		System.out.println("Map created");
	while (rep) {
		getMap();
			prevmovx = movx;
			prevmovy = movy;
			char mov = sc.next().charAt(0);
			switch (mov) {
				case 'w' -> movy--;
				case 's' -> movy++;
				case 'a' -> movx--;
				case 'd' -> movx++;
				case 'q' -> System.exit(0);
			}
			if (movx > x-2) movx = x-2;
			if (movx < 1) movx = 1;
			if (movy > y-2) movy = y-2;
			if (movy < 1) movy = 1;


			System.out.println("\033[H\033[2J");
	}
    	sc.close();
	}
}
