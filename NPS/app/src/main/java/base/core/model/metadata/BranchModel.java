package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_BRANCH_TABLE_NAME)
@Entity(tableName = constant.TABLE_BRANCH_TABLE_NAME)
public class BranchModel extends BaseModel {
    @ColumnInfo
    private String ma_dv;
    @ColumnInfo
    private String diachi_dv;
    @ColumnInfo
    private String ghichu;
    @ColumnInfo
    private int donvi_id;
    @ColumnInfo
    private String ten_dv;
    @ColumnInfo
    private String dienthoai;
    @ColumnInfo
    private int loai_dv_id;
    @ColumnInfo
    private int tinh_thanh_id;

    public String getMa_dv() {
        return ma_dv;
    }

    public void setMa_dv(String ma_dv) {
        this.ma_dv = ma_dv;
    }

    public String getDiachi_dv() {
        return diachi_dv;
    }

    public void setDiachi_dv(String diachi_dv) {
        this.diachi_dv = diachi_dv;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getDonvi_id() {
        return donvi_id;
    }

    public void setDonvi_id(int donvi_id) {
        this.donvi_id = donvi_id;
    }

    public String getTen_dv() {
        return ten_dv;
    }

    public void setTen_dv(String ten_dv) {
        this.ten_dv = ten_dv;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public int getLoai_dv_id() {
        return loai_dv_id;
    }

    public void setLoai_dv_id(int loai_dv_id) {
        this.loai_dv_id = loai_dv_id;
    }

    public int getTinh_thanh_id() {
        return tinh_thanh_id;
    }

    public void setTinh_thanh_id(int tinh_thanh_id) {
        this.tinh_thanh_id = tinh_thanh_id;
    }

    @Override
    public String getString() {
        return null;
    }
}
