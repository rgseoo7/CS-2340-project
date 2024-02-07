package com.example.myapplication;
import com.example.myapplication.Model.Player;
import com.example.myapplication.ViewModel.DownMoveStrategy;
import com.example.myapplication.ViewModel.LeftMovement;
import com.example.myapplication.ViewModel.RightMovement;
import com.example.myapplication.ViewModel.UpMoveStrategy;
import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//public class MovementTest {
//    private int playerXPos;
//    private int playerYPos;
//    private LeftMovement leftMovement;
//    private RightMovement rightMovement;
//    private DownMoveStrategy downMovement;
//    private UpMoveStrategy upMovement;
//
//    @Before
//    public void setUp() {
//        //creation
//        playerXPos = Player.getInstance().getX();
//        playerYPos = Player.getInstance().getY();
//        leftMovement = new LeftMovement();
//        rightMovement = new RightMovement();
//        downMovement = new DownMoveStrategy();
//        upMovement = new UpMoveStrategy();
//    }
//    @Test
//    public void testMovementLeft() {
//        //test to see if moves left
//        //simulate event
////        Player.getInstance().setX(100);
////        Player.getInstance().setY(100);
//        int[] newPos = leftMovement.move(playerXPos, playerYPos);
//        //assert equals
//        assertEquals(playerXPos - 50, newPos[0]); // Check the updated X position
//        assertEquals(playerYPos, newPos[1]);
//    }
//    @Test
//    public void testMovementRight() {
//        //test to see if moves right
//        //simulate event
////        Player.getInstance().setX(100);
////        Player.getInstance().setY(100);
//        int[] newPos = rightMovement.move(playerXPos, playerYPos);
//        //assert equals
//        assertEquals(playerXPos + 50, newPos[0]);
//        assertEquals(playerYPos, newPos[1]);
//    }
//
//    @Test
//    public void testMovementDown() {
//        int[] newPos = downMovement.move(playerXPos, playerYPos);
//        assertEquals(playerXPos, newPos[0]);
//        assertEquals(playerYPos + 50, newPos[1]);
//    }
//    @Test
//    public void testMovementUp() {
//        int[] newPos = upMovement.move(playerXPos, playerYPos);
//        assertEquals(playerXPos, newPos[0]);
//        assertEquals(playerYPos - 50, newPos[1]);
//    }
//
//
//    @After
//    public void tearDown() {
//        Player.getInstance().setX(playerXPos);
//        Player.getInstance().setY(playerYPos);
//    }
//
//}
