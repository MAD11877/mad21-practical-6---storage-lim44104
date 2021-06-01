package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    ArrayList<User> data;
    Context mContext;

    public CustomAdapter(ArrayList<User> input, Context mContext) {
        this.data = input;
        this.mContext = mContext;
    }

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.customrecyclerview,parent,false);

        return new CustomViewHolder(item);
    }

    public void onBindViewHolder (CustomViewHolder holder, int position){
        User user = data.get(position);
        holder.name.setText(user.name);
        holder.description.setText(user.description);

        if (user.name.endsWith("7")) {
            holder.bigImage.setImageResource(R.drawable.ic_launcher_round);
            holder.bigImage.setVisibility(View.VISIBLE);
        } else {
            holder.bigImage.setVisibility(View.GONE);
        }

        holder.userImage.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

            builder.setTitle("Profile")
                    .setMessage(user.name)
                    .setNegativeButton("Close",null)
                    .setPositiveButton("View", (dialog, id) -> {
                        Intent mainIntent = new Intent(mContext, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("user", user);
                        mainIntent.putExtras(bundle);
                        mContext.startActivity(mainIntent);
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    public int getItemCount(){
        return data.size();
    }

}
