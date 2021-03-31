package de.fhe.ai.pme.swipe.view.ui.home.configuration.card_view;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;

public class CardViewFragment extends BaseFragment {

    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.root = inflater.inflate(R.layout.fragment_card, container, false);


        ConstraintLayout innerLayout = root.findViewById(R.id.inner_layout);
        innerLayout.setOnClickListener(this.innerLayoutListener);

        ImageView ratingGood = root.findViewById(R.id.rating_good);
        ratingGood.setOnClickListener(this.ratingGoodListener);

        ImageView ratingBad = root.findViewById(R.id.rating_bad);
        ratingBad.setOnClickListener(this.ratingBadListener);


        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        //Log.i(Constants.APP_TAG, "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                innerLayout.animate().translationX(-1000f);
                                //Log.i(Constants.APP_TAG, "Right to Left");
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                innerLayout.animate().translationX(1000f);
                                //Log.i(Constants.APP_TAG, "Left to Right");
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
        return root;

    }


    boolean currentPageIsFront = true;


    private final View.OnClickListener innerLayoutListener= v -> {

        ImageView card =  root.findViewById(R.id.imageView);
        TextView text = root.findViewById(R.id.textView4);
        ConstraintLayout innerLayout = root.findViewById(R.id.inner_layout);


        innerLayout.animate().rotationYBy(180f).start();
        if(currentPageIsFront){
            card.setImageResource(R.drawable.ic_folder);
            text.setText("FrontPage");
            innerLayout.animate().rotationYBy(360f).start();
            currentPageIsFront = false;

        } else {
            card.setImageResource(R.drawable.ic_add_file);
            text.setText("BackPage");
            innerLayout.animate().rotationYBy(360f).start();
            currentPageIsFront = true;
        }
    };

    private final View.OnClickListener ratingGoodListener= v -> {
        ConstraintLayout innerLayout = root.findViewById(R.id.inner_layout);
        innerLayout.animate().translationX(1000f);
    };

    private final View.OnClickListener ratingBadListener= v -> {
    ConstraintLayout innerLayout = root.findViewById(R.id.inner_layout);
        innerLayout.animate().translationX(-1000f);
    };
}

