package ludf.ik17255.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private ConstraintLayout l;
    private float height, width, centerH, centerW, unit, scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.mButton);
        editTextNumber = findViewById(R.id.editTextNumber);
        l = findViewById(R.id.drawLayout);


        l.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            height = l.getMeasuredHeight();
            width = l.getMeasuredWidth();
            centerH = height/2;
            centerW = width/2;
            unit = centerW/4 - width/50;

        });


        mButton.setOnClickListener(view -> {
            String input = editTextNumber.getText().toString();
            if(input.length() > 0){
                scale = Float.parseFloat(input);

                if(scale > 4 || scale < 0.5){
                    editTextNumber.setError("0,5 <= number <= 4");
                }else{
                    myView v = new myView(MainActivity.this);

                    height = l.getMeasuredHeight();
                    width = l.getMeasuredWidth();
                    centerH = height/2;
                    centerW = width/2;
                    unit = centerW/4 - width/50;
                    Bitmap result = Bitmap.createBitmap((int)width, (int)height, Bitmap.Config.ARGB_8888);
                    Canvas canvas1 = new Canvas(result);
                    v.draw(canvas1);
                    l.addView(v);
                }
            }else{
                editTextNumber.setError("Please enter a number");
            }

        });

    }

    public class myView extends View {

        private final Paint myPaint;

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
            myPaint.setStrokeWidth(12);
            canvas.drawRect(centerW-scale*unit, centerH-scale*unit, centerW+scale*unit, centerH+scale*unit, myPaint);
        }


    }


}