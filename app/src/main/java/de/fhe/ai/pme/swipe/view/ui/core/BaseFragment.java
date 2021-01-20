package de.fhe.ai.pme.swipe.view.ui.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import de.fhe.ai.pme.swipe.R;

/*
    Common super class for all our Fragments. Offers some regularly needed
    helper methods.
 */
public class BaseFragment extends Fragment {

    /*
        Getting a ViewModel as well as a specific AndroidViewModel that has
        access to the context requires some effort. This method hides all the
        complex stuff.
     */
    protected <T extends ViewModel> T getViewModel(Class<T> tClass) {
        return new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(
                        requireActivity().getApplication()
                )).get(tClass);
    }

    /*
        Helper method to hide the keyboard, for example when submitting a form.
     */
    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}



