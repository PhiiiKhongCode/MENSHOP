package com.fpoly.controller.admin.HoaDon.TrangThaiHoaDon;

import com.fpoly.service.HoaDonService;
import com.fpoly.service.TrangThaiHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaGiaoHangController {
    @Autowired
    TrangThaiHoaDonService trangThaiHoaDonService;

    @RequestMapping("admin/DonHang/DaGiaoHang/danhSach")
    public String getHoaDonDaGiao(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        System.out.println("admin/DonHang/DaGiaoHang/danhSach");
        trangThaiHoaDonService.getHoaDonDaGiao(model, page, size);
        return "admin/hoadon/TrangThaiHoaDon/DaGiao";
    }

    @RequestMapping("admin/DonHang/DaGiaoHang/timKiem/{duLieuTimKiem}")
    public String timKiemHoaDonDaGiaoHang(Model model,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "5") int size,
                                          @PathVariable("duLieuTimKiem") String duLieuTimKiem) {
        System.out.println("admin/DonHang/DaGiaoHang/timKiem/{duLieuTimKiem}");
        trangThaiHoaDonService.timKiemHoaDonDaGiaoHang(model, page, size, duLieuTimKiem);
        return "admin/hoadon/TrangThaiHoaDon/DaGiao";
    }

    @RequestMapping("admin/DonHang/DaGiaoHang/Ngay/{duLieuTimKiem}")
    public String timKiemHoaDonTheoNgayDaGiaoHang(Model model,
                                                  @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "5") int size,
                                                  @PathVariable("duLieuTimKiem") String duLieuTimKiemString) {
        System.out.println("admin/DonHang/DaGiaoHang/Ngay/{duLieuTimKiem}");
        trangThaiHoaDonService.timKiemHoaDonTheoNgayDaGiaoHang(model, page, size, duLieuTimKiemString);
        return "admin/hoadon/TrangThaiHoaDon/DaGiao";
    }

    @Autowired
    HoaDonService hoaDonService;
    // Duyệt đơn hoàn trả của khách hàng
    @RequestMapping("/xacNhanHoanTraHang/{id}")
    public ResponseEntity<String> xacNhanHoanTraHang(@PathVariable("id") Long hoaDonId){
        System.out.println("Duyệt đơn hoàn trả của khách hàng");
        return hoaDonService.xacNhanHoanTraHang(hoaDonId);
    }

    // Không xác nhận hoàn trả của khách hàng
    @RequestMapping("/xacNhanKhongDongYHoanTraHang/{id}")
    public ResponseEntity<String> xacNhanKhongDongYHoanTraHang(@PathVariable("id") Long hoaDonId){
        System.out.println("Không xác nhận hoàn trả của khách hàng");
        return hoaDonService.xacNhanKhongDongYHoanTraHang(hoaDonId);
    }
}
