package com.example.first;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dell on 20-Feb-18.
 */

public class TransperentMask extends View {
    public TransperentMask(Context context) {
        super(context);
    }

    public TransperentMask(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TransperentMask(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TransperentMask(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint Cirpaint = new Paint();
        Cirpaint.setColor(Color.TRANSPARENT);
        Cirpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        Paint BorderCir = new Paint();
        BorderCir.setColor(getResources().getColor(R.color.purple));
        BorderCir.setStyle(Paint.Style.STROKE);
        BorderCir.setStrokeWidth(60);

        Paint BorderCir2 = new Paint();
        BorderCir2.setColor(Color.WHITE);
        BorderCir2.setStyle(Paint.Style.STROKE);
        BorderCir2.setStrokeWidth(70);

        int x = (int) getResources().getDimension(R.dimen.cir_width);
        int y = (int) getResources().getDimension(R.dimen.cir_height);
        int rad = (int) getResources().getDimension(R.dimen.cir_radius);


      /* RectF rect1 = new RectF();
        rect1.set(0,0, getResources().getDimension(R.dimen.video_width)/2, getResources().getDimension(R.dimen.video_width)/2);
        canvas.drawOval(rect1,Cirpaint);*/


        canvas.drawCircle(x,y,rad,BorderCir2);
        canvas.drawCircle(x,y,rad,BorderCir);
        canvas.drawCircle(x,y,rad,Cirpaint);


    }
}
