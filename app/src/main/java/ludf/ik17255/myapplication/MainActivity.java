package ludf.ik17255.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView v = new myView(MainActivity.this);
                Bitmap result = Bitmap.createBitmap(25, 25, Bitmap.Config.ARGB_8888);
                Canvas canvas1 = new Canvas(result);
                v.draw(canvas1);
                ConstraintLayout l = findViewById(R.id.drawLayout);
                l.addView(v);
            }
        });

    }

    public class myView extends View {

        private Paint myPaint;

        public myView(Context context){
            super(context);
            myPaint = new Paint();

        }

        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            myPaint.setColor(Color.TRANSPARENT);
            canvas.drawPaint(myPaint);
            myPaint.setColor(Color.BLACK);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(10);
            Random rand = new Random();
            canvas.drawRect(rand.nextInt(300), rand.nextInt(300), rand.nextInt(300)+300, rand.nextInt(300)+300, myPaint);
        }


    }


}