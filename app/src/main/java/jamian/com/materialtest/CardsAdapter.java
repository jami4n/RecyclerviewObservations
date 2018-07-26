package jamian.com.materialtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MYVH>{

    ArrayList<MainActivity.CardModal> all_cards;
    CardListener cardListener;
    Context context;

    public CardsAdapter(ArrayList<MainActivity.CardModal> all_cards, CardListener cardListener, Context context) {
        this.all_cards = all_cards;
        this.cardListener = cardListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MYVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_card,parent,false);
        return new MYVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MYVH holder, int position) {
        holder.card_title.setText(all_cards.get(position).getText());
        ViewGroup.LayoutParams params = holder.card_parent.getLayoutParams();
        params.width = ((MainActivity) context).getWindowManager().getDefaultDisplay().getWidth() - 140;
        holder.card_parent.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return all_cards.size();
    }

    public class MYVH extends RecyclerView.ViewHolder {

        CardView card_parent;
        TextView card_title;

        public MYVH(View itemView) {
            super(itemView);

            card_parent = itemView.findViewById(R.id.card_parent);
            card_title = itemView.findViewById(R.id.tv_card_title);

        }
    }
}
