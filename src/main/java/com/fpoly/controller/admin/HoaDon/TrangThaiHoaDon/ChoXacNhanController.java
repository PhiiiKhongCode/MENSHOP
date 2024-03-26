package com.fpoly.controller.admin.HoaDon.TrangThaiHoaDon;

import com.fpoly.service.TrangThaiHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChoXacNhanController {
    @Autowired
    TrangThaiHoaDonService trangThaiHoaDonService;

    @RequestMapping("admin/DonHang/ChoXacNhanDonHang/danhSach")
    public String getHoaDonChoXacNhan(Model model,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        System.out.println("admin/DonHang/ChoXacNhanDonHang/danhSach");
        trangThaiHoaDonService.getHoaDonChoXacNhan(model, page, size);
        return "admin/hoadon/TrangThaiHoaDon/ChoXacNhan";
    }

    @RequestMapping("admin/DonHang/ChoXacNhanDonHang/timKiem/{duLieuTimKiem}")
    public String timKiemHoaDonChoXacNhanDonHang(Model model,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @PathVariable("duLieuTimKiem") String duLieuTimKiem) {
        System.out.println("admin/DonHang/ChoXacNhanDonHang/timKiem/{duLieuTimKiem}");
        trangThaiHoaDonService.timKiemHoaDonChoXacNhanDonHang(model, page, size, duLieuTimKiem);
        return "admin/hoadon/TrangThaiHoaDon/ChoXacNhan";
    }

    @RequestMapping("admin/DonHang/ChoXacNhanDonHang/Ngay/{duLieuTimKiem}")
    public String timKiemHoaDonTheoNgayChoXacNhan(Model model,
                                                  @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "5") int size,
                                                  @PathVariable("duLieuTimKiem") String duLieuTimKiemString) {
        System.out.println("admin/DonHang/ChoXacNhanDonHang/Ngay/{duLieuTimKiem}");
        trangThaiHoaDonService.timKiemHoaDonTheoNgayChoXacNhan(model, page, size, duLieuTimKiemString);
        return "admin/hoadon/TrangThaiHoaDon/ChoXacNhan";
    }
}
