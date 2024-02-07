package com.example.myapplication;
import android.widget.EditText;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.example.myapplication.View.InitialConfigActivity;

public class InitialConfigActivityTest2 {

    private EditText playerNameInput;

    @Before
    public void setUp() {

        // create Name input
        playerNameInput = InitialConfigActivity.getPlayerNameInput();
    }

    @Test
    public void testPlayerNameWhitespaceOnly() {

        String whitespaceName = "     ";
        //simulate the event
        InitialConfigActivity.getPlayerNameInput(whitespaceName);

        // check if the name is valid
        boolean isNameValid = InitialConfigActivity.isPlayerNameValid();

        // Assert that the name is incorrect
        assertEquals(false, isNameValid);
        //repeat until player inputs correct name
    }

    @Test
    public void testPlayerNameNull() {

        String nullName = null;
        //simulate the event a player inputs null
        InitialConfigActivity.getPlayerNameInput(nullName);

        // check if the name is valid
        boolean isNameValid = InitialConfigActivity.isPlayerNameValid();

        // Assert that the name is incorrect
        assertEquals(false, isNameValid);
        //repeat until player inputs correct name
    }

    @Test
    public void testPlayerNameEmpty() {
        String emptyName = "";
        // simulate the event a player inputs nothing
        InitialConfigActivity.getPlayerNameInput(emptyName);

        // check if the name is valid
        boolean isNameValid = InitialConfigActivity.isPlayerNameValid();

        // Assert that the name is incorrect
        assertEquals(false, isNameValid);

        //repeat until player inputs correct name
    }
}
