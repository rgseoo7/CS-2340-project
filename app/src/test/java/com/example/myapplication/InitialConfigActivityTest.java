//package com.example.myapplication;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import static org.junit.Assert.assertEquals;
//import com.example.myapplication.View.InitialConfigActivity;
//
//@RunWith(RobolectricTestRunner.class)
//public class InitialConfigActivityTest {
//    private InitialConfigActivity activity;
//    private RadioGroup difficultyRadioGroup;
//    private RadioButton radioButton;
//
//    @Before
//    public void setUp() {
//        activity = Robolectric.setupActivity(InitialConfigActivity.class);
//        difficultyRadioGroup = activity.findViewById(R.id.difficulty_radio_group);
//        radioButton = new RadioButton(activity);
//        difficultyRadioGroup.addView(radioButton);
//    }
//
//    @Test
//    public void testDifficultySelectionEasy() {
//        radioButton.setText("Easy");
//        radioButton.setChecked(true);
//
//        // Simulate the onCheckedChange event
//        difficultyRadioGroup.check(radioButton.getId());
//
//        // Assert that the playerHP variable is updated correctly
//        int playerHP = activity.getPlayerHP();
//        assertEquals(200, playerHP);
//    }
//
//
//    @Test
//    public void testDifficultySelectionMedium() {
//        radioButton.setText("Medium");
//        radioButton.setChecked(true);
//
//        // Simulate the onCheckedChange event
//        difficultyRadioGroup.check(radioButton.getId());
//
//        // Assert that the playerHP variable is updated correctly
//        int playerHP = activity.getPlayerHP();
//        assertEquals(150, playerHP);
//    }
//
//    @Test
//    public void testDifficultySelectionHard() {
//        radioButton.setText("Hard");
//        radioButton.setChecked(true);
//
//        // Simulate the onCheckedChange event
//        difficultyRadioGroup.check(radioButton.getId());
//
//        // Assert that the playerHP variable is updated correctly
//        int playerHP = activity.getPlayerHP();
//        assertEquals(100, playerHP);
//    }
//}


