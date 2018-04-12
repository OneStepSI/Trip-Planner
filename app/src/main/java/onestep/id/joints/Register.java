package onestep.id.joints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText name,user,pass,hp;
    Button regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        hp = (EditText) findViewById(R.id.hp);
        regis = (Button) findViewById(R.id.buttonRegis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });
    }
}
