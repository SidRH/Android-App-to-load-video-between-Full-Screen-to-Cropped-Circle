package com.example.first;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Siddharth on 19-Feb-18.
 */

public class canvasMask extends View {
    public canvasMask(Context context) {
        super(context);
    }

    public canvasMask(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public canvasMask(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public canvasMask(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

   /* private float pixelsFromDp(float dp)
    {
        return dp * this.getContext().getResources().getDisplayMetrics().density;
    }
    private float dpFromPixels(float px)
    {
        return px / this.getContext().getResources().getDisplayMetrics().density;
    }*/

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);



        Paint Recpaint = new Paint();
        //Recpaint.setColor(Color.BLUE);
        LinearGradient rectGrad = new LinearGradient(0,0,canvas.getWidth(),canvas.getHeight(),getResources().getColor(R.color.grad1),getResources().getColor(R.color.grad2), Shader.TileMode.CLAMP);
        Recpaint.setShader(rectGrad);
        Rect rect = new Rect();
        rect.set(0,0,canvas.getWidth(),canvas.getHeight());
        canvas.drawRect(rect,Recpaint);


        Paint Cirpaint = new Paint();
        Cirpaint.setColor(Color.TRANSPARENT);
        Cirpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        Paint BorderCir = new Paint();
        BorderCir.setColor(getResources().getColor(R.color.purple));
        BorderCir.setStyle(Paint.Style.STROKE);
        BorderCir.setStrokeWidth(60);

       /* float x = dpFromPixels(xi);
        float y = dpFromPixels(xi);
        float rad = dpFromPixels(100);*/

        int x = (int) getResources().getDimension(R.dimen.cir_width);
        int y = (int) getResources().getDimension(R.dimen.cir_height);
        int rad = (int) getResources().getDimension(R.dimen.cir_radius);

        //Draw Circle dynamically around video
      /* RectF rect1 = new RectF();
        rect1.set(0,0, getResources().getDimension(R.dimen.video_width)/2, getResources().getDimension(R.dimen.video_width)/2);
        canvas.drawOval(rect1,Cirpaint);*/

        //canvas.drawCircle(x,y,rad,BorderCir);
        canvas.drawCircle(x,y,rad,Cirpaint);


    }
}
