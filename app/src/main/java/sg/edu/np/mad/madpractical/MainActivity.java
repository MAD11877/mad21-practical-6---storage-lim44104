package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import sg.edu.np.mad.madpractical.R;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Main Activity";
    private boolean initialFollowed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"Create");

        User user = new User(
                "Hello World!",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                0,
                false
        );

        TextView userName = findViewById(R.id.userName);    //define user name.
        userName.setText("MAD" + getIntent().getIntExtra("randomNum",0));

        TextView userDescription = findViewById(R.id.userDescription);  //define description
        userDescription.setText(user.getDescription());

        Button followButton = findViewById(R.id.btnFollow);  //define follow button

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            user = bundle.getParcelable("user");
            initialFollowed = user.isFollowed();
            userName.setText(user.name);
            userDescription.setText(user.description);
            followButton.setText(user.isFollowed() ? "Unfollow" : "Follow");
        }
        else {
            user = new User("hi", "hi", 1, false);
        }

        User finalUser = user;
        followButton.setOnClickListener(new View.OnClickListener() {    //button event listener
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Pressed!");

                if (finalUser.isFollowed()) {    //if false (haven't follow), set to false and set text to Follow
                    finalUser.setFollowed(false);
                    followButton.setText("Follow");
                    Log.v(TAG, "User is not Followed.");
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(
                            MainActivity.this,
                            "Unfollowed",
                            duration);
                    toast.show();
                }

                else {
                    finalUser.setFollowed(true); //if true (followed), set to true and set text to Followed
                    followButton.setText("Followed");
                    Log.v(TAG, "User is Followed.");
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(
                            MainActivity.this,
                            "Followed",
                            duration);
                    toast.show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG,"Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG,"Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"Destroy");
    }
}