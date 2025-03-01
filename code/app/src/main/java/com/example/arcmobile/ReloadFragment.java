package com.example.arcmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.arcmobile.databinding.FragmentReloadBinding;

public class ReloadFragment extends Fragment {

    private FragmentReloadBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentReloadBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cancelButton.setOnClickListener(v ->
                NavHostFragment.findNavController(ReloadFragment.this)
                        .navigate(R.id.action_ReloadFragment_to_BalanceFragment)
        );

        binding.doneBalanceButton.setOnClickListener(v -> {
            // Ensure input is valid
            String AddedAmntTxt = binding.amntAdd.getText().toString().trim();
            int addedAmnt = 0;

            try {
                if (!AddedAmntTxt.isEmpty()) { // Check if text is not empty
                    addedAmnt = Integer.parseInt(AddedAmntTxt);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Avoid crash
            }

            // Create a Bundle to pass data
            Bundle bundle = new Bundle();
            bundle.putInt("addedAmnt", addedAmnt);

            // Navigate with the Bundle
            NavHostFragment.findNavController(ReloadFragment.this)
                    .navigate(R.id.action_ReloadFragment_to_BalanceFragment, bundle);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}