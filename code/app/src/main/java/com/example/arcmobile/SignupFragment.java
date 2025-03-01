package com.example.arcmobile;

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

public class SignupFragment extends Fragment {

    private EditText editTextEmail, editTextPassword;
    private Button signupButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI elements
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        signupButton = view.findViewById(R.id.signupButton);

        // Set click listener for login button
        signupButton.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter both email and password", Toast.LENGTH_SHORT).show();
            } else {
                // Perform login operation (placeholder)
                Toast.makeText(getActivity(), "Signup successful!", Toast.LENGTH_SHORT).show();
            }

            NavHostFragment.findNavController(SignupFragment.this)
                    .navigate(R.id.action_SignupFragment_to_LoginFragment);


        });
    }
}
