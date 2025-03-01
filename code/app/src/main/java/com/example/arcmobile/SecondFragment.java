package com.example.arcmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.arcmobile.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.doneBalanceButton.setOnClickListener(v -> {
            // Get balance text and convert to integer
            String AddedAmntTxt = binding.amntAdd.getText().toString().trim();
            int addedAmnt = 0;
            addedAmnt = Integer.parseInt(AddedAmntTxt);

            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        binding.cancelButton.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}