package com.example.myapplication;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.widget.ImageView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowSystemClock;

import com.example.myapplication.ViewModel.FastMovementStrategy;

import java.time.Duration;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class EnemyTest {

    private FastMovementStrategy strategy;
    private ImageView mockImageView;

    @Before
    public void setUp() {
        mockImageView = mock(ImageView.class);
        strategy = new FastMovementStrategy();
    }

    @Test
    public void testMoveRightInitially() {
        when(mockImageView.getX()).thenReturn(0f);

        strategy.move(mockImageView);

        // Verify sprite moves to the right initially
        verify(mockImageView).setX(100f); // Assuming initial X is 0 and speed is 100
    }

    @Test
    public void testChangeDirectionAfterDuration() {
        when(mockImageView.getX()).thenReturn(0f);

        // Simulate the first move to the right
        strategy.move(mockImageView);
        // Advance time to simulate duration passing
        ShadowSystemClock.advanceBy(Duration.ofDays(FastMovementStrategy.DURATION));

        strategy.move(mockImageView);

        // Verify sprite moves to the left after duration
        verify(mockImageView).setX(-100f); // Assuming it changes direction and moves left
    }
}
