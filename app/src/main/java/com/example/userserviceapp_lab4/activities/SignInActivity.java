package com.example.userserviceapp_lab4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.userserviceapp_lab4.R;
import com.example.userserviceapp_lab4.databinding.ActivitySignInBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_sign_in);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //    return insets;
        //});

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
            binding.textCreateNewAccount.setOnClickListener(v ->
                    startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));

            binding.buttonSignIn.setOnClickListener(v -> addDataToFireStore());
    }

    private void addDataToFireStore() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String,String> data = new HashMap<>();

        data.put("first_name", "John");
        data.put("last_name", "Doe");

        database.collection("Users").add(data).addOnSuccessListener(
                documentReference -> {
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(exception -> {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}