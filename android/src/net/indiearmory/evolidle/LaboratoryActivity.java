package net.indiearmory.evolidle;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LaboratoryActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratory);

        // Populate activity
        createMenu();
    }

    private void createMenu(){
        listView = (ListView) findViewById(R.id.menulistview);
        // Set up menu fields
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("Body");
        fields.add("Brain");
        fields.add("Genetics");
        // Populate list view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, fields);
        listView.setAdapter(arrayAdapter);
        // Create interactivity
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    // TODO
                }else if(i == 1){
                    // TODO
                }else if(i == 2){
                    // TODO
                }
            }
        });
    }
}
