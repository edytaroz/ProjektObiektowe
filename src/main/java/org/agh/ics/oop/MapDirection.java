package org.agh.ics.oop;


public enum MapDirection {
    NORTH,
    SOUTHEAST,
    NORTHEAST,
    SOUTHWEST,
    NORTHWEST,
    SOUTH,
    WEST,
    EAST;

    public MapDirection next () {
        switch (this) {
            case EAST: return MapDirection.SOUTHEAST;
            case SOUTH: return MapDirection.SOUTHWEST;
            case WEST: return MapDirection.NORTHWEST;
            case NORTH: return MapDirection.NORTHEAST;
            case SOUTHEAST: return MapDirection.SOUTH;
            case NORTHEAST: return MapDirection.EAST;
            case SOUTHWEST: return MapDirection.WEST;
            case NORTHWEST: return MapDirection.NORTH;
            default:
                System.out.println("Coś nie tak");
                return null;
        }
    }
    public MapDirection previous () {
        switch (this) {
            case EAST: return MapDirection.NORTHEAST;
            case SOUTH: return MapDirection.SOUTHEAST;
            case WEST: return MapDirection.SOUTHWEST;
            case NORTH: return MapDirection.NORTHWEST;
            case SOUTHEAST: return MapDirection.EAST;
            case NORTHEAST: return MapDirection.NORTH;
            case SOUTHWEST: return MapDirection.SOUTH;
            case NORTHWEST: return MapDirection.WEST;
            default:
                System.out.println("Coś nie tak");
                return null;
        }
    }
    public Vector2d toUnitVector () {
        switch (this) {
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case NORTH: return new Vector2d(0,1);
            case SOUTHEAST: return new Vector2d(1,-1);
            case NORTHEAST: return new Vector2d(1,1);
            case SOUTHWEST: return new Vector2d(-1,-1);
            case NORTHWEST: return new Vector2d(-1,1);
            default:
                System.out.println("Coś nie tak");
                return null;
        }
    }
}
