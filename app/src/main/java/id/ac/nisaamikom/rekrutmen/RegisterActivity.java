package id.ac.nisaamikom.rekrutmen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button reg;
    private TextView tvLogin;
    private EditText etEmail, etPass, etHp;
    private DbHelper db;
    private RadioButton isAdmin;
    private RadioButton isPegawai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DbHelper(this);
        reg = (Button)findViewById(R.id.btnReg);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        etHp = (EditText) findViewById(R.id.etHp);
        isAdmin =(RadioButton)findViewById(R.id.radioAdmin);
        isPegawai =(RadioButton)findViewById(R.id.radioPegawai);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:

        }
    }

    private void register(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String hp = etHp.getText().toString();
        if(email.isEmpty() && pass.isEmpty() && hp.isEmpty()){
            displayToast("Username/password field empty");
        }
        else if(isAdmin.isChecked()){
            db.addSeller(email,pass, hp);
            displayToast("Admin registered");
            finish();
        }
        else if(isPegawai.isChecked()){
                db.addBuyer(email,pass,hp);
                displayToast("Pegawai registered");
                finish();
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
