package com.project.Shop.dto.Bill;

import com.project.Shop.entity.enumClass.BillStatus;
import com.project.Shop.entity.enumClass.InvoiceType;

import java.time.LocalDateTime;
import java.util.Date;

public interface BillDtoInterface {
    Long getMaHoaDon();
    String getMaDinhDanh();
    String getHoVaTen();
    String getSoDienThoai();
    LocalDateTime getNgayTao();
    Double getTongTien();
    BillStatus getTrangThai();
    InvoiceType getLoaiDon();
    String getHinhThucThanhToan();

    String getMaGiaoDich();
    String getMaDoiTra();
}
