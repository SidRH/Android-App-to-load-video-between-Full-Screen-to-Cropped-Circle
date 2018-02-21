package com.example.first;

import android.animation.Animator;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    View cardView;
    VideoView videoView;
    FrameLayout f_layout;
    RelativeLayout r_layout;
    RelativeLayout.LayoutParams pl;
    RelativeLayout.LayoutParams p2;
    FrameLayout.LayoutParams cardParam;
    com.example.first.canvasMask cm;
    com.example.first.TransperentMask Tm;

    boolean flag = true;
    Boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = (View)findViewById(R.id.card_view);
        videoView = (VideoView)findViewById(R.id.videoView);
        f_layout = (FrameLayout)findViewById(R.id.frameLayout);
        r_layout = (RelativeLayout)findViewById(R.id.relLayout);
        cm = (com.example.first.canvasMask)findViewById(R.id.mask);
        Tm = (com.example.first.TransperentMask)findViewById(R.id.Tmask);


        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ "/" +R.raw.testvid));
        videoView.start();


       final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Shrink();
                handler.postDelayed(this, 2000);
            }
        }, 2000);

    }
    

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Shrink()
    {

       if(flag)
       {

        /*pl.width=900;
        pl.height=900;
        //pl.bottomMargin=500;
        pl.topMargin=400;*/
        pl = new RelativeLayout.LayoutParams(videoView.getLayoutParams());
        pl.width = (int) getResources().getDimension(R.dimen.video_width);
        pl.height= (int) getResources().getDimension(R.dimen.video_height);
        pl.topMargin= (int) getResources().getDimension(R.dimen.Top_margin);
        pl.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pl.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
        pl.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
        pl.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pl.addRule(RelativeLayout.CENTER_HORIZONTAL);

       /* videoView.setLayoutParams(pl);
        cm.setVisibility(View.VISIBLE);
        f_layout.setVisibility(View.VISIBLE);
        */

        SetCard();
        animateReveal();
        flag = false;
       }
       else
       {
           p2 = new RelativeLayout.LayoutParams(videoView.getLayoutParams());
           p2.width= ViewGroup.LayoutParams.FILL_PARENT;
           p2.height =  ViewGroup.LayoutParams.FILL_PARENT;
           p2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
           p2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
           p2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
           p2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
          // pl.addRule(RelativeLayout.CENTER_HORIZONTAL);
          // videoView.setLayoutParams(p2);
           animateHide();

           /*videoView.setLayoutParams(p2);
           cm.setVisibility(View.GONE);
           f_layout.setVisibility(View.GONE);*/
           flag = true;

       }

    }

    public void SetCard() {

        cardParam = new FrameLayout.LayoutParams(cardView.getLayoutParams());

        int card_height_min = (int) getResources().getDimension(R.dimen.card_hei_min);
        int card_height_max = (int) getResources().getDimension(R.dimen.card_hei_max);
        Random rand = new Random();
        int  card_height = rand.nextInt(card_height_max) + card_height_min;

        cardParam.height = (int) pxToDp(card_height);
        cardParam.width = FrameLayout.LayoutParams.MATCH_PARENT;
        cardParam.topMargin = (int) getResources().getDimension(R.dimen.card_top_margin);
        cardParam.leftMargin = (int) getResources().getDimension(R.dimen.card_left_margin);
        cardParam.rightMargin= (int) getResources().getDimension(R.dimen.card_right_margin);
        cardView.setLayoutParams(cardParam);
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        return px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateReveal() {

        int cx = f_layout.getMeasuredWidth() / 2;
        int cy = f_layout.getMeasuredHeight() / 2;
        int startRadius = 0;
        int endRadius = Math.max(f_layout.getWidth(),f_layout.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(f_layout, cx, cy, startRadius,endRadius);
        anim.setDuration(1000);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //videoView.setLayoutParams(pl);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        videoView.setLayoutParams(pl);
        cm.setVisibility(View.VISIBLE);
        f_layout.setVisibility(View.VISIBLE);
        anim.start();
    }






    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateHide() {

        int Mx = (int) getResources().getDimension(R.dimen.cir_width);
        int My = (int) getResources().getDimension(R.dimen.cir_height);

        int startRadius = Math.max(cm.getWidth(),cm.getHeight());
        int endRadius = (int) getResources().getDimension(R.dimen.cir_radius);

        Animator anim1 = ViewAnimationUtils.createCircularReveal(cm, Mx, My,startRadius,endRadius);
        anim1.setDuration(500);

        anim1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                cm.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        f_layout.setVisibility(View.GONE);
        videoView.setLayoutParams(p2);
        anim1.start();


    }
    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        }
        else
        {
            Toast.makeText(this, "Press Back again to Exit.",Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3000);

        }

    }

}
