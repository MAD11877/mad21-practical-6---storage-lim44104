package sg.edu.np.mad.madpractical;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "List Activity";
    static ArrayList<User> userList;
    DBHandler dbHandler;
    CustomAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        /*
        for (int i = 0; i < 20; ++i) {
            userList.add(new User(
                    "Name" + rng(), "Description " + rng(), i, rngFollow()
            ));
        }
        Log.v(TAG, "List Activity Created");

         */

        dbHandler = new DBHandler(this,null,null,1);
        userList = dbHandler.getUsers();

        RecyclerView recyclerView = findViewById(R.id.rv);
        CustomAdapter cAdapter = new CustomAdapter(userList, ListActivity.this);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(cLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);
    }

    private boolean rngFollow() {
        return new Random().nextInt(2) == 1;
    }

    private int rng() {
        return new Random().nextInt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Resume");

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        if (sharedPreferences.contains("user_id")) {
            int userId = sharedPreferences.getInt("user_id", 0);
            User user = userList.get(userId);
            userList.get(userId).setFollowed(!user.isFollowed());
        }
    }
}