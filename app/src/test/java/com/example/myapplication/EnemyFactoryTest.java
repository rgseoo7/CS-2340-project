
package com.example.myapplication;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.widget.ImageView;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.EnemyFactory;
import com.example.myapplication.Model.FastEnemy;
import com.example.myapplication.Model.SlowEnemy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class EnemyFactoryTest {

    private ImageView mockSprite;

    @Before
    public void setUp() {
        mockSprite = mock(ImageView.class);
    }

    @Test
    public void testCreateFastEnemy() {
        Enemy enemy = EnemyFactory.createEnemy(EnemyFactory.EnemyType.FAST, mockSprite);
        assertNotNull(enemy);
        assertTrue(enemy instanceof FastEnemy);
    }

    @Test
    public void testCreateSlowEnemy() {
        Enemy enemy = EnemyFactory.createEnemy(EnemyFactory.EnemyType.SLOW, mockSprite);
        assertNotNull(enemy);
        assertTrue(enemy instanceof SlowEnemy);
    }
}

