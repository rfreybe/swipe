package de.fhe.ai.pme.swipe.view.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;

public class StatisticsFragment extends BaseFragment {

    private StatisticsViewModel statisticsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        statisticsViewModel = this.getViewModel(StatisticsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

        return root;
    }
}
