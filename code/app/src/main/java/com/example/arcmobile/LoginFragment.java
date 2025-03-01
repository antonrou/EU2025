package com.example.arcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class LoginFragment extends Fragment {

    private EditText editTextEmail, editTextPassword;
    private Button loginButton, signupButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI elements
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        loginButton = view.findViewById(R.id.loginButton);
        signupButton = view.findViewById(R.id.signupButton);

        // Set click listener for login button
        loginButton.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter both email and password", Toast.LENGTH_SHORT).show();
            } else {
                // Perform login operation (placeholder)
                Toast.makeText(getActivity(), "Login successful!", Toast.LENGTH_SHORT).show();
                // Navigate to ProfileActivity
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });


        // Set click listener for sign-up button
        signupButton.setOnClickListener(v -> {
            // Navigate to sign-up fragment or activity (placeholder)
            Toast.makeText(getActivity(), "Redirecting to sign-up page...", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_LoginFragment_to_SignupFragment);
        });

    }
}
