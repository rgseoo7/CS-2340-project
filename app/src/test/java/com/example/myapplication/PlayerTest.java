package com.example.myapplication;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.example.myapplication.Model.Player;
/*
public class PlayerTest {
    Player player1 = Player.getInstance();
    Player player2 = Player.getInstance();

    @Test
    public void testPlayerIsSingleton() {
        // player1 and player2 should refer to same object in memory
        assertTrue(player1.equals(player2));

        player1.updateX(-150);
        player1.updateY(-150);

        // changes to player1 should apply to player2 and vice versa
        player1.updateX(15);
        assertEquals(15, player2.getX(), 0.1);
        assertFalse(player2.isLookingLeft());

        player2.updateY(20);
        assertEquals(20, player1.getY(), 0.1);
    }

    @Test
    public void testPlayerMovement() {
        // sets player1 x coordinate to 400, furthest to the right allowed
        player1.updateX(385);
        assertFalse(player1.isLookingLeft());

        // player1 should not be allowed to go past x = 400
        player1.updateX(10);
        assertEquals(400, player1.getX(), 0.1);

        // player should be looking left after travelling left
        player1.updateX(-5);
        assertTrue(player1.isLookingLeft());

        // sets player1 y coordinate to 0, furthest down allowed
        player1.updateY(-20);
        assertEquals(0, player1.getY(), 0.1);

        // player1 should not be allowed to go below y = 0
        player1.updateY(-5);
        assertEquals(0, player1.getY(), 0.1);

    }

}

 */
