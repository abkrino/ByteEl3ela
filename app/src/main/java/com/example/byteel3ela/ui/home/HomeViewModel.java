package com.example.byteel3ela.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> uriImg;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("pageHome");
    public String text;
    public String a;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        uriImg = new MutableLiveData<>();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                a = Objects.requireNonNull(snapshot.child("image").getValue()).toString();
                uriImg.setValue(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                text = Objects.requireNonNull(snapshot.child("text").getValue()).toString();
                mText.setValue(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getUri() {
        return uriImg;
    }
}