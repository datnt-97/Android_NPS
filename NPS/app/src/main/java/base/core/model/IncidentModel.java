package base.core.model;

import androidx.sqlite.db.SimpleSQLiteQuery;

import java.util.List;

import base.MainActivity;
import base.core.model.base.BaseModel;
import base.core.model.metadata.ErrorModel;

public class IncidentModel extends BaseModel {
    private String ma_yeu_cau;
    private String mo_ta_loi_tom_tat;
    private String mo_ta_loi_chi_tiet;
    private String dia_chi;
    private String nguoi_lien_he;
    private String dien_thoai;
    private String thoi_gian_nhan_yeu_cau;
    private String thoi_gian_hoan_thanh_xu_ly;
    private String nhom_loi;
    private int nguoi_nhan_id;
    private String loi;
    private int nguoi_phan_cong_id;
    private int truong_nhom_id;
    private int atm_id;
    private int ngan_hang_id;
    private int don_vi_id;
    private int loai_yeu_cau_id;
    private int trang_thai_xu_ly_id;
    private int nguoi_xu_ly_id;
    private String thoi_gian_phan_cong;
    private String thoi_gian_ho_tro;
    private int loai_ho_tro_id;
    private int trang_thai;
    private int tinh_thanh_id;
    private double diem;
    private int hop_dong_id;
    private String hen_xu_ly;
    private String huong_xu_ly;
    private int do_uu_tien;
    private String ngay_tao;
    private int danh_gia;
    private String email;
    private int kenh_tiep_nhan_id;
    private int trang_thai_yeu_cau_id;
    private String ticket_code;
    private double he_so_goc;
    private double he_so_hoan_thanh;

    private String display_truong_nhom_id;
    private String display_nhom_loi;
    private String nguoi_nhan_display;
    private String nguoi_phan_cong_display;
    private List<ErrorModel> display_loi;
    private String display_ngan_hang_id;
    private String display_don_vi_id;
    private String display_loai_yeu_cau_id;
    private String display_trang_thai_xu_ly_id;
    private String display_nguoi_xu_ly_id;
    private String display_loai_ho_tro_id;
    private String display_trang_thai;
    private String display_tinh_thanh_id;
    private String display_hop_dong_id;
    private String display_kenh_tiep_nhan_id;
    private String display_trang_thai_yeu_cau_id;


    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public double getHe_so_goc() {
        return he_so_goc;
    }

    public void setHe_so_goc(double he_so_goc) {
        this.he_so_goc = he_so_goc;
    }

    public double getHe_so_hoan_thanh() {
        return he_so_hoan_thanh;
    }

    public void setHe_so_hoan_thanh(double he_so_hoan_thanh) {
        this.he_so_hoan_thanh = he_so_hoan_thanh;
    }

    public String getMa_yeu_cau() {
        return ma_yeu_cau;
    }


    public void setMa_yeu_cau(String ma_yeu_cau) {
        this.ma_yeu_cau = ma_yeu_cau;
    }

    public String getMo_ta_loi_tom_tat() {
        return mo_ta_loi_tom_tat;
    }

    public void setMo_ta_loi_tom_tat(String mo_ta_loi_tom_tat) {
        this.mo_ta_loi_tom_tat = mo_ta_loi_tom_tat;
    }

    public String getMo_ta_loi_chi_tiet() {
        return mo_ta_loi_chi_tiet;
    }

    public void setMo_ta_loi_chi_tiet(String mo_ta_loi_chi_tiet) {
        this.mo_ta_loi_chi_tiet = mo_ta_loi_chi_tiet;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getNguoi_lien_he() {
        return nguoi_lien_he;
    }

    public void setNguoi_lien_he(String nguoi_lien_he) {
        this.nguoi_lien_he = nguoi_lien_he;
    }

    public String getDien_thoai() {
        return dien_thoai;
    }

    public void setDien_thoai(String dien_thoai) {
        this.dien_thoai = dien_thoai;
    }

    public String getThoi_gian_nhan_yeu_cau() {
        return thoi_gian_nhan_yeu_cau;
    }

    public void setThoi_gian_nhan_yeu_cau(String thoi_gian_nhan_yeu_cau) {
        this.thoi_gian_nhan_yeu_cau = thoi_gian_nhan_yeu_cau;
    }

    public String getThoi_gian_hoan_thanh_xu_ly() {
        return thoi_gian_hoan_thanh_xu_ly;
    }

    public void setThoi_gian_hoan_thanh_xu_ly(String thoi_gian_hoan_thanh_xu_ly) {
        this.thoi_gian_hoan_thanh_xu_ly = thoi_gian_hoan_thanh_xu_ly;
    }

    public String getNhom_loi() {
        return nhom_loi;
    }

    public void setNhom_loi(String nhom_loi) {
        this.nhom_loi = nhom_loi;
    }

    public int getNguoi_nhan_id() {
        return nguoi_nhan_id;
    }

    public void setNguoi_nhan_id(int nguoi_nhan_id) {
        this.nguoi_nhan_id = nguoi_nhan_id;
    }

    public int getNguoi_phan_cong_id() {
        return nguoi_phan_cong_id;
    }

    public void setNguoi_phan_cong_id(int nguoi_phan_cong_id) {
        this.nguoi_phan_cong_id = nguoi_phan_cong_id;
    }

    public int getTruong_nhom_id() {
        return truong_nhom_id;
    }

    public void setTruong_nhom_id(int truong_nhom_id) {
        this.truong_nhom_id = truong_nhom_id;
    }

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
    }

