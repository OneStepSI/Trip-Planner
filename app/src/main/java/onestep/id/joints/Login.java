package onestep.id.joints;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText etUser,etPass;
    Button login;
    String username,password,id_user,nama,email,token;
    int id_agenda;
    public static final String loginURL = "http://192.168.1.5/tripd/api/login.php"; //wifi unej 10.132.1.216
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUser = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);
        login =(Button)findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        username = etUser.getText().toString().trim();
        password = etPass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                                //JSONObject user = jObj.getJSONObject("user");
                                id_user = jObj.getString("id_user");
                                nama = jObj.getString("nama").trim();
                                username=jObj.getString("username").trim();
                                email = jObj.getString("email").trim();
                                token = jObj.getString("token");
                                id_agenda=0;

                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("id_user", id_user); // Storing string
                                editor.putString("username",username);
                                editor.putString("email",email);
                                editor.putString("nama",nama);
                                editor.putInt("id_agenda",id_agenda);

                                editor.commit();

                                Intent intent = new Intent(Login.this, Main2Activity.class);
                                intent.putExtra("id_user", id_user);
                                intent.putExtra("username", username);
                                intent.putExtra("email", email);
                                intent.putExtra("nama",nama);
                                intent.putExtra("id_agenda",id_agenda);
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



                       /* if(response.trim().equals("success")){
                            openProfile();
                        }else{
                            Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        }*/

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); */
    }

    public void login(View view){
        userLogin();
    }
}
