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
import android.widget.TextView;

import com.example.ebiz.myapplication.R;
import com.example.ebiz.myapplication.tasks.ITaskCallback;
import com.example.ebiz.myapplication.tasks.LoginTask;
import com.example.ebiz.myapplication.model.User;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    public static final String ERROR_KEY = "onError";

    private EditText usernameField;

    private EditText passwordField;

    private TextView errorView;

    private Button loginButton;

    @Override protected void onCreate(Bundle savedInstanceState) {
        Log.i(LoginActivity.class.getSimpleName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        loginButton = (Button) findViewById(R.id.login_button);

        errorView = (TextView) findViewById(R.id.login_error_text);
        errorView.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable username = usernameField.getText();
                Editable password = passwordField.getText();
                final User user = new User(username.toString(), password.toString());

                new LoginTask(new ITaskCallback<Boolean>() {
                    @Override
                    public void callback(Boolean canLogin) {
                        if (canLogin != null && canLogin) {
                            Log.i(LoginActivity.class.getSimpleName(), "Login success !");
                            errorView.setVisibility(View.INVISIBLE);

                            // Switch to Chat Activity
                            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                            intent.putExtra(User.USERNAME_FIELD, user.getUsername());
                            intent.putExtra(User.PASSWORD_FIELD, user.getPassword());
                            startActivity(intent);
                        } else {
                            Log.i(LoginActivity.class.getSimpleName(), "Login failed.");
                            errorView.setVisibility(View.VISIBLE);
                        }
                    }
                }).execute(user);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ERROR_KEY, errorView.getVisibility() == View.VISIBLE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        errorView.setVisibility(savedInstanceState.getBoolean(ERROR_KEY) ? View.VISIBLE : View.INVISIBLE);
    }
}
