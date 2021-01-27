package de.fhe.ai.pme.swipe.view.ui.settings;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.view.ui.core.BaseFragment;

public class SettingsFragment extends BaseFragment {

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        settingsViewModel = this.getViewModel(SettingsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        return root;
    }
}
