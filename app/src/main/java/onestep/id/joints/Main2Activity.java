package onestep.id.joints;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView listView;
    private BerandaListAdapter adapter;
    private List<mBeranda> mBerandas;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Trip-D");
        listView = (ListView) findViewById(R.id.list);
        mBerandas = new ArrayList<>();

        mBerandas.add(new mBeranda(1, "Jember"));

        adapter = new BerandaListAdapter(getApplicationContext(), mBerandas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fabBeranda);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, CreateTrip.class);
                startActivity(i);
            }
        });
    }
}
