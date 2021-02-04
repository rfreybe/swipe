package de.fhe.ai.pme.swipe.view.ui.settings;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.core.Constants;


public class SettingsFragment extends PreferenceFragmentCompat {

    private SettingsViewModel settingsViewModel;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        findPreference(Constants.PREF_DARK_MODE).setOnPreferenceChangeListener((preference, newValue) -> {
            AppCompatDelegate.setDefaultNightMode(
                    (Boolean)newValue ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );

            return true;
        });




}
}




