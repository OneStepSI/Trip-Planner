package onestep.id.joints;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CostFragment extends Fragment {
    private ExpandableListView listView;
    private ExpandableListAdapter adapter;
    private List<String> list;
    private HashMap<String, List<String>> listHashMap;
    private FloatingActionButton fab;
    public static final String insertCostURL = "http://192.168.43.241/tripd/api/agenda.php?apicall=insert_cost";
    public static final String getCostURL = "http://192.168.43.241/tripd/api/agenda.php?apicall=get_cost_agenda";
    private String id_user,nama_keperluan,detail,jumlah,kategori_cost;
    private int id_agenda;
    private EditText etKeperluan,etDetail,etDana;
    private RadioGroup RgKategori;
    private RadioButton RbKategori;
    private Button btn_cost_dialog;

    public CostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cost, container, false);
        listView = (ExpandableListView) view.findViewById(R.id.list_cost);
        initData();
        adapter = new CostExpandableListAdapter(getActivity(), list, listHashMap);
        listView.setAdapter(adapter);
        fab = (FloatingActionButton)view.findViewById(R.id.fabCost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_cost, null);

                btn_cost_dialog = (Button)mView.findViewById(R.id.buttonCostDialog);
                etKeperluan =(EditText) mView.findViewById(R.id.etKeperluan);
                etDetail=(EditText)mView.findViewById(R.id.etDtail);
                etDana=(EditText)mView.findViewById(R.id.etDana);
                RgKategori=(RadioGroup)mView.findViewById(R.id.RgKategoriCost);









                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();

                btn_cost_dialog.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {

                        if (RgKategori.getCheckedRadioButtonId()==-1){
                            Toast.makeText(getActivity(),"silahkan pilih kategori cost",Toast.LENGTH_LONG).show();
                        }
                        else {

                            int id= RgKategori.getCheckedRadioButtonId();
                            //get radio button view
                            View radioButton = RgKategori.findViewById(id);
                            // return index of selected radiobutton
                            int radioId = RgKategori.indexOfChild(radioButton);
                            // based on index getObject of radioButton
                            RadioButton btn = (RadioButton) RgKategori.getChildAt(radioId);
                            //After getting radiobutton you can now use all its methods
                            kategori_cost = (String) btn.getText();


                           // int selectedIdCost = RgKategori.getCheckedRadioButtonId();
                            // find the radiobutton by returned id
                           // RbKategori = (RadioButton)mView.findViewById(selectedIdCost);
                         //   kategori_cost=RbKategori.getText().toString();
                            insertCost();


                        }

                    }
                });
            }
        });
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        listHashMap = new HashMap<>();

        list.add("Transportaion");
        list.add("Food");
        list.add("Hotel");
        list.add("Tiket Wisata");
        list.add("Lain-Lain");


        List<String> list1 = new ArrayList<>();
        list1.add("Pesawat");
        list1.add("Mobil");

        List<String> list2 = new ArrayList<>();
        list2.add("Makan 6x");
        list2.add("oleh-oleh");

        listHashMap.put(list.get(0),list1);
        listHashMap.put(list.get(1),list2);
        listHashMap.put(list.get(2),list1);
        listHashMap.put(list.get(3),list2);
        listHashMap.put(list.get(4),list1);

    }
    public void insertCost(){
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        id_user = pref.getString("id_user", null);
        id_agenda = pref.getInt("id_agenda", 0);




        nama_keperluan=etKeperluan.getText().toString();
        detail=etDetail.getText().toString();
        jumlah=etDana.getText().toString();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertCostURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getActivity(), "Cost sukses ditambahkan", Toast.LENGTH_SHORT).show();
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
                map.put("nama_keperluan",nama_keperluan );
                map.put("detail", detail);
                map.put("jumlah", jumlah);
                map.put("kategori_cost",kategori_cost);

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
