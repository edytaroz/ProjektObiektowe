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

    public MapDirection next() {
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
                System.out.println("Problem next");
                return null;
        }
    }

    public MapDirection previous() {
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
                System.out.println("Problem previous");
                return null;
        }
    }

    public int MapDirectionToInt(MapDirection direction) {
        switch (direction) {
            case NORTHEAST: return 1;
            case SOUTHEAST: return 3;
            case SOUTHWEST: return 5;
            case NORTHWEST: return 7;
            case EAST: return 2;
            case NORTH: return 0;
            case SOUTH: return 4;
            case WEST: return 6;
            default:
                System.out.println("Problem MapToInt");
                return -1;
        }
    }

    public MapDirection intToMapDirection(int n) {
        switch (n) {
            case 1: return MapDirection.NORTHEAST;
            case 3: return MapDirection.SOUTHEAST;
            case 5: return MapDirection.SOUTHWEST;
            case 7: return MapDirection.NORTHWEST;
            case 2: return MapDirection.EAST;
            case 0: return MapDirection.NORTH;
            case 4: return MapDirection.SOUTH;
            case 6: return MapDirection.WEST;
            default:
                System.out.println("Problem IntToMap");
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case EAST: return "E";
            case SOUTH: return "S";
            case WEST: return "W";
            case NORTH: return "N";
            case SOUTHEAST: return "SE";
            case NORTHEAST: return "NE";
            case SOUTHWEST: return "SW";
            case NORTHWEST: return "NW";
            default:
                System.out.println("Problem String");
                return null;
        }
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case EAST: return new Vector2d(1, 0);
            case SOUTH: return new Vector2d(0, -1);
            case WEST: return new Vector2d(-1, 0);
            case NORTH: return new Vector2d(0, 1);
            case SOUTHEAST: return new Vector2d(1, -1);
            case NORTHEAST: return new Vector2d(1, 1);
            case SOUTHWEST: return new Vector2d(-1, -1);
            case NORTHWEST: return new Vector2d(-1, 1);
            default:
                System.out.println("Problem toUnitVector");
                return null;
        }
    }

    public MapDirection oposite() {
        switch (this) {
            case EAST -> {
                return MapDirection.WEST;
            }
            case SOUTH -> {
                return MapDirection.NORTH;
            }
            case WEST -> {
                return MapDirection.EAST;
            }
            case NORTH -> {
                return MapDirection.SOUTH;
            }
            case SOUTHEAST -> {
                return MapDirection.NORTHWEST;
            }
            case NORTHEAST -> {
                return MapDirection.SOUTHWEST;
            }
            case SOUTHWEST -> {
                return MapDirection.NORTHEAST;
            }
            case NORTHWEST -> {
                return MapDirection.SOUTHEAST;
            }
            default -> {
                System.out.println("Problem oposite");
                return null;
            }
        }
    }
}
