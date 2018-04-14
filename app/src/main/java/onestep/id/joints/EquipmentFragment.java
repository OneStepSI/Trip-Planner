package onestep.id.joints;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class EquipmentFragment extends Fragment {
    public static final String createPeralatanURL = "http://192.168.43.241/tripd/api/agenda.php?apicall=insert_peralatan";
    public static final String getPeralatanURL = "http://192.168.43.241/tripd/api/agenda.php?apicall=get_peralatan";
    private GridView gridView;
    private EquipmentGridAdapter adapter;
    private List<mEquipment> mEquipments = new ArrayList<mEquipment>();
    private ProgressDialog pDialog;
    private FloatingActionButton fab;
    private String id_user, pembawa, nama_peralatan;
    private int id_agenda;
    private EditText etPeralatan, etPembawa;
    private Button btnPrepare;

    public EquipmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        gridView = (GridView) view.findViewById(R.id.gridEquipment);





        adapter = new EquipmentGridAdapter(getActivity(), mEquipments);
        gridView.setAdapter(adapter);

        fab = (FloatingActionButton) view.findViewById(R.id.fabCost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_equipment, null);


                etPeralatan = (EditText) mView.findViewById(R.id.etPeralatan);
                etPembawa = (EditText) mView.findViewById(R.id.etPembawa);
                btnPrepare = (Button) mView.findViewById(R.id.prepare);


                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();

                btnPrepare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insertPeralatan();
                    }
                });
            }
        });

        getPeralatan();
        return view;
    }

    public void insertPeralatan() {
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        id_user = pref.getString("id_user", null);
        id_agenda = pref.getInt("id_agenda", 0);

        pembawa = etPembawa.getText().toString();
        nama_peralatan = etPeralatan.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, createPeralatanURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getActivity(), "peralatan sukses ditambahkan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getActivity(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_agenda", String.valueOf(id_agenda));
                map.put("username", pembawa);
                map.put("nama_peralatan", nama_peralatan);


                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getPeralatan() {
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        id_user = pref.getString("id_user", null);
        id_agenda = pref.getInt("id_agenda", 0);

        // Creating volley request obj
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getPeralatanURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(String.valueOf(getActivity()), response.toString());
                        hidePDialog();

                        // Parsing json

                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray jsonArray = obj.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objAgenda = jsonArray.getJSONObject(i);
                                mEquipment equipment = new mEquipment();

                                equipment.setEquip(objAgenda.getString("nama_peralatan"));
                                equipment.setPembawa(objAgenda.getString("username"));


                                mEquipments.add(equipment);
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
                VolleyLog.d(String.valueOf(getActivity()), "Error: " + error.getMessage());
                hidePDialog();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_agenda", String.valueOf(id_agenda));
                return map;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
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
}


