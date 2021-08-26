/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocvien;

import java.util.Date;

/**
 *
 * @author HuynhThiKhanhLinh
 */
public class HocVienModel {
    private String MaHocVien;
    private String TenHocVien;
    private Date NgaySinh;
    private String GioiTinh;
    private Float DiemThi;

    public HocVienModel() {
    }

    public HocVienModel(String MaHocVien, String TenHocVien, Date NgaySinh, String GioiTinh, Float DiemThi) {
        this.MaHocVien = MaHocVien;
        this.TenHocVien = TenHocVien;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiemThi = DiemThi;
    }

    public String getMaHocVien() {
        return MaHocVien;
    }

    public String getTenHocVien() {
        return TenHocVien;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public Float getDiemThi() {
        return DiemThi;
    }

    public void setMaHocVien(String MaHocVien) {
        this.MaHocVien = MaHocVien;
    }

    public void setTenHocVien(String TenHocVien) {
        this.TenHocVien = TenHocVien;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setDiemThi(Float DiemThi) {
        this.DiemThi = DiemThi;
    }
}
