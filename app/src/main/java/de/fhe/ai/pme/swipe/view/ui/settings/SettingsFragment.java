package de.fhe.ai.pme.swipe.view.ui.settings;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceFragmentCompat;

import de.fhe.ai.pme.swipe.R;
import de.fhe.ai.pme.swipe.core.Constants;
import de.fhe.ai.pme.swipe.model.Folder;


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

        // Wenn der OnClick an ist, crasht die app
        // On Click Listener Back Button
        //ImageView BackBtn = getActivity().findViewById(R.id.back_button);
        //BackBtn.setOnClickListener(this.backBtnListener);

    }

    // Redirect to Home Fragment
    private final View.OnClickListener backBtnListener= v -> {

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        navController.navigate(R.id.navigation_home);
    };
}




