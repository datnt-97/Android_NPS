package base.ui.atm;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nps.R;

import base.ui.map.MapFragment;

public class AtmFragment extends Fragment {
    FrameLayout mapLayout;
    MapFragment mapFragment;

    public AtmFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_atm, container, false);
        mapLayout = v.findViewById(R.id.layoutMap);
        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mapLayout.setMinimumHeight((int) (metrics.heightPixels));
        mapLayout.setMinimumWidth((int) (metrics.widthPixels));
        mapFragment = new MapFragment();
        ft.add(mapLayout.getId(), mapFragment);
        ft.commit();
        return v;
    }
}
