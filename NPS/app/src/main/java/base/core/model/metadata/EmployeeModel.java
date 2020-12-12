package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_EMPLOYEE_TABLE_NAME)
@Entity(tableName = constant.TABLE_EMPLOYEE_TABLE_NAME)
public class EmployeeModel extends BaseModel {
    @ColumnInfo
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return name;
    }

    public static EmployeeModel getDefault() {
        EmployeeModel model = new EmployeeModel();
        model.setName("Select technical");
        model.setId(constant.DB_DEFAULT_ID);
        return model;
    }

    public static EmployeeModel getDefault(String dfault) {
        EmployeeModel model = new EmployeeModel();
        model.setName(dfault);
        model.setId(constant.DB_DEFAULT_ID);
        return model;
    }
}
