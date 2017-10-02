package net.indiearmory.evolidle;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the abstract game class
        Game.startNewGame();

        // Populate this activity
        createMainMenu();
    }

    private void createMainMenu(){
        listView = (ListView) findViewById(R.id.menulistview);
        // Set up menu fields
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("World");
        fields.add("Laboratory");
        fields.add("Settings");
        // Populate list view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, fields);
        listView.setAdapter(arrayAdapter);
        // Create interactivity
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    // Go to gameWorld screen (LibGDX)
                    Intent intent = new Intent(MainActivity.this, AndroidLauncher.class);
                    startActivity(intent);
                }else if(i == 1){
                    // Go to lab screen (Android UI)
                    Intent intent = new Intent(MainActivity.this, LaboratoryActivity.class);
                    startActivity(intent);
                }else if(i == 2){
                    // Go to settings screen
                }
            }
        });
    }

}