    public int getAtm_id() {
        return atm_id;
    }

    public void setAtm_id(int atm_id) {
        this.atm_id = atm_id;
    }

    public int getNgan_hang_id() {
        return ngan_hang_id;
    }

    public void setNgan_hang_id(int ngan_hang_id) {
        this.ngan_hang_id = ngan_hang_id;
    }

    public int getDon_vi_id() {
        return don_vi_id;
    }

    public void setDon_vi_id(int don_vi_id) {
        this.don_vi_id = don_vi_id;
    }

    public int getLoai_yeu_cau_id() {
        return loai_yeu_cau_id;
    }

    public void setLoai_yeu_cau_id(int loai_yeu_cau_id) {
        this.loai_yeu_cau_id = loai_yeu_cau_id;
    }

    public int getTrang_thai_xu_ly_id() {
        return trang_thai_xu_ly_id;
    }

    public void setTrang_thai_xu_ly_id(int trang_thai_xu_ly_id) {
        this.trang_thai_xu_ly_id = trang_thai_xu_ly_id;
    }

    public int getNguoi_xu_ly_id() {
        return nguoi_xu_ly_id;
    }

    public void setNguoi_xu_ly_id(int nguoi_xu_ly_id) {
        this.nguoi_xu_ly_id = nguoi_xu_ly_id;
    }

    public String getThoi_gian_phan_cong() {
        return thoi_gian_phan_cong;
    }

    public void setThoi_gian_phan_cong(String thoi_gian_phan_cong) {
        this.thoi_gian_phan_cong = thoi_gian_phan_cong;
    }

    public String getThoi_gian_ho_tro() {
        return thoi_gian_ho_tro;
    }

    public void setThoi_gian_ho_tro(String thoi_gian_ho_tro) {
        this.thoi_gian_ho_tro = thoi_gian_ho_tro;
    }

    public int getLoai_ho_tro_id() {
        return loai_ho_tro_id;
    }

