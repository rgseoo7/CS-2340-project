
package com.example.myapplication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.widget.ImageView;

import com.example.myapplication.ViewModel.CollisionDetector;
import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.FastEnemy;
import com.example.myapplication.Model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class CollisionDetectionTest {

    @Mock
    private Player mockPlayer;

    private Enemy testEnemy;
    private CollisionDetector collisionDetector;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testEnemy = new FastEnemy(mock(ImageView.class));
        when(mockPlayer.getPlayerHP()).thenReturn(100);
        collisionDetector = new CollisionDetector();
    }

    @Test
    public void testIsCollisionDetectsCollisionProperly() {
        final int leftBoundary = CollisionDetector.getWallLeft();
        final int rightBoundary = CollisionDetector.getWallRight();
        final int topBoundary = CollisionDetector.getWallTop();
        final int bottomBoundary = CollisionDetector.getWallBottom();

        assertFalse(collisionDetector.isCollision((leftBoundary + rightBoundary) / 2, (topBoundary + bottomBoundary) / 2));
        assertFalse(collisionDetector.isCollision(leftBoundary, topBoundary));
        assertTrue(collisionDetector.isCollision(leftBoundary - 1, topBoundary - 1));
    }

    @Test
    public void testNoCollisionDoesNotReduceHealth() {
        testEnemy.updatePlayerLocation(200, 200);
        verify(mockPlayer, never()).setPlayerHP(anyInt());
    }
}

