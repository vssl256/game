import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class JSON {
    public class MapData {
        public int width;
        public int height;
        public int playerY;
        public int playerX;
        public int doorY;
        public int doorX;
        public String[] map;

    }
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
    static Reader reader;
    static MapData mapData;


    public static char[][] loadMap(String jsonPath) throws FileNotFoundException {
        reader = new FileReader(jsonPath);

        mapData = gson.fromJson(reader, MapData.class);

        char[][] parsedMap = new char[mapData.height][mapData.width];

        for (int i = 0; i < mapData.height; i++) {
            parsedMap[i] = mapData.map[i].toCharArray();
        }
        return parsedMap;
    }
}


//"map": [
//    "■ ■ ■ ■ ■ ■ ■",
//    "■ . . . . . ■",
//    "■ . . . . . ■",
//    "■ . . . . . ■",
//    "■ . . . . . ■",
//    "■ . . . . . ■",
//    "■ ■ ■ ■ ■ ■ ■"
//]