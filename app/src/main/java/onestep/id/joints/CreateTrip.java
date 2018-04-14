package onestep.id.joints;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class CreateTrip extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btn_berangkat, btn_sampai,btnPlan;
    private RadioGroup RgTraveler,RgActivity;
    private RadioButton RbTraveler,RbActivity;
    private DatePickerDialogFragment mDatePickerDialogFragment;
    private String nama_agenda,traveler,activity,berangkat,datang,id_user;
    private EditText etAgenda;
    public static final String createAgendaURL = "http://192.168.43.241/tripd/api/agenda.php?apicall=insert_agenda";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        btn_berangkat = (Button)findViewById(R.id.buttonBerangkat);
        btn_sampai = (Button)findViewById(R.id.buttonSampai);
        btn_berangkat.setText("Keberangkatan\n DD/MM/YY");
        btn_sampai.setText("Kedatangan\n DD/MM/YY");
        mDatePickerDialogFragment = new DatePickerDialogFragment();

        etAgenda=(EditText)findViewById(R.id.agenda);
        btnPlan=(Button)findViewById(R.id.planit);

        RgTraveler=(RadioGroup)findViewById(R.id.rg_traveler);
        RgActivity=(RadioGroup)findViewById(R.id.rg_activity);



        btn_berangkat.setOnClickListener(this);
        btn_sampai.setOnClickListener(this);

        btnPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (RgTraveler.getCheckedRadioButtonId()==-1 || RgActivity.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplication(),"silahkan pilih kategori traveler dan kategori activity",Toast.LENGTH_LONG).show();
                }
                else {


                    insertAgenda();
                }


            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonBerangkat) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_START_DATE);
            mDatePickerDialogFragment.show(getFragmentManager(), "datePicker");
        } else if (id == R.id.buttonSampai) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_END_DATE);
            mDatePickerDialogFragment.show(getFragmentManager(), "datePicker");
        }
    }

    @SuppressLint("ValidFragment")
    public class DatePickerDialogFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        public static final int FLAG_START_DATE = 0;
        public static final int FLAG_END_DATE = 1;

        private int flag = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void setFlag(int i) {
            flag = i;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (flag == FLAG_START_DATE) {
                btn_berangkat.setText(format.format(calendar.getTime()));
            } else if (flag == FLAG_END_DATE) {
                btn_sampai.setText(format.format(calendar.getTime()));
            }
        }

    }
    public void insertAgenda(){
        SharedPreferences pref = this.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        id_user = pref.getString("id_user",null);

        // get selected radio button from radioGroup
        int selectedIdTraveler = RgTraveler.getCheckedRadioButtonId();
        int selectedIdActivity = RgActivity.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RbTraveler = (RadioButton) findViewById(selectedIdTraveler);
        RbActivity=(RadioButton)findViewById(selectedIdActivity);
        traveler=RbTraveler.getText().toString();
        activity=RbActivity.getText().toString();
        nama_agenda=etAgenda.getText().toString();
        berangkat=btn_berangkat.getText().toString();
        datang=btn_sampai.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, createAgendaURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getApplicationContext(), "Agenda sukses ditambahkan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateTrip.this, Main2Activity.class);
                                startActivity(intent);

                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateTrip.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user", id_user);
                map.put("nama_agenda", nama_agenda);
                map.put("tanggal_berangkat", berangkat);
                map.put("tanggal_kedatangan", datang);
                map.put("kategori_traveler", traveler);
                map.put("kategori_activity", activity);

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
