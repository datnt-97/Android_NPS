package base.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.nps.R;

import base.MainActivity;
import base.core.lib.util;
import base.core.mudules.services.MainService;

public class SettingFragment extends Fragment {
    private Button btnLoadMetadata;
    private Button btnShowLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        btnLoadMetadata = v.findViewById(R.id.btnLoadMetadata);
        btnShowLocation = v.findViewById(R.id.btnShowLocation);
        btnLoadMetadata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.appController.loadMetaData();
                } catch (Exception e) {
                    util.createAlert(getContext(), util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
                }
            }
        });
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.createAlert(getContext(), util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO,
                        MainService.getMapBase().getMapModel().getLat() + "--" +
                                MainService.getMapBase().getMapModel().getLng()
                        , null);
            }
        });
        return v;
    }
}
