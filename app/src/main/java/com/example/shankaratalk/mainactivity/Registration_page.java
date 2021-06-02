package com.example.shankaratalk.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shankaratalk.MainActivity;
import com.example.shankaratalk.R;
import com.example.shankaratalk.fragment.Home;
import com.example.shankaratalk.modelclass.Create_user;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_page extends AppCompatActivity {
    private TextInputEditText name;
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText confirmpassword;
    private TextInputEditText uid;
    private RadioGroup radioGroup;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Spinner spinner;
    private Button button;
    private String gendar;
    private String Bio = "";
    Create_user create_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        uid = findViewById(R.id.uid);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
        R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = null;
                String Followers = "0";
                String Following = "0";
                String PostCount = "0";
                String identity = (String) spinner.getSelectedItem();
                String fname = name.getText().toString();
                String femail = email.getText().toString();
                String fpassword = password.getText().toString();
                String fuid = uid.getText().toString();
                String fconfirmpassword = confirmpassword.getText().toString();
                if (radioButton3.isChecked())
                {
                    gendar = "Male";
                }
                else
                {
                    gendar = "Female";
                }

                if (fname.isEmpty())
                {
                    name.setError("Please enter The name");
                }
                else  if (femail.isEmpty())
                {
                    email.setError("Please enter The email");
                }
                else  if (fpassword.isEmpty())
                {
                    password.setError("Please enter The passwword");
                }
                else  if (fconfirmpassword.isEmpty())
                {
                    confirmpassword.setError("Please enter The Password");
                }
                else if (fuid.isEmpty() | fuid.length() != 12)
                {
                    Toast.makeText(Registration_page.this, "Pls Enetr correct UID", Toast.LENGTH_SHORT).show();
                }
                else if (fpassword.equals(confirmpassword))
                {
                    Toast.makeText(Registration_page.this, "Password Don't match", Toast.LENGTH_SHORT).show();
                }
                else if (!(radioButton3.isChecked() | radioButton4.isChecked()))
                {
                    Toast.makeText(getApplicationContext(), "Plse Select The Gendar", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    create_user = new Create_user(fname,femail,fuid,gendar,identity,PostCount,link,Following,Followers,Bio);
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(femail,fconfirmpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseDatabase.getInstance().getReference("All_users").child(FirebaseAuth.getInstance().getUid()).setValue(create_user);
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Registration_page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });
    }

    public void goforlogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login_page.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(Registration_page.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }
}