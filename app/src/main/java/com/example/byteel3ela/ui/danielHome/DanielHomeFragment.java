package com.example.byteel3ela.ui.danielHome;

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

public class DanielHomeFragment extends Fragment {
    private DanielHomeViewModel danielHomeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        danielHomeViewModel =
                new ViewModelProvider(this).get(DanielHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_danielhome, container, false);
        final TextView textView = root.findViewById(R.id.text_danielHome);
        final ImageView imageView = root.findViewById(R.id.imageViewDaniel);
        danielHomeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        danielHomeViewModel.getUri().observe(getViewLifecycleOwner(), new Observer<String>() {
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
