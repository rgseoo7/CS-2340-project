package com.example.myapplication;

import com.example.myapplication.View.Room1Activity;

import android.widget.ImageView;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.content.Intent;

/*
@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class Room1ActivityTest {

    private Room1Activity activity;
    private ImageView mockImageView;
    @Before
    public void setUp() {
        activity = mock(Room1Activity.class);
        mockImageView = mock(ImageView.class);
        when(activity.findViewById(R.id.iv_player_sprite)).thenReturn(mockImageView);
    }

    @Test
    public void testOnPlayerMove_TransitionConditionMet() {
        doCallRealMethod().when(activity).onPlayerMove(anyInt(), anyInt());
        activity.onPlayerMove(500, 417);
        verify(activity).transitionToNextActivity();
    }


    @Test
    public void testDecreaseValue() {
        doCallRealMethod().when(activity).decreaseValue();
        when(activity.getPlayerScore()).thenReturn(0);
        activity.decreaseValue();
        verify(activity).startActivity(any(Intent.class));
    }
}


 */