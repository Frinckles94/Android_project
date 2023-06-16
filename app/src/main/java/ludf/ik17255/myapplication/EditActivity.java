package ludf.ik17255.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class EditActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> records;
    private SQLiteDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button backButton = findViewById(R.id.buttonBack);
        Button clearButton = findViewById(R.id.buttonClear);
        ListView listView = findViewById(R.id.listView);

        db = new SQLiteDB(EditActivity.this);
        records = db.getValues();
        ListAdapter adapter = new SimpleAdapter(this, records, R.layout.list_row, new String[]{"ID", "Scaling", "Timestamp"}, new int[]{R.id.recordID, R.id.recordScaling, R.id.recordTimestamp});
        listView.setAdapter(adapter);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        clearButton.setOnClickListener(view -> {
            db.clear();
            finish();
            startActivity(getIntent());
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), ViewRecordActivity.class);
            HashMap<String, String> h = records.get(position);
            intent.putExtra("ID",h.get("ID"));
            intent.putExtra("Scaling",h.get("Scaling"));
            intent.putExtra("Timestamp",h.get("Timestamp"));
            startActivity(intent);
        });
    }


}