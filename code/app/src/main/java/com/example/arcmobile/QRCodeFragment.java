package com.example.arcmobile;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.arcmobile.databinding.FragmentQrcodeBinding;

public class QRCodeFragment extends Fragment {

    private CountDownTimer countDownTimer;
    private int totalTime = 90;  // Set the initial time (in seconds)


    private FragmentQrcodeBinding binding;

        @Override
        public View onCreateView(
                @NonNull LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
        ) {

            binding = FragmentQrcodeBinding.inflate(inflater, container, false);
            return binding.getRoot();

        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            TextView totalTimeTxt = binding.timeTxt;
            int totalTime = 5400; //90 mins

            totalTimeTxt.setText(String.valueOf(totalTime));

            countDownTimer = new CountDownTimer(totalTime * 1000, 1000) {  // Count down in seconds
                @Override
                public void onTick(long millisUntilFinished) {
                    // Update the TextView with the remaining time
                    totalTimeTxt.setText(String.valueOf(millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    // Show a message when time is up
                    totalTimeTxt.setText("Time's up!");
                }

            };
            countDownTimer.start();
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            binding = null;
        }

    }
