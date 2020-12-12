package base.ui.core;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.widget.AppCompatCheckedTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.core.lib.constant;
import base.core.lib.util;
import base.core.model.base.BaseModel;

public class MultiSelectionSpinner<Model extends BaseModel> extends androidx.appcompat.widget.AppCompatSpinner implements DialogInterface.OnMultiChoiceClickListener {
    ArrayList<Model> items = null;
    ArrayList<Model> itemsAll = null;
    boolean[] selection = null;
    boolean[] disabledItems = null;
    ArrayAdapter adapter;
    boolean isCheckAll = false;

    public MultiSelectionSpinner(Context context) {
        super(context);
        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item) {
            @Override
            public boolean isEnabled(int position) {
                if (items.get(position).getId() == constant.DB_DEFAULT_ID) {
                    return false;
                }
                return true;
            }
        };
        super.setAdapter(adapter);
    }

    public MultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item) {
            @Override
            public boolean isEnabled(int position) {
                if (items.get(position).getId() == constant.DB_DEFAULT_ID) {
                    return true;
                }
                return true;
            }
        };
        super.setAdapter(adapter);
    }

    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;
        for (int i = 0; i < items.size(); ++i) {
            if (selection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;
                sb.append(items.get(i).getString());
            }
        }
        return sb.toString();
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (selection != null && which < selection.length) {
            selection[which] = isChecked;
            adapter.clear();
            adapter.add(buildSelectedItemString());
        } else {
            throw new IllegalArgumentException(

                    "'idx' is out of bounds.");

        }
    }

    @Override

    public boolean performClick() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] itemNames = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getString();
        }
        builder.setMultiChoiceItems(itemNames, selection, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (which == 0) {
                    if (isChecked) {
                        ListView list = ((AlertDialog) dialog).getListView();
                        for (int i = 0; i < list.getCount(); i++) {
                            list.setItemChecked(i, true);
                        }
                    } else {
                        ListView list = ((AlertDialog) dialog).getListView();
                        for (int i = 0; i < list.getCount(); i++) {
                            list.setItemChecked(i, false);
                        }
                    }
                    // util.createAlert(getContext(), util.ALERT_STATUS.ALERT_STATUS_INFOR, util.ALERT_TYPE.ALERT_TYPE_INFO, "okj", null);
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface arg0, int arg1) {
            }

        });
        AlertDialog alertDialog = builder.create();
        ListView listView = alertDialog.getListView();

        alertDialog.getListView().setOnHierarchyChangeListener(
                new ViewGroup.OnHierarchyChangeListener() {

                    @Override
                    public void onChildViewAdded(View parent, View child) {
                        CharSequence text = ((AppCompatCheckedTextView) child).getText();
                        int i = Arrays.asList(itemNames).indexOf(text.toString());
                        if (!disabledItems[i]) {
                            child.setOnClickListener(null);
                        }
                    }

                    @Override
                    public void onChildViewRemoved(View parent, View child) {

                    }
                });
        alertDialog.show();
        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        super.setAdapter(adapter);
    }

    public void setItemsAll(ListView list) {
        for (int i = 0; i < list.getCount(); i++) {
            list.setItemChecked(i, true);
        }

    }

    public void setItems(ArrayList<Model> items, boolean isCheckAll) {
        this.isCheckAll = isCheckAll;
        this.items = items;
        itemsAll = items;
        disabledItems = new boolean[items.size()];
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == constant.DB_DEFAULT_ID)
                disabledItems[i] = true;
            else
                disabledItems[i] = true;
        }
        selection = new boolean[this.items.size()];
        adapter.clear();
        adapter.add("");
        Arrays.fill(selection, false);
    }

    public void setSelection(ArrayList selection) {

        for (int i = 0; i < this.selection.length; i++) {
            this.selection[i] = false;
        }
        for (int i = 0; i < selection.size(); i++) {
            Model sel = (Model) selection.get(i);
            for (int j = 0; j < items.size(); ++j) {
                if (items.get(j).getId() == (sel.getId())) {
                    this.selection[j] = true;
                }
            }
        }
        adapter.clear();
        adapter.add(buildSelectedItemString());
    }

    public ArrayList<Model> getSelectedItems() {
        ArrayList<Model> selectedItems = new ArrayList<>();
        for (int i = 0; i < items.size(); ++i) {

            if (selection[i]) {

                selectedItems.add(items.get(i));
            }
        }
        return selectedItems;

    }
}
