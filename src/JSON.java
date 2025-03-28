import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSON {

    public class MapData {
        public int width;
        public int height;
        public int playerY;
        public int playerX;
        public String[] map;
        
        public Door[] doors;
        public static class Door {
            public int x;
            public int y;
            public int target;
            public int targetX;
            public int targetY;
        }

    }

    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
    static InputStream reader;
    static MapData mapData;

    public static char[][] loadMap(String jsonPath) throws FileNotFoundException {

        reader = JSON.class.getResourceAsStream(jsonPath);
        if (reader == null) {
            throw new FileNotFoundException("Файл " + jsonPath + " не найден!");
        }

        mapData = gson.fromJson(new InputStreamReader(reader), MapData.class);

        char[][] parsedMap = new char[mapData.height][mapData.width];

        for (int i = 0; i < mapData.height; i++) {
            parsedMap[i] = mapData.map[i].toCharArray();
        }
        return parsedMap;
    }
}