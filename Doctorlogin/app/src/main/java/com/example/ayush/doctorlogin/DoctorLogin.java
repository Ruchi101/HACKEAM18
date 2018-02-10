package com.example.ayush.doctorlogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoctorLogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText docID,docPass;
    Button docLoginBtn;
    String id,pass;
    private final String TAG = "adsf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        mAuth = FirebaseAuth.getInstance();

        docID = findViewById(R.id.doc_id);
        docPass = findViewById(R.id.doc_pass);
        docLoginBtn = findViewById(R.id.doc_login_submit);

        docLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = docID.getText().toString();
                id = id+ "@hackeam.com";
                pass = docPass.getText().toString();

                signIn(id,pass);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            //startActivity(new Intent(this,DoctorDashboard.class));
            Toast.makeText(getApplicationContext(),"Already Exists", Toast.LENGTH_LONG);
        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Signed in", Toast.LENGTH_LONG);
                            docLoginBtn.setText("Signed in");
                            //startActivity(new Intent(DoctorLogin.this,DoctorDashboard.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(DoctorLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        // [END sign_in_with_email]
    }

}
