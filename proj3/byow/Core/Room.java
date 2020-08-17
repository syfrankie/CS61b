package byow.Core;

import byow.lab12.Position;

public class Room {
    public Position pos;
    public int width;
    public int height;

    public Room(Position p, int w, int h) {
        pos = p;
        width = w;
        height = h;
    }
}
