package com.example.ebiz.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ebiz.myapplication.R;
import com.example.ebiz.myapplication.tasks.LoginTask;
import com.example.ebiz.myapplication.tasks.User;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;

    private EditText passwordField;

    private Button loginButton;

    @Override protected void onCreate(Bundle savedInstanceState) {
        Log.i(LoginActivity.class.getSimpleName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setVisibility(View.INVISIBLE);

        usernameField.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing to do
            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                // nothing to do
            }

            @Override public void afterTextChanged(Editable s) {
                if (s.length() >= 3) {
                    loginButton.setVisibility(View.VISIBLE);
                }
                else {
                    loginButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable username = usernameField.getText();
                Editable password = passwordField.getText();
                User user = new User(username.toString(), password.toString());

                new LoginTask(new LoginTask.LoginCallback() {
                    @Override
                    public void onLogin(boolean canLogin) {
                        if (canLogin) {
                            Log.i(LoginActivity.class.getSimpleName(), "Login success !");
                            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                            startActivity(intent);
                        } else {
                            Log.i(LoginActivity.class.getSimpleName(), "Login failed.");
                        }
                    }
                }).execute(user);
            }
        });
    }
}
