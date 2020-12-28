package com.example.byteel3ela.ui.internet;

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

public class InternetViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public String web;

    public InternetViewModel() {

        mText = new MutableLiveData<>();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                web = Objects.requireNonNull(snapshot.child("pageInternet").getValue()).toString();
                mText.setValue(web);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public LiveData<String> getText() {
        return mText;
    }
}
