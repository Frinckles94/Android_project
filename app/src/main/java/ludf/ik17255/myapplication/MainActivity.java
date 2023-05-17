package ludf.ik17255.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private ConstraintLayout l;
    private myView v;
    private float height, width, centerH, centerW, unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.mButton);
        editTextNumber = findViewById(R.id.editTextNumber);
        l = findViewById(R.id.drawLayout);

        v = new myView(MainActivity.this);
        l.addView(v);

        l.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = l.getMeasuredHeight();
                width = l.getMeasuredWidth();
                centerH = height/2;
                centerW = width/2;
                unit = centerW/4 - width/50;
                l.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


        mButton.setOnClickListener(view -> {
            String input = editTextNumber.getText().toString();
            if(input.length() > 0){
                float scale = Float.parseFloat(input);
                if(scale > 4 || scale < 0.5){
                    Toast.makeText(MainActivity.this, "Please enter a number in range [0,5 ... 4]", Toast.LENGTH_SHORT).show();
                }else{
                    v.addRectangle(scale);
                }
            }else{
                Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public class myView extends View {

        private final Paint myPaint;
        private final List<Float> RectScaling;

        public myView(Context context){
            super(context);

            myPaint = new Paint();
            myPaint.setColor(Color.BLACK);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(12);

            RectScaling = new ArrayList<>();

        }

        public void addRectangle(float scalingC){
            RectScaling.add(scalingC);
            invalidate();
        }

        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            for(float scalingC: RectScaling){
                canvas.drawRect(centerW-scalingC*unit, centerH-scalingC*unit, centerW+scalingC*unit, centerH+scalingC*unit, myPaint);
            }

        }


    }


}