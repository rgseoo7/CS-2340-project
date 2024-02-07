package com.example.myapplication.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.myapplication.Model.Player;
import com.example.myapplication.R;

public class GridView extends View {
    public GridView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        canvas.drawBitmap(map, 0, 0, null);

        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.BLACK);
        int gridSize = 16;

        for (int x = 0; x < getWidth(); x += gridSize) {
            canvas.drawLine(x, 0, x, getHeight(), gridPaint);
        }

        for (int y = 0; y < getHeight(); y += gridSize) {
            canvas.drawLine(0, y, getWidth(), y, gridPaint);
        }

        Bitmap spriteImage = BitmapFactory.decodeResource(getResources(), R.drawable.sprite1);
        int spriteX = Player.getInstance().getX();
        int spriteY = Player.getInstance().getY();
        canvas.drawBitmap(spriteImage, spriteX, spriteY, null);
    }
}
