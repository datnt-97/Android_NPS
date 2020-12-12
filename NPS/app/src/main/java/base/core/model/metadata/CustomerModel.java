package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_CUSTOMER_TABLE_NAME)
@Entity(tableName = constant.TABLE_CUSTOMER_TABLE_NAME)
public class CustomerModel extends BaseModel {
    @ColumnInfo
    private String ten_day_du;
    @ColumnInfo
    private String ten_viet_tat;
    @ColumnInfo
    private String dia_chi;
    @ColumnInfo
    private String lien_he;

    public String getTen_day_du() {
        return ten_day_du;
    }

    public void setTen_day_du(String ten_day_du) {
        this.ten_day_du = ten_day_du;
    }

    public String getTen_viet_tat() {
        return ten_viet_tat;
    }

    public void setTen_viet_tat(String ten_viet_tat) {
        this.ten_viet_tat = ten_viet_tat;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getLien_he() {
        return lien_he;
    }

    public void setLien_he(String lien_he) {
        this.lien_he = lien_he;
    }

    @Override
    public String getString() {
        return null;
    }
}

