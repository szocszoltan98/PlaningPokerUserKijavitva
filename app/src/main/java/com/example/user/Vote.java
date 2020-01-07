package com.example.user;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Vote extends Fragment {
    private Button SendBtn;
    private boolean check;
    private TextView questionTV;
    private boolean exists;
    private CardView a,b,c,d,e,f,g,h,i,lastchecked;
    private String groupCodeS,uNameS,question,answer,groupCode;
    private GridLayout cards;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference questionsReference = database.getReference().child("Questions");
    private static DatabaseReference answerReference = database.getReference().child("Answers");
    private TextView getQuestionTextView;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vote, container, false);
        groupCodeS=getArguments().getString("groupCode");
        uNameS=getArguments().getString("name");
        answer=getArguments().getString("answrer");
        groupCode=getArguments().getString("groupCode");

        questionTV=v.findViewById(R.id.Question);
        //getQuestion();
        a=v.findViewById(R.id.one);
        b=v.findViewById(R.id.two);
        c=v.findViewById(R.id.tree);
        d=v.findViewById(R.id.four);
        e=v.findViewById(R.id.ten);
        f=v.findViewById(R.id.fifty);
        g=v.findViewById(R.id.oneh);
        h=v.findViewById(R.id.onet);
        i=v.findViewById(R.id.nulla);
        check=false;
        return v;
    }

    private void OnClickListeners()
    {
        SendBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                exists=false;
                answerReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot item: dataSnapshot.getChildren()) {
                            if(item.child("name").getValue().toString().equals(uNameS) && item.child("question").getValue().toString().equals(question) && item.child("groupId").getValue().toString().equals(groupCodeS)) {
                                exists=true;
                            }


                        }
                        /*if(!exists) {
                            AnswerItem my = new AnswerItem(uNameS, answer, groupCode, question);
                            String newKey = answerReference.push().getKey();
                            if (newKey != null) {
                                answerReference.child(newKey).setValue(my);
                                questionTV.setText("Your answer was submited succesfully");
                                cards.setVisibility(View.GONE);
                                SendBtn.setVisibility(View.GONE);
                                llProgressBar.setVisibility(View.GONE);
                                showAnswers.setVisibility(View.VISIBLE);
                            }
                        }
                        else {
                            Toast.makeText(getContext(),"Mar volt valaszolva", Toast.LENGTH_SHORT).show();

                        }*/
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            }
        });


    }
    private void  getQuestion() {
        questionsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item: dataSnapshot.getChildren()){
                    String txt = item.child("groupId").getValue().toString();

                    if (txt.equals(groupCodeS)) {
                        if (item.child("active").getValue().toString().equals("true")) {
                            question = item.child("question").getValue().toString();

                        }
                    }


                }
                questionTV.setText(question);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}
}
