package org.agh.ics.oop;


public interface IPositionChangeObserver {
   void change(Vector2d oldPosition, Vector2d newPosition);
}
