package com.example.mtalh.hijinnks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Comments extends AppCompatActivity {

    public static List<Model_Comments> list_comments = new ArrayList<Model_Comments>();
    String profile_namefield_1, comment_datefield, comment_textfield;
    RecyclerView recyclerView;
    EditText comment_text;
    ImageView comment_button;
ImageView back_button_comments;
    private RecyclerAdapter_comment adapter;
    private LinearLayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           getWindow().setStatusBarColor(Color.parseColor("#41539b"));
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        comment_text = (EditText) findViewById(R.id.comment_edittext);
        comment_button = (ImageView) findViewById(R.id.comment_button);
        back_button_comments=(ImageView)findViewById(R.id.back_button_comments);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_comment_activity);
        adapter = new RecyclerAdapter_comment(list_comments, getApplicationContext());
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!comment_text.getText().toString().isEmpty()) {
                    list_comments.add(new Model_Comments("Leonard Perze", "31 Oct 2017 ", comment_text.getText().toString()));
                    InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                } else if (comment_text.getText().toString().isEmpty()) {
                    Toast.makeText(Comments.this, "Please Write Comment", Toast.LENGTH_SHORT).show();
                }
                recyclerView.smoothScrollToPosition(list_comments.size());
                comment_text.setText("");
             }

        });

        adapter.SetItemClcik(new RecyclerAdapter_comment.ItemClickInterface() {
            @Override
            public void itemclickmethod(int position) {
                Toast.makeText(Comments.this, "Hi: " + position, Toast.LENGTH_SHORT).show();

            }
        });
        back_button_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Comments.this,Home.class));
            }
        });
    }

    public void adddata(String profile_person_name12, String comment_date12, String comment_text12) {
        adapter.addnewdata(profile_person_name12, comment_date12, comment_text12);
        list_comments.add(new Model_Comments(profile_person_name12, comment_date12, comment_text12));

    }
}
