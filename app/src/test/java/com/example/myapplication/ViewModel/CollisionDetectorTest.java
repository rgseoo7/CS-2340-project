package com.example.myapplication.ViewModel;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CollisionDetectorTest {

    @Test
    public void testCollisionWithWall() {
        CollisionDetector detector = new CollisionDetector();

        // Inside the wall boundaries (no collision)
        assertFalse(detector.isCollision(400, 600));

        // Left of the wall (collision)
        assertTrue(detector.isCollision(100, 600));

        // Right of the wall (collision)
        assertTrue(detector.isCollision(900, 600));

    }

    @Test
    public void testCollisionWithWall2() {
        CollisionDetector detector = new CollisionDetector();
        // Above the wall (collision)
        assertTrue(detector.isCollision(400, 200));

        // Below the wall (collision)
        assertTrue(detector.isCollision(400, 1300));
    }
}
