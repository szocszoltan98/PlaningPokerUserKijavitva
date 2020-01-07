package com.example.user;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends Fragment {
    public Button goToTheGroupBtn;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference db = database.getReference().child("Groups");
    private EditText gcode,name;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        goToTheGroupBtn=v.findViewById(R.id.LoginButton);
        gcode=v.findViewById(R.id.edt_groupCode);
        name=v.findViewById(R.id.Name);
        //connect to group
        goToTheGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()==true){
                final String codeS=gcode.getText().toString();
                final String nameS=name.getText().toString();
                db= FirebaseDatabase.getInstance().getReference("groups");
                db.child(codeS).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if(dataSnapshot.exists()==false)
                        {
                            Toast.makeText(getContext() , "Group is not exist!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {

                            FragmentTransaction fr=getFragmentManager().beginTransaction();
                            Fragment f=new Vote();
                            fr.addToBackStack(null);
                            fr.replace(R.id.fragment_container,f);
                            Bundle args=new Bundle();
                            args.putString("groupCode",codeS);
                            args.putString("name",nameS);
                            f.setArguments(args);
                            fr.commit();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }}
        });
        return v;
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    boolean checkDataEntered() {
        boolean isTrue = true;
        if (isEmpty(gcode)) {
            gcode.setError("Name is required!");
            isTrue = false;
        }

        if (isEmpty(name)) {
            name.setError("Group name is required!");
            isTrue = false;
        }
        return isTrue;
    }

}
