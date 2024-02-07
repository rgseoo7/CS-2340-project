//package com.example.myapplication;
//
//import com.example.myapplication.Model.Player;
//import com.example.myapplication.ViewModel.DownMoveStrategy;
//import com.example.myapplication.ViewModel.LeftMovement;
//
//import org.junit.Test;
//import org.junit.Before;
//import static org.junit.Assert.assertEquals;
//
//
//public class WallTest {
//    //private GameScreenActivity gameScreen;
//    private Player player;
//    private DownMoveStrategy downMovement;
//    private LeftMovement leftMovement;
//    private int playerXPos;
//    private int playerYPos;
//    @Before
//    public void setUp() {
//        player = Player.getInstance();
//        player.setX(130);
//        player.setY(1200);
//
//
//        downMovement = new DownMoveStrategy();
//        leftMovement = new LeftMovement();
//    }
//    @Test
//    public void testPlayerBlockDown() {
//        int[] newPos = downMovement.move(player.getX(), player.getY());
//
//        // Check if not moving
//        assertEquals(130, player.getX());
//        assertEquals(1200, newPos[1]);
//    }
//
//    @Test
//    public void testPlayerBlockLeft() {
//        int[] newPos = leftMovement.move(player.getX(), player.getY());
//
//        // check if not moving
//        assertEquals(130, newPos[0]);
//        assertEquals(1200, player.getY());
//    }
//}
