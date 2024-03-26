package com.fpoly.controller.admin.HoaDon.TrangThaiHoaDon;

import com.fpoly.service.TrangThaiHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaHoanTraController {
    @Autowired
    TrangThaiHoaDonService trangThaiHoaDonService;

    @RequestMapping("admin/DonHang/DaHoanHang/danhSach")
    public String getHoaDonDaHoanTra(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        System.out.println("admin/DonHang/DaHoanHang/danhSach");
        trangThaiHoaDonService.getHoaDonDaHoanTra(model, page, size);
        return "admin/hoadon/TrangThaiHoaDon/HoanTra";
    }

    @RequestMapping("admin/DonHang/DaHoanHang/timKiem/{duLieuTimKiem}")
    public String timKiemHoaDonDaHoanTra(Model model,
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @PathVariable("duLieuTimKiem") String duLieuTimKiem) {
        System.out.println("admin/DonHang/DaHoanHang/timKiem/{duLieuTimKiem}");
        trangThaiHoaDonService.timKiemHoaDonDaHoanTra(model, page, size, duLieuTimKiem);
        return "admin/hoadon/TrangThaiHoaDon/HoanTra";
    }

    @RequestMapping("admin/DonHang/DaHoanHang/Ngay/{duLieuTimKiem}")
    public String timKiemHoaDonTheoNgayDaHuy(Model model,
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "5") int size,
                                             @PathVariable("duLieuTimKiem") String duLieuTimKiemString) {
        System.out.println("admin/DonHang/DaHoanHang/Ngay/{duLieuTimKiem}");
        trangThaiHoaDonService.timKiemHoaDonTheoNgayHoanTra(model, page, size, duLieuTimKiemString);
        return "admin/hoadon/TrangThaiHoaDon/HoanTra";
    }
}
