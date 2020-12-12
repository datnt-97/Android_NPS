package base.ui.incident;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nps.R;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import base.MainActivity;
import base.core.api.AppController;
import base.core.lib.constant;
import base.core.lib.util;
import base.core.model.AtmModel;
import base.core.model.IncidentModel;
import base.core.model.metadata.EmployeeModel;
import base.core.model.metadata.ProvinceModel;
import base.core.model.result.ResultBase;
import base.core.model.result.ResultModel;
import base.ui.core.DateTimePicker;
import base.ui.core.MultiSelectionSpinner;
import base.ui.map.MapFragment;

public class IncidentFragment extends Fragment {
    private MultiSelectionSpinner<ProvinceModel> spinProvince;
    private MultiSelectionSpinner<EmployeeModel> spinEmployee;
    private TextView txtDateFrom;
    private TextView txtDateTo;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_incident, container, false);
        spinEmployee = v.findViewById(R.id.cbTechnicial);
        spinProvince = v.findViewById(R.id.cbProvince);
        txtDateFrom = v.findViewById(R.id.txtDateFrom);
        txtDateTo = v.findViewById(R.id.txtDateTo);

        context = this.getContext();
        setViewList();
        try {
            setViewDate();
        } catch (ParseException e) {
            util.createAlert(getContext(), util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
            return v;
        }

        MainActivity.appController.loadIncidentListWithPaging(0, this.getContext(), new util.FunctionCallback2<ResultModel, Boolean>() {
            @Override
            public Boolean apply(ResultModel resultModel) {
                IncidentModel incidentModel = new IncidentModel();
                ResultBase<IncidentModel> resultBase = new ResultBase<IncidentModel>(incidentModel);
                try {
                    List<IncidentModel> incidentModels = resultBase.ParseList(resultModel.getData(), PropertyNamingStrategy.SNAKE_CASE);
                } catch (IOException e) {
                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
                }
                return null;
            }
        });
        return v;
    }

    private void setDate(final TextView txt, long minDate, long maxDate) {
        DateTimePicker dateTimePickerFrom = new DateTimePicker(context);
        try {
            dateTimePickerFrom.setDatePickerDialog(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txt.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                }
            }, util.stringToDate(txt.getText().toString()));
        } catch (ParseException e) {
            util.createAlert(getContext(), util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
            return;
        }
        if (minDate > 0) {
            dateTimePickerFrom.getDatePickerDialog().getDatePicker().setMinDate(minDate);
        }
        if (maxDate > 0) {
            dateTimePickerFrom.getDatePickerDialog().getDatePicker().setMaxDate(maxDate);
        }
        dateTimePickerFrom.getDatePickerDialog().show();
    }

    private void setViewDate() throws ParseException {
        final Calendar cldr = Calendar.getInstance();
        int dayOfMonth = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        txtDateFrom.setText((dayOfMonth - 1) + "/" + (month + 1) + "/" + year);
        txtDateTo.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        final Date dateFrom;
        try {
            dateFrom = util.stringToDate((dayOfMonth) + "/" + (month + 1) + "/" + year);
        } catch (ParseException e) {
            throw e;
        }

        txtDateFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setDate(txtDateFrom, 0, 0);
                }
            }
        });
        txtDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(txtDateFrom, 0, 0);
            }
        });

        txtDateTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setDate(txtDateTo, 0, 0);
                }
            }
        });
        txtDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(txtDateTo, 0, 0);
            }
        });
    }

    private void setViewList() {
        //        Set view list
        ArrayList<ProvinceModel> provinceModels = new ArrayList();
        provinceModels.addAll(MainActivity.appDatabase.provinceDAO().findAll());

        ArrayList<EmployeeModel> employeeModels = new ArrayList();
        employeeModels.addAll(MainActivity.appDatabase.employeeDAO().findAll());

        Collections.sort(provinceModels, new Comparator<ProvinceModel>() {
            @Override
            public int compare(ProvinceModel o1, ProvinceModel o2) {
                return o1.getTen_tinh_thanh().compareTo(o2.getTen_tinh_thanh());
            }
        });
        provinceModels.add(0, ProvinceModel.getDefault("Select all province"));

        Collections.sort(employeeModels, new Comparator<EmployeeModel>() {
            @Override
            public int compare(EmployeeModel o1, EmployeeModel o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        employeeModels.add(0, EmployeeModel.getDefault("Select all employee"));

        spinProvince.setItems(provinceModels,true);
//        spinProvince.setSelection(new ArrayList(provinceModels.subList(0, 1)));
        spinEmployee.setItems(employeeModels,false);
//        spinEmployee.setSelection(new ArrayList(employeeModels.subList(0, 1)));
    }

}
