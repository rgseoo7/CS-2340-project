package com.example.myapplication;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Intent;
import android.widget.ImageView;

import com.example.myapplication.Model.Player;
import com.example.myapplication.View.EndScreenLoss;
import com.example.myapplication.View.GameScreenActivity;
import com.example.myapplication.View.EndScreenLoss;
import com.example.myapplication.ViewModel.ErraticMovementStrategy;
import com.example.myapplication.ViewModel.FastMovementStrategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowSystemClock;

import java.time.Duration;

@RunWith(RobolectricTestRunner.class)
public class LucyTest4 {
    private ErraticMovementStrategy strategy;
    private ImageView mockImageView;

    @Before
    public void setUp() {
        mockImageView = mock(ImageView.class);
        strategy = new ErraticMovementStrategy();
    }

    @Test
    public void testMoveRight() {
        when(mockImageView.getX()).thenReturn(0f);

        strategy.move(mockImageView);

        verify(mockImageView).setX(25f);
    }

    @Test
    public void testChangeDirection() {
        when(mockImageView.getX()).thenReturn(0f);

        strategy.move(mockImageView);
        ShadowSystemClock.advanceBy(Duration.ofDays(FastMovementStrategy.DURATION));

        strategy.move(mockImageView);
        verify(mockImageView).setX(-25f);
    }
}



