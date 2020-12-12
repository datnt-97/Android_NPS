package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;


@OverrideTable(name = constant.TABLE_STOCK_TABLE_NAME)
@Entity(tableName = constant.TABLE_STOCK_TABLE_NAME)
public class StockModel extends BaseModel {

    @ColumnInfo
    private String ten_kho;
    @ColumnInfo
    private String dia_chi;
    @ColumnInfo
    private int tinh_thanh_id;
    @ColumnInfo
    private int nguoi_quan_ly;
    @ColumnInfo
    private int don_vi_id;
    @ColumnInfo
    private int loai_kho;


    public String getTen_kho() {
        return ten_kho;
    }

    public void setTen_kho(String ten_kho) {
        this.ten_kho = ten_kho;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public int getTinh_thanh_id() {
        return tinh_thanh_id;
    }

    public void setTinh_thanh_id(int tinh_thanh_id) {
        this.tinh_thanh_id = tinh_thanh_id;
    }

    public int getNguoi_quan_ly() {
        return nguoi_quan_ly;
    }

    public void setNguoi_quan_ly(int nguoi_quan_ly) {
        this.nguoi_quan_ly = nguoi_quan_ly;
    }

    public int getDon_vi_id() {
        return don_vi_id;
    }

    public void setDon_vi_id(int don_vi_id) {
        this.don_vi_id = don_vi_id;
    }

    public int getLoai_kho() {
        return loai_kho;
    }

    public void setLoai_kho(int loai_kho) {
        this.loai_kho = loai_kho;
    }

    @Override
    public String getString() {
        return null;
    }
}
