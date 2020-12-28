package com.example.byteel3ela.ui.byteEl3ela;

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

public class ByteEl3elaViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("pageByteEl3ela");
    public String a;

    public ByteEl3elaViewModel() {
        mText = new MutableLiveData<>();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                a = Objects.requireNonNull(snapshot.child("text").getValue()).toString();
                mText.setValue(a);
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