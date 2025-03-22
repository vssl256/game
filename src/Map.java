import java.io.IOException;

public class Map {

		static int x = 34;
		static int y = 11;

		static String lastDirection = "▲";

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

		static int prevmovx = x/2;
		static int prevmovy = y/2;

		static char[][] map = new char[y][x];
		static char[][] mapOverlay = new char[y][x];

		static boolean rep = true;

	public static int getX() {
		return x;
	}

	public static void Build(char block) {
		switch (lastDirection) {
			case "▲" -> mapOverlay[movy-1][movx] = block;
			case "▼" -> mapOverlay[movy+1][movx] = block;
			case "◄" -> mapOverlay[movy][movx-1] = block;
			case "►" -> mapOverlay[movy][movx+1] = block;
		}
		ClearConsole.init();
		getMap();
	}

	public static void resetMap() {
		createMap();
		ClearConsole.init();
		getMap();
	}

	public static void createMap() {
		for (int cy = 0; cy < y; cy++) {
			for (int cx = 0; cx < x; cx++) {
				map[cy][cx] = '.';
				if ((cy<1||cx<1)||(cy>y-2||cx>x-2)) map[cy][cx] = '■';
				
				if (cy==5&&cx==5) map[cy][cx] = 'x';
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
				System.out.print(mapOverlay[cy][cx]+" ");
			}
			System.out.println();
		} 
	}

	public static void move() throws InterruptedException {

        switch (mapOverlay[movy][movx]) {
			
        // Препятствия нет
            case '.' -> {
				OUBCheck();
				if (oub) getMap();
                System.out.print("\033[" + (movy + 1) + ";" + ((movx * 2) + 1) + "H");
                System.out.print(lastDirection);
            }

		// Шипы
            case 'x' -> {
                Player.hpSet(-5);
                System.out.print("\033[" + (movy + 1) + ";" + ((movx * 2) + 1) + "H");
                System.out.print(lastDirection);
            }

		// Препятствие
            default -> {
                System.out.print("\033[" + (prevmovy + 1) + ";" + ((prevmovx * 2) + 1) + "H");
                movy = prevmovy;
                movx = prevmovx;
                System.out.print(lastDirection);
            }
        }

		// Отображение координат игрока
		System.out.print("\033[" + (y+1) + ";" + 0 + "H");
		System.out.print("\033[2K"+"\n"+"\033[2K");
		System.out.print("\033[" + (y+1) + ";" + 0 + "H");
		System.out.println("X: "+movx+"\nY: "+movy);
		System.out.println("HP: "+Player.hpGet()+" ");
		System.out.println("WASD to move, C - build, X - destroy, R - restart, Q - quit.");
	}
	static boolean oub = false;
	public static void OUBCheck() {
		if ((movy==0||movx==0)||(movy==y-1||movx==x-1)) {
			ClearConsole.init();
			oub = true;
			if (movy == 0) 		{movy = y - 1; return;}
			if (movx == 0) 		{movx = x - 1; return;}
			if (movy == y - 1) 	{movy = 0; return;}
			if (movx == x - 1)	movx = 0;
		} else oub = false;
	}
	
    public static void Start() throws InterruptedException {
		createMap();
		ClearConsole.init();
		getMap();
		while (rep) {
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
			System.out.print(mapOverlay[prevmovy][prevmovx]);
			switch (mov) {
				case 'w' -> {movy--; lastDirection = "▲";}
				case 's' -> {movy++; lastDirection = "▼";}
				case 'a' -> {movx--; lastDirection = "◄";}
				case 'd' -> {movx++; lastDirection = "►";}

				case 'q' -> System.exit(0);
				case 'r' -> resetMap();

				case 'c' -> Build('▓');
				case 'x' -> Build('.');

				case 'f' -> Player.hpSet(-25);
			}
			movx = Math.max(0, Math.min(x - 1, movx));
            movy = Math.max(0, Math.min(y - 1, movy));
		}
	}
}