    public void setLoai_ho_tro_id(int loai_ho_tro_id) {
        this.loai_ho_tro_id = loai_ho_tro_id;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    public int getTinh_thanh_id() {
        return tinh_thanh_id;
    }

    public void setTinh_thanh_id(int tinh_thanh_id) {
        this.tinh_thanh_id = tinh_thanh_id;
    }


    public int getHop_dong_id() {
        return hop_dong_id;
    }

    public void setHop_dong_id(int hop_dong_id) {
        this.hop_dong_id = hop_dong_id;
    }

    public String getHen_xu_ly() {
        return hen_xu_ly;
    }

    public void setHen_xu_ly(String hen_xu_ly) {
        this.hen_xu_ly = hen_xu_ly;
    }

    public String getHuong_xu_ly() {
        return huong_xu_ly;
    }

    public void setHuong_xu_ly(String huong_xu_ly) {
        this.huong_xu_ly = huong_xu_ly;
    }

    public int getDo_uu_tien() {
        return do_uu_tien;
    }

    public void setDo_uu_tien(int do_uu_tien) {
        this.do_uu_tien = do_uu_tien;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public int getDanh_gia() {
        return danh_gia;
    }

    public void setDanh_gia(int danh_gia) {
        this.danh_gia = danh_gia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKenh_tiep_nhan_id() {
        return kenh_tiep_nhan_id;
    }

    public void setKenh_tiep_nhan_id(int kenh_tiep_nhan_id) {
        this.kenh_tiep_nhan_id = kenh_tiep_nhan_id;
    }

    public int getTrang_thai_yeu_cau_id() {
        return trang_thai_yeu_cau_id;
    }

    public void setTrang_thai_yeu_cau_id(int trang_thai_yeu_cau_id) {
        this.trang_thai_yeu_cau_id = trang_thai_yeu_cau_id;
    }

    public String getTicket_code() {
        return ticket_code;
    }

    public void setTicket_code(String ticket_code) {
        this.ticket_code = ticket_code;
    }


    public List<ErrorModel> getDisplay_loi() {
        if (MainActivity.appDatabase != null) {
            String className = MainActivity.appDatabase.errorDAO().getTableName();
            SimpleSQLiteQuery query = new SimpleSQLiteQuery("SELECT * FROM " + className + " WHERE id in (" + loi.split((",").toString()) + ")");
            return MainActivity.appDatabase.errorDAO().findAllWithQuery(query);
        }
        return display_loi;
    }

    public void setDisplay_loi(List<ErrorModel> display_loi) {
        this.display_loi = display_loi;
    }

    public String getDisplay_nhom_loi() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.errorGroupDAO().findById(Integer.valueOf(this.nhom_loi)).getGroup_error_name();
        }
        return display_nhom_loi;
    }

    public void setDisplay_nhom_loi(String display_nhom_loi) {
        this.display_nhom_loi = display_nhom_loi;
    }

    public String getNguoi_nhan_display() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.employeeDAO().findById(this.nguoi_nhan_id).getName();
        }
        return nguoi_nhan_display;
    }

    public void setNguoi_nhan_display(String nguoi_nhan_display) {
        this.nguoi_nhan_display = nguoi_nhan_display;
    }

    public String getNguoi_phan_cong_display() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.employeeDAO().findById(this.nguoi_phan_cong_id).getName();
        }
        return nguoi_phan_cong_display;
    }

    public void setNguoi_phan_cong_display(String nguoi_phan_cong_display) {
        this.nguoi_phan_cong_display = nguoi_phan_cong_display;
    }

    public String getDisplay_truong_nhom_id() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.employeeDAO().findById(this.truong_nhom_id).getName();
        }
        return display_truong_nhom_id;
    }

    public void setDisplay_truong_nhom_id(String display_truong_nhom_id) {
        this.display_truong_nhom_id = display_truong_nhom_id;
    }

    public String getDisplay_ngan_hang_id() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.customerDAO().findById(this.ngan_hang_id).getTen_viet_tat();
        }
        return display_ngan_hang_id;
    }

    public void setDisplay_ngan_hang_id(String display_ngan_hang_id) {
        this.display_ngan_hang_id = display_ngan_hang_id;
    }

    public String getDisplay_don_vi_id() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.branchDAO().findById(this.don_vi_id).getTen_dv();
        }
        return display_don_vi_id;
    }

    public void setDisplay_don_vi_id(String display_don_vi_id) {
        this.display_don_vi_id = display_don_vi_id;
    }

    public String getDisplay_loai_yeu_cau_id() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.requestTypeDAO().findById(this.loai_yeu_cau_id).getTen_loai_yeu_cau();
        }
        return display_loai_yeu_cau_id;
    }

    public void setDisplay_loai_yeu_cau_id(String display_loai_yeu_cau_id) {
        this.display_loai_yeu_cau_id = display_loai_yeu_cau_id;
    }

    public String getDisplay_trang_thai_xu_ly_id() {
        return display_trang_thai_xu_ly_id;
    }

    public void setDisplay_trang_thai_xu_ly_id(String display_trang_thai_xu_ly_id) {
        this.display_trang_thai_xu_ly_id = display_trang_thai_xu_ly_id;
    }

    public String getDisplay_nguoi_xu_ly_id() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.employeeDAO().findById(this.nguoi_xu_ly_id).getName();
        }
        return display_nguoi_xu_ly_id;
    }

    public void setDisplay_nguoi_xu_ly_id(String display_nguoi_xu_ly_id) {
        this.display_nguoi_xu_ly_id = display_nguoi_xu_ly_id;
    }

    public String getDisplay_loai_ho_tro_id() {
        return display_loai_ho_tro_id;
    }

    public void setDisplay_loai_ho_tro_id(String display_loai_ho_tro_id) {
        this.display_loai_ho_tro_id = display_loai_ho_tro_id;
    }

    public String getDisplay_trang_thai() {
        return display_trang_thai;
    }

    public void setDisplay_trang_thai(String display_trang_thai) {
        this.display_trang_thai = display_trang_thai;
    }

    public String getDisplay_tinh_thanh_id() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.provinceDAO().findById(this.tinh_thanh_id).getTen_tinh_thanh();
        }
        return display_tinh_thanh_id;
    }

    public void setDisplay_tinh_thanh_id(String display_tinh_thanh_id) {
        this.display_tinh_thanh_id = display_tinh_thanh_id;
    }

    public String getDisplay_hop_dong_id() {
        if (MainActivity.appDatabase != null) {
            return MainActivity.appDatabase.requestTypeDAO().findById(this.loai_yeu_cau_id).getTen_loai_yeu_cau();
        }
        return display_hop_dong_id;
    }

    public void setDisplay_hop_dong_id(String display_hop_dong_id) {
        this.display_hop_dong_id = display_hop_dong_id;
    }

    public String getDisplay_kenh_tiep_nhan_id() {
        return display_kenh_tiep_nhan_id;
    }

    public void setDisplay_kenh_tiep_nhan_id(String display_kenh_tiep_nhan_id) {
        this.display_kenh_tiep_nhan_id = display_kenh_tiep_nhan_id;
    }

    public String getDisplay_trang_thai_yeu_cau_id() {
        return display_trang_thai_yeu_cau_id;
    }

    public void setDisplay_trang_thai_yeu_cau_id(String display_trang_thai_yeu_cau_id) {
        this.display_trang_thai_yeu_cau_id = display_trang_thai_yeu_cau_id;
    }

    @Override
    public String getString() {
        return null;
    }
}
