package com.example.administrator.imageview;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int [] image=new int[]{
            R.drawable.lijiang,R.drawable.qiao,R.drawable.shuangta,R.drawable.xiangbi,R.drawable.shui
    };
    int currentImg=2;
    private int alpha=255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button plus= (Button) findViewById(R.id.plus);
        final Button minus= (Button) findViewById(R.id.minus);
        final Button next = (Button) findViewById(R.id.next);
        final ImageView image1= (ImageView) findViewById(R.id.image1);
        final ImageView image2= (ImageView) findViewById(R.id.image2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setImageResource(image[++currentImg%image.length]);
            }
        });
        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view==plus){
                    alpha+=20;
                }
                if (view==minus){
                    alpha-=20;
                }
                if (alpha>=255){
                    alpha=255;
                }
                if (alpha<=0){
                    alpha=0;
                }
                image1.setImageAlpha(alpha);
            }
        };
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        image1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BitmapDrawable bitmapDrawable= (BitmapDrawable) image1.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                double scale=1.0*bitmap.getHeight()/image1.getHeight();
                int x= (int) (motionEvent.getX()*scale);
                int y= (int) (motionEvent.getY()*scale);
                if (x + 120 > bitmap.getWidth())
                {
                    x = bitmap.getWidth() - 120;
                }
                if (y + 120 > bitmap.getHeight())
                {
                    y = bitmap.getHeight() - 120;
                }
                image2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,120,120));
                return false;
            }
        });
    }
}
