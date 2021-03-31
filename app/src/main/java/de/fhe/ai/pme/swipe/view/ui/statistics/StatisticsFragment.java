package de.fhe.ai.pme.swipe.view.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;

public class StatisticsFragment extends BaseFragment {

    private StatisticsViewModel statisticsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        statisticsViewModel = this.getViewModel(StatisticsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

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
