package onestep.id.joints;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private ListView listView;
    private BerandaListAdapter adapter;
    private List<mBeranda> mBerandas=new ArrayList<mBeranda>();
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ProgressDialog pDialog;
    private String id_user;
    private int id_agenda;
    public static final String AgendaURL = "http://10.132.4.66/tripd/api/agenda.php?apicall=get_all_agenda_user"; //wifi unej 10.132.1.216

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Trip-D");
        listView = (ListView) findViewById(R.id.list);
        adapter = new BerandaListAdapter(getApplicationContext(), mBerandas);
        listView.setAdapter(adapter);


        fab = (FloatingActionButton) findViewById(R.id.fabBeranda);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, CreateTrip.class);
                startActivity(i);
            }
        });

        getAgenda();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBeranda m =mBerandas.get(i);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("id_agenda",Integer.parseInt(m.getId_agenda()));
                editor.commit();

                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    public void getAgenda(){
        pDialog = new ProgressDialog(Main2Activity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        SharedPreferences pref = this.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        id_user = pref.getString("id_user",null);
        id_agenda=pref.getInt("id_agenda",0);

        // Creating volley request obj
        StringRequest stringRequest = new StringRequest(Request.Method.POST,AgendaURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(String.valueOf(Main2Activity.this), response.toString());
                        hidePDialog();

                        // Parsing json

                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray jsonArray = obj.getJSONArray("data");
                            for(int i=0; i<jsonArray.length(); i++) {
                                JSONObject objAgenda = jsonArray.getJSONObject(i);
                                mBeranda beranda = new mBeranda();

                                beranda.setNama_agenda(objAgenda.getString("nama_agenda"));
                                beranda.setId_agenda(objAgenda.getString("id_agenda"));


                                mBerandas.add(beranda);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(String.valueOf(Main2Activity.this), "Error: " + error.getMessage());
                hidePDialog();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user",id_user);
                return map;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
