package com.example.toan.applazada.Model.ObjectClass;

import java.util.List;

public class SanPham {
    int maSP, gia, soLuong, maLoaiSP, maThuongHieu, maNV, luotMua;
    String anhLon;
    String anhNho;
    String thongTin;
    String tenSanPham;
    String tenNhanVien;
    List<ChiTietSanPham> chiTietSanPhamList;
    byte[] hinhgiohang;

    public byte[] getHinhgiohang() {
        return hinhgiohang;
    }

    public void setHinhgiohang(byte[] hinhgiohang) {
        this.hinhgiohang = hinhgiohang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public int getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getLuotMua() {
        return luotMua;
    }

    public void setLuotMua(int luotMua) {
        this.luotMua = luotMua;
    }

    public String getAnhLon() {
        return anhLon;
    }

    public void setAnhLon(String anhLon) {
        this.anhLon = anhLon;
    }

    public String getAnhNho() {
        return anhNho;
    }

    public void setAnhNho(String anhNho) {
        this.anhNho = anhNho;
    }

    public String getThongTin() {
        return thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }
}
