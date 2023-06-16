package ludf.ik17255.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewRecordActivity extends AppCompatActivity {

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);


        extras = getIntent().getExtras();
        TextView id = findViewById(R.id.id);
        id.setText(extras.getString("ID"));
        TextView sc = findViewById(R.id.scaling);
        sc.setText(extras.getString("Scaling"));
        TextView ts = findViewById(R.id.timestamp);
        ts.setText(extras.getString("Timestamp"));

        Button backButton = findViewById(R.id.buttonBack);
        Button deleteButton = findViewById(R.id.buttonDelete);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditActivity.class);
            startActivity(intent);
        });

        deleteButton.setOnClickListener(view -> {
            SQLiteDB db = new SQLiteDB(ViewRecordActivity.this);
            db.delete(extras.getString("ID"));
            Intent intent = new Intent(this, EditActivity.class);
            startActivity(intent);
        });



    }
}