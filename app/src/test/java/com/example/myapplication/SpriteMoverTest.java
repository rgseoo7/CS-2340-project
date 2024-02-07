package com.example.myapplication;

import com.example.myapplication.ViewModel.SpriteMover;
import com.example.myapplication.ViewModel.CollisionDetector;
import com.example.myapplication.ViewModel.MovementStrategy;
import com.example.myapplication.ViewModel.PlayerMovementObserver;

import android.widget.ImageView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.doCallRealMethod;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

@RunWith(RobolectricTestRunner.class)
public class SpriteMoverTest {

    private SpriteMover spriteMover;
    private CollisionDetector collisionDetector;
    private MovementStrategy movementStrategy;
    private PlayerMovementObserver movementObserver;

    @Before
    public void setUp() {
        collisionDetector = mock(CollisionDetector.class);
        movementStrategy = mock(MovementStrategy.class);
        movementObserver = mock(PlayerMovementObserver.class);
        spriteMover = new SpriteMover(collisionDetector);
        spriteMover.setMovementStrategy(movementStrategy);
        spriteMover.setMovementObserver(movementObserver);
    }

    @Test
    public void testSpriteMover_WithStrategyAndObserver_NoCollision() {
        when(movementStrategy.move(anyInt(), anyInt())).thenReturn(new int[] {5, 5});
        when(collisionDetector.isCollision(5, 5)).thenReturn(false);

        ImageView mockSprite = mock(ImageView.class);
        spriteMover.moveSprite(mockSprite, 0, 0);

        verify(movementObserver).onPlayerMove(5, 5);
    }

    @Test
    public void testSpriteMover_WithStrategyAndObserver_WithCollision() {
        when(movementStrategy.move(anyInt(), anyInt())).thenReturn(new int[] {5, 5});
        when(collisionDetector.isCollision(5, 5)).thenReturn(true);

        ImageView mockSprite = mock(ImageView.class);
        spriteMover.moveSprite(mockSprite, 0, 0);

        verify(movementObserver, never()).onPlayerMove(anyInt(), anyInt());
    }
}

