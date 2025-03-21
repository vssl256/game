import java.io.IOException;
import java.util.Scanner;
public class Map {
		static Scanner sc = new Scanner(System.in);

		static int x = 33;
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

		static int xoffset = x;
		static int yoffset = movy+1;
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
				map[cy][cx] = '.';
				if ((cy<1||cx<1)||(cy>y-2||cx>x-2)) map[cy][cx] = '■';
				
				if (cy==5&&cx==5) map[cy][cx] = '▓';
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
		if (map[movy][movx]!='▓') System.out.print("\033[" + (movy + 1) + ";" + ((movx * 2) + 1) + "H");
		else {
			System.out.print("\033[" + (prevmovy + 1) + ";" + ((prevmovx * 2) + 1) + "H");
			movy = prevmovy;
			movx = prevmovx;
		}
		if (prevmovy == movy) {
			System.out.print((movx > prevmovx) ? "►" : "◄");
		} else {
			System.out.print((movy > prevmovy) ? "▼" : "▲");
		}
		//System.out.print("▲");
		System.out.print("\033[" + (y + 2) + ";" + 0 + "H");
		System.out.print("\033[2K"+"\n"+"\033[2K");
		System.out.print("X: "+movx+"\nY: "+movy);
		//Thread.sleep(200);
	}
    public static void Start() throws InterruptedException {
		createMap();
		ClearConsole.init();
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
			System.out.print("\033[" + (prevmovy + 1) + ";" + ((prevmovx * 2) + 1) + "H");
			System.out.print(map[prevmovy][prevmovx]);
			switch (mov) {
				case 'w' -> movy--;
				case 's' -> movy++;
				case 'a' -> movx--;
				case 'd' -> movx++;
				case 'q' -> System.exit(0);
			}
			movx = Math.max(1, Math.min(x - 2, movx));
            movy = Math.max(1, Math.min(y - 2, movy));


			//ClearConsole.init();
	}
    	sc.close();
	}
}
