package de.fhe.ai.pme.swipe.view.ui.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.w3c.dom.Text;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;
import de.fhe.ai.pme.swipe.storage.KeyValueStore;

public class StatisticsFragment extends BaseFragment {



    private KeyValueStore keyValueStore;

    private StatisticsViewModel statisticsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        this.keyValueStore = new KeyValueStore(getActivity().getApplication());
        statisticsViewModel = this.getViewModel(StatisticsViewModel.class);

        int goodValue = keyValueStore.getValueInt("goodValue");
        int badValue = keyValueStore.getValueInt("badValue");

        TextView GoodValue = root.findViewById(R.id.GoodNumber);
        TextView BadValue = root.findViewById(R.id.BadNumber);

        GoodValue.setText(String.valueOf(goodValue));
        BadValue.setText(String.valueOf(badValue));

        // On Click Listener Back Button
        ImageView BackBtn = getActivity().findViewById(R.id.back_button);
        BackBtn.setOnClickListener(this.backBtnListener);

        return root;

    }

    // Redirect to Home Fragment
    private final View.OnClickListener backBtnListener= v -> {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_home);
    };
}
