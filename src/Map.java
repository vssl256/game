import java.util.Scanner;

public class Map {
    public static void Start() {
		Scanner sc = new Scanner(System.in);
		int x = 33;
		int y = 11;
		int movx = x/2;
		int movy = y/2;
		char[][] map = new char[y][x];
		boolean rep = true;
		while (rep) { 
			for (int cy = 0; cy < y; cy++) {
				for (int cx = 0; cx < x; cx++) {
					map[cy][cx] = '▫';
					if ((cy<1||cx<1)||(cy>y-2||cx>x-2)) map[cy][cx] = '■';
					if (cx == movx && cy == movy) map[cy][cx] = '▲';
					System.out.print(map[cy][cx]+" ");
				}
				System.out.println();
			}
            
			char mov = sc.next().charAt(0);
			switch (mov) {
				case 'w' -> movy--;
				case 's' -> movy++;
				case 'a' -> movx--;
				case 'd' -> movx++;
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
