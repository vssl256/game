import java.io.IOException;
import java.util.Scanner;
public class Map {
		static Scanner sc = new Scanner(System.in);

		static int x = 11;
		static int y = 11;

		static int movx = x/2;
		public static void setMovx(int movx) {
			Map.movx = movx;
		}
		public static int getMovx() {
			return movx;
		}
		
		static int movy = y/2;
		public static void setMovy(int movy) {
			Map.movy = movy;
		}
		public static int getMovy() {
			return movy;
		}

		static int xoffset = movx*2+1;
		static int yoffset = movy-1;
		static int prevmovx = x/2;
		static int prevmovy = y/2;
		static int vector = -1;

		static char[][] map = new char[y][x];
		static char[][] mapOverlay = new char[y][x];

		static boolean rep = true;

	public static int getX() {
		return x;
	}
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
				//mapOverlay[prevmovy][prevmovx] = map[movy][movx];
				//if (cx == movx && cy == movy) mapOverlay[cy][cx] = '▲';
				System.out.print(mapOverlay[cy][cx]+" ");
			}
			System.out.println();
		} 
	}
	public static void move() throws InterruptedException {
		
		System.out.print("\033[" + yoffset + ";" + xoffset + "H");
		System.out.print("▲");
		Thread.sleep(200);
	}
    public static void Start() throws InterruptedException {
		createMap();
		System.out.println("Map created");
		getMap();
	while (rep) {
		//getMap();
		move();
			prevmovx = movx;
			prevmovy = movy;
			char mov = 0;
			try {
				mov = (char)Movement.Start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.print("\033[" + yoffset + ";" + xoffset + "H");
			System.out.print(map[movy][movx]);
			switch (mov) {
				case 'w' -> {movy--; yoffset--;}
				case 's' -> {movy++; yoffset++;}
				case 'a' -> {movx--; xoffset-=2;}
				case 'd' -> {movx++; xoffset+=2;}
				case 'q' -> System.exit(0);
			}
			//if (movx > x-2) movx = x-2;
			//if (movx < 1) movx = 1;
			//if (movy > y-2) movy = y-2;
			//if (movy < 1) movy = 1;


			//ClearConsole.init();
	}
    	sc.close();
	}
}
