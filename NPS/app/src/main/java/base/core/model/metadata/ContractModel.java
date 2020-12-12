package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.Date;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_CONTRACT_TABLE_NAME)
@Entity(tableName = constant.TABLE_CONTRACT_TABLE_NAME)
public class ContractModel extends BaseModel {

    @ColumnInfo
    private String so_hop_dong;
    @ColumnInfo
    private String tu_ngay;
    @ColumnInfo
    private String den_ngay;
    @ColumnInfo
    private int loai_hop_dong_id;
    @ColumnInfo
    private int ngan_hang_id;
    @ColumnInfo
    private String ten_hop_dong;
    @ColumnInfo
    private String ghi_chu;
    @ColumnInfo
    private int trang_thai;
    @ColumnInfo
    private int modified_by;
    @ColumnInfo
    private String modified_date;
    @ColumnInfo
    private int approval_by;

    public String getSo_hop_dong() {
        return so_hop_dong;
    }

    public void setSo_hop_dong(String so_hop_dong) {
        this.so_hop_dong = so_hop_dong;
    }

    public String getTu_ngay() {
        return tu_ngay;
    }

    public void setTu_ngay(String tu_ngay) {
        this.tu_ngay = tu_ngay;
    }

    public String getDen_ngay() {
        return den_ngay;
    }

    public void setDen_ngay(String den_ngay) {
        this.den_ngay = den_ngay;
    }

    public int getLoai_hop_dong_id() {
        return loai_hop_dong_id;
    }

    public void setLoai_hop_dong_id(int loai_hop_dong_id) {
        this.loai_hop_dong_id = loai_hop_dong_id;
    }

    public int getNgan_hang_id() {
        return ngan_hang_id;
    }

    public void setNgan_hang_id(int ngan_hang_id) {
        this.ngan_hang_id = ngan_hang_id;
    }

    public String getTen_hop_dong() {
        return ten_hop_dong;
    }

    public void setTen_hop_dong(String ten_hop_dong) {
        this.ten_hop_dong = ten_hop_dong;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public int getApproval_by() {
        return approval_by;
    }

    public void setApproval_by(int approval_by) {
        this.approval_by = approval_by;
    }

    @Override
    public String getString() {
        return null;
    }
}
