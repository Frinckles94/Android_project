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
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.mButton);
        editTextNumber = findViewById(R.id.editTextNumber);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editTextNumber.getText().toString();
                if(input.length() > 0){
                    float scale = Float.parseFloat(input);

                    if(scale > 4 || scale < 0.5){
                        editTextNumber.setError("Number should be greater than 0.5 and less than 4");
                    }else{

                        myView v = new myView(MainActivity.this);
                        int height = findViewById(R.id.drawLayout).getMeasuredHeight();
                        int width = findViewById(R.id.drawLayout).getMeasuredWidth();
                        int padding = 10;

                        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                        Canvas canvas1 = new Canvas(result);
                        v.draw(canvas1);
                        ConstraintLayout l = findViewById(R.id.drawLayout);
                        l.addView(v);


                        //mButton.setText(String.valueOf(scale));
                        mButton.setText(String.valueOf(height));
                    }
                }else{
                    editTextNumber.setError("Please enter a number");
                }

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