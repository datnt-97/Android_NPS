package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_PROVICE_TABLE_NAME)
@Entity(tableName = constant.TABLE_PROVICE_TABLE_NAME)
public class ProvinceModel extends BaseModel {

    @ColumnInfo
    private String ten_tinh_thanh;
    @ColumnInfo
    private int quan_ly_id;
    @ColumnInfo
    private int vung_dich_vu_id;
    @ColumnInfo
    private int khu_vuc_id;

    public String getTen_tinh_thanh() {
        return ten_tinh_thanh;
    }

    public void setTen_tinh_thanh(String ten_tinh_thanh) {
        this.ten_tinh_thanh = ten_tinh_thanh;
    }

    public int getQuan_ly_id() {
        return quan_ly_id;
    }

    public void setQuan_ly_id(int quan_ly_id) {
        this.quan_ly_id = quan_ly_id;
    }

    public int getVung_dich_vu_id() {
        return vung_dich_vu_id;
    }

    public void setVung_dich_vu_id(int vung_dich_vu_id) {
        this.vung_dich_vu_id = vung_dich_vu_id;
    }

    public int getKhu_vuc_id() {
        return khu_vuc_id;
    }

    public void setKhu_vuc_id(int khu_vuc_id) {
        this.khu_vuc_id = khu_vuc_id;
    }

    @Override
    public String getString() {
        return ten_tinh_thanh;
    }

    public static ProvinceModel getDefault() {
        ProvinceModel model = new ProvinceModel();
        model.setTen_tinh_thanh("Select province");
        model.setId(constant.DB_DEFAULT_ID);
        return model;
    }

    public static ProvinceModel getDefault(String defalut) {
        ProvinceModel model = new ProvinceModel();
        model.setTen_tinh_thanh(defalut);
        model.setId(constant.DB_DEFAULT_ID);
        return model;
    }
}
