package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;


@OverrideTable(name = constant.TABLE_REQUEST_TYPE_TABLE_NAME)
@Entity(tableName = constant.TABLE_REQUEST_TYPE_TABLE_NAME)
public class RequestTypeModel extends BaseModel {
    @ColumnInfo
    String loai_yeu_cau_id;
    @ColumnInfo
    String ten_loai_yeu_cau;
    @ColumnInfo
    String he_so;
    @ColumnInfo
    String ghi_chu;
    @ColumnInfo
    String trang_thai;
    @ColumnInfo
    String error_group_id_array;
    @ColumnInfo
    String loai_hien_thi;

    public String getLoai_yeu_cau_id() {
        return loai_yeu_cau_id;
    }

    public void setLoai_yeu_cau_id(String loai_yeu_cau_id) {
        this.loai_yeu_cau_id = loai_yeu_cau_id;
    }

    public String getTen_loai_yeu_cau() {
        return ten_loai_yeu_cau;
    }

    public void setTen_loai_yeu_cau(String ten_loai_yeu_cau) {
        this.ten_loai_yeu_cau = ten_loai_yeu_cau;
    }

    public String getHe_so() {
        return he_so;
    }

    public void setHe_so(String he_so) {
        this.he_so = he_so;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getError_group_id_array() {
        return error_group_id_array;
    }

    public void setError_group_id_array(String error_group_id_array) {
        this.error_group_id_array = error_group_id_array;
    }

    public String getLoai_hien_thi() {
        return loai_hien_thi;
    }

    public void setLoai_hien_thi(String loai_hien_thi) {
        this.loai_hien_thi = loai_hien_thi;
    }

    @Override
    public String getString() {
        return null;
    }
}
