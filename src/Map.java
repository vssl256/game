import java.io.FileNotFoundException;
import java.io.IOException;


public class Map {
	
		static int x;
		static int y;

		static String lastDirection = "▲";

		static int movx = 16;
		static int movy = 3;

		static int prevmovx;
		static int prevmovy;

		static char[][] map;
		static char[][] mapOverlay;
	
		static boolean rep = true;
		static String mapPath = "/maps/map1.json";

		static int currentMap = 1;

	public static void load() throws FileNotFoundException {
		map = JSON.loadMap(mapPath);
		x = JSON.mapData.width;
		y = JSON.mapData.height;
		mapOverlay = new char[y][x];
		for (int cy = 0; cy < y; cy++) {
    		System.arraycopy(map[cy], 0, mapOverlay[cy], 0, x);
		}
		ClearConsole.init();
		getMap();
	}
	public static void load(String nextPath) throws FileNotFoundException {
		mapPath = nextPath;
		load();
	}

	public static void Build(char block) throws FileNotFoundException {
		switch (lastDirection) {
			case "▲" -> mapOverlay[movy-1][movx] = block;
			case "▼" -> mapOverlay[movy+1][movx] = block;
			case "◄" -> mapOverlay[movy][movx-1] = block;
			case "►" -> mapOverlay[movy][movx+1] = block;
		}
		ClearConsole.init();
		getMap();
	}

	public static void resetMap() throws FileNotFoundException {
		ClearConsole.init();
		getMap();
	}

	public static void getMap() {
		for (int cy = 0; cy < y; cy++) {
			for (int cx = 0; cx < x; cx++) {
				System.out.print(mapOverlay[cy][cx]+" ");
			}
			System.out.println();
		} 
	}

	public static void cursorSet(int y, int x) {
		System.out.print("\033[" + y + ";" + x + "H");
	}

	public static void rowClear() {
		System.out.print("\033[2K"+"\n"+"\033[2K");
	}

	public static void move() throws InterruptedException, FileNotFoundException {

		for (JSON.MapData.Door door : JSON.mapData.doors) {

			if (movx == door.x && movy == door.y) {

					movx = door.targetX;
					movy = door.targetY;
				currentMap = door.target;
				load("/maps/map"+currentMap+".json");
				cursorSet((movy + 1), ((movx * 2) + 1));
				System.out.print(lastDirection);
				statusBar();
				return;

			}

		}
        switch (mapOverlay[movy][movx]) {

        // Препятствия нет
            case '.' -> {
				if (oub) getMap();
                cursorSet((movy + 1),((movx * 2) + 1));
                System.out.print(lastDirection);
            }

		// Шипы
            case 'x' -> {
                Player.hpSet(-5);
                cursorSet((movy + 1), ((movx * 2) + 1));
                System.out.print(lastDirection);
            }

		// Препятствие
            default -> {
                cursorSet((prevmovy + 1), ((prevmovx * 2) + 1));
                movy = prevmovy;
                movx = prevmovx;
                System.out.print(lastDirection);
            }
        }

		// Отображение координат игрока
		statusBar();
	}
    public static void statusBar() throws FileNotFoundException, InterruptedException {
		cursorSet((y+1), 0);
		rowClear();
		cursorSet((y+1), 0);
		System.out.println("X: "+movx+"\nY: "+movy);
		System.out.println("HP: "+Player.hpGet()+" ");
		System.out.println("WASD to move, C - build, X - destroy, R - restart, Q - quit.");
		cursorSet((y+1), (x*2-mapPath.length()-5));
		System.out.println("map: "+mapPath);
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
	
    public static void Start() throws InterruptedException, FileNotFoundException {
		load();
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
			cursorSet((prevmovy + 1), ((prevmovx * 2) + 1));
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
