package byow.lab12;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        int x = p.x;
        int y = p.y;
        int len = 0;
        for (int j = y; j < y + s; j += 1) {
            for (int i = x - len; i < x + s + len; i += 1) {
                world[i][j] = t;
            }
            len += 1;
        }
        len -= 1;
        for (int j = y + s; j < y + 2*s; j += 1) {
            for (int i = x - len; i < x + s + len; i += 1) {
                world[i][j] = t;
            }
            len -= 1;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.WATER;
            case 4: return Tileset.TREE;
            case 5: return Tileset.SAND;
            case 6: return Tileset.GRASS;
            default: return Tileset.NOTHING;
        }
    }

    private static void combineHexagon(TETile[][] world, Position p, int s, int count) {
        int x = p.x;
        int y = p.y;
        TETile t;
        for (int c = 0; c < count; c++) {
            Position temp = new Position(x + c * (2 * s - 1), y + c * s);
            t = randomTile();
            addHexagon(world, temp, s , t);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // define the unit length of each hexagon
        int unit = 3;

        Position p = new Position(8, 24);
        for (int i = 0; i < 3; i++) {
            p.y -= unit * 2;
            combineHexagon(world, p, unit, 3 + i);
        }
        for (int j = 0; j < 2; j++) {
            p.x += (2 * unit - 1);
            p.y -= unit;
            combineHexagon(world, p, unit, 4 - j);
        }

        ter.renderFrame(world);
    }
}
