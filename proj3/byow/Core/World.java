package byow.Core;

import byow.lab12.Position;
import byow.TileEngine.*;

import java.util.*;

public class World {

    public static TETile[][] createWorld(TETile[][] world, Random random) {
        Queue<Room> rooms = addRoom(world, random);
        addHallway(world, rooms, random);
        clearWorld(world);
        addAvatar(world, rooms, random);
        return world;
    }

    private static void horizonTile(TETile[][] world, Position p, int size, TETile t) {
        for (int i = 0; i < size; i++) {
            world[p.x + i][p.y] = t;
        }
    }

    private static void verticalTile(TETile[][] world, Position p, int size, TETile t) {
        for (int i = 0; i < size; i++) {
            world[p.x][p.y + i] = t;
        }
    }

    // Room
    private static Queue<Room> addRoom(TETile[][] world, Random random) {
        Set<int[]> pos = new HashSet<>();
        Queue<Room> rooms = new LinkedList<>();
        int count = 25 + random.nextInt(7);

        int sizeX = world.length;
        int sizeY = world[0].length;
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                world[x][y] = Tileset.WALL;
            }
        }

        while (count != 0) {
            int i = 1 + random.nextInt(sizeX - 10);
            int j = 1 + random.nextInt(sizeY - 8);
            if (pos.contains(new int[]{i, j})) continue;

            pos.add(new int[]{i, j});
            Position p = new Position(i, j);
            int w = 2 + random.nextInt(9);
            int h = 2 + random.nextInt(7);
            Room r = new Room(p, w, h);
            createRoom(world, r);
            rooms.add(r);
            count -= 1;
        }

        return rooms;
    }

    private static void createRoom(TETile[][] world, Room r) {
        Position p = r.pos;
        int w = r.width;
        int h = r.height;

        /*
        Position topleft = new Position(p.x, p.y + h - 1);
        Position bottomright = new Position(p.x + w - 1, p.y);

        horizonTile(world, p, w, Tileset.WALL);
        verticalTile(world, p, h, Tileset.WALL);
        horizonTile(world, topleft, w, Tileset.WALL);
        verticalTile(world, bottomright, h, Tileset.WALL);
        */

        for (int i = 0; i < w; i++) {
            Position temp = new Position(p.x + i, p.y);
            verticalTile(world, temp, h, Tileset.FLOOR);
        }
    }

    // Hallway
    private static void addHallway(TETile[][] world, Queue<Room> rooms, Random random) {
        LinkedList<Room> temp = new LinkedList<>();
        for (Room r: rooms) {
            temp.add(r);
        }

        while (temp.size() != 1) {
            Room r1 = temp.removeFirst();
            Room r2 = temp.peekFirst();
            Position p1 = r1.pos;
            Position p2 = r2.pos;

            int horizonX = Math.min(p1.x, p2.x);
            int horizonSize = Math.max(p1.x, p2.x) - horizonX;
            Position horizon = new Position(horizonX, Math.min(p1.y, p2.y));
            horizonTile(world, horizon, horizonSize, Tileset.FLOOR);

            int verticalY = Math.min(p1.y, p2.y);
            int verticalSize = Math.max(p1.y, p2.y) - verticalY;
            int verticalX;
            if (verticalY == p1.y) verticalX = p2.x;
            else verticalX = p1.x;
            Position vertical = new Position(verticalX, verticalY);
            verticalTile(world, vertical, verticalSize, Tileset.FLOOR);
        }
    }

    // count num of Floor around the Wall
    private static void clearWorld(TETile[][] world) {
        int[][] change = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}, {-1,-1}, {1,1}, {-1,1}, {1,-1}};
        int sizeX = world.length;
        int sizeY = world[0].length;
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                int count = 0;
                for (int[] c: change) {
                    int i = x + c[0];
                    int j = y + c[1];
                    if (i >= 0 && i < sizeX && j >= 0 && j < sizeY && world[i][j].equals(Tileset.FLOOR)) {
                        count += 1;
                    }
                }
                if (count == 0) world[x][y] = Tileset.NOTHING;
            }
        }
    }

    // Avatar and Door
    private static void addAvatar(TETile[][] world, Queue<Room> rooms, Random random) {
        List<Room> temp = new ArrayList<>();
        for (Room r: rooms) {
            temp.add(r);
        }

        int idx1 = random.nextInt(temp.size());
        Room born = temp.get(idx1);
        int x1 = born.pos.x + random.nextInt(born.width - 1);
        int y1 = born.pos.y + random.nextInt(born.height - 1);
        world[x1][y1] = Tileset.AVATAR;

        int idx2 = random.nextInt(temp.size());
        Room door = temp.get(idx2);
        int x2 = door.pos.x + random.nextInt(door.width - 1);
        int y2 = door.pos.y + random.nextInt(door.height - 1);
        world[x2][y2] = Tileset.LOCKED_DOOR;
    }

}
