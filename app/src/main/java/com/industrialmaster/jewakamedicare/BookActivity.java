package com.industrialmaster.jewakamedicare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.industrialmaster.jewakamedicare.actions.BookingSaveTask;

public class BookActivity extends AppCompatActivity {
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        id = data.getInt("id");


    }

    public void save(View view) {
        EditText etName = findViewById(R.id.etName);
        EditText etContact = findViewById(R.id.etContact);
        EditText etEmail = findViewById(R.id.etEmail);

        String name = etName.getText().toString();
        String contact = etContact.getText().toString();
        String email = etEmail.getText().toString();

        BookingSaveTask book = new BookingSaveTask(this);
        book.execute("" + id, name, contact, email);
    }
}