package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView name, description;
    ImageView userImage, bigImage;
    public CustomViewHolder (View itemView){
        super(itemView);
        this.userImage = itemView.findViewById(R.id.img_profile);
        this.bigImage = itemView.findViewById(R.id.img_profile_big);
        this.name = itemView.findViewById(R.id.name);
        this.description = itemView.findViewById(R.id.description);
    }
}
