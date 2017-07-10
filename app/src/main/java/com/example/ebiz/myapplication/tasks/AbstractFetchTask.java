package com.example.ebiz.myapplication.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ebiz.myapplication.model.User;
import com.example.ebiz.myapplication.utils.HttpRequestUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ebiz on 10/07/2017.
 */
public abstract class AbstractFetchTask<R> extends AsyncTask<User, Void, R> {

    private ITaskCallback<R> taskCallback;

    public AbstractFetchTask(ITaskCallback<R> taskCallback) {
        this.taskCallback = taskCallback;
    }

    @Override
    protected R doInBackground(User... users) {
        Log.i(LoginTask.class.getSimpleName(), "Try to login to server...");
        HttpURLConnection connection = null;
        for (User user : users) {
            try {
                URL loginUrl = new URL(getUrl() + "/" + user.getUsername() + "/" + user.getPassword());
                connection = (HttpURLConnection) loginUrl.openConnection();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpRequestUtils.OK_STATUS) {
                    InputStream inputStream = connection.getInputStream();
                    String response = HttpRequestUtils.streamToString(inputStream);
                    Log.i(LoginTask.class.getSimpleName(), response);
                    return parseJsonResponse(response);
                }
                else {
                    InputStream inputStream = connection.getErrorStream();
                    String response = HttpRequestUtils.streamToString(inputStream);
                    Log.i(LoginTask.class.getSimpleName(), response);
                    return null;
                }
            } catch (java.io.IOException e) {
                Log.i(LoginTask.class.getSimpleName(), "Failure.");
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(R result) {
        taskCallback.callback(result);
    }

    protected abstract String getUrl();

    protected abstract R parseJsonResponse(String response);
}
