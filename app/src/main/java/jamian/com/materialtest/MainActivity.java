package jamian.com.materialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CardListener{

    public static final String TAG = "12345";
    RecyclerView rv_cards;
    ArrayList<CardModal> all_cards;
    LinearLayoutManager linearLayoutManager;
    CardsAdapter cardsAdapter;

    ImageView iv_image;
    int prev_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_cards = findViewById(R.id.rv_all_cards);
        all_cards = new ArrayList<>();
        cardsAdapter = new CardsAdapter(all_cards,this,this);
        iv_image = findViewById(R.id.iv_image);

        SnapHelper snap = new LinearSnapHelper();

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv_cards.setLayoutManager(linearLayoutManager);
        rv_cards.setItemAnimator(new DefaultItemAnimator());
        rv_cards.setAdapter(cardsAdapter);
        rv_cards.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = ((LinearLayoutManager)rv_cards.getLayoutManager())
                            .findFirstCompletelyVisibleItemPosition();
                    onCardSwiped(position);
                }
            }
        });

        snap.attachToRecyclerView(rv_cards);
        setData();

        Glide.with(MainActivity.this).load(all_cards.get(0).getImg()).into(iv_image);
    }

    private void setData() {

        all_cards.add(new CardModal("Deer",R.drawable.deer));
        all_cards.add(new CardModal("Tiger",R.drawable.tiger));
        all_cards.add(new CardModal("Eagle",R.drawable.eagle));
        all_cards.add(new CardModal("Lion",R.drawable.lion));
        all_cards.add(new CardModal("Elephant",R.drawable.elephant));
        all_cards.add(new CardModal("Wolf",R.drawable.wolf));
        all_cards.add(new CardModal("Panda",R.drawable.panda));

        cardsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCardSwiped(int position) {
        if(position < 0 || position == prev_position)return;
        prev_position = position;

        TransitionOptions transitionOptions = new DrawableTransitionOptions().crossFade(500);

        Glide.with(MainActivity.this).load(all_cards.get(position).getImg()).transition(transitionOptions).into(iv_image);
    }

    @Override
    public void onCardClicked(int position) {
    }

    public class CardModal{
        String text;
        int img;

        public CardModal(String text, int img) {
            this.text = text;
            this.img = img;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }
    }
}
