package com.example.byteel3ela.ui.demyamaHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.byteel3ela.R;
import com.squareup.picasso.Picasso;

public class DemyamaHomeFragment extends Fragment {
    private DemyamaHomeViewModel demyamaHomeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        demyamaHomeViewModel =
                new ViewModelProvider(this).get(DemyamaHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_demyamahome, container, false);
        final TextView textView = root.findViewById(R.id.text_demyamaHome);
        final ImageView imageView = root.findViewById(R.id.imageViewDemyama);

        demyamaHomeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        demyamaHomeViewModel.getUri().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Picasso.get()
                        .load(s)
                        .into(imageView);
            }
        });
        return root;
    }
}