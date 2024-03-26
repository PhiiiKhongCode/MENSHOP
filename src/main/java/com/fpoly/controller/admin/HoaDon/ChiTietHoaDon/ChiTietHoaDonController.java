package com.fpoly.controller.admin.HoaDon.ChiTietHoaDon;

import com.fpoly.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChiTietHoaDonController {
    @Autowired
    ChiTietHoaDonService chiTietHoaDonService;

    //CHỜ XÁC NHẬN
    @RequestMapping("ChiTietHoaDon/ChoXacNhan/hoa-don-id={id}")
    public String ChoXacNhan(@PathVariable("id") Long id, Model model) {
        System.out.println("ChiTietHoaDon/ChoXacNhan/hoa-don-id={id}");
        chiTietHoaDonService.choXacNhan(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTChoXacNhan";
    }

    //CHỜ GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/ChoGiaoHang/hoa-don-id={id}")
    public String ChoGiaoHang(@PathVariable("id") Long id, Model model) {
        System.out.println("ChiTietHoaDon/ChoGiaoHang/hoa-don-id={id}");
        chiTietHoaDonService.choGiaoHang(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTChoGiaohang";
    }

    //ĐANG GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/DangGiaoHang/hoa-don-id={id}")
    public String DangGiaoHang(@PathVariable("id") Long id, Model model) {
        System.out.println("ChiTietHoaDon/DangGiaoHang/hoa-don-id={id}");
        chiTietHoaDonService.dangGiaoHang(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDangGiaoHang";
    }

    //ĐÃ GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/DaGiaoHang/hoa-don-id={id}")
    public String DaGiaoHang(@PathVariable("id") Long id, Model model) {
        System.out.println("ChiTietHoaDon/DaGiaoHang/hoa-don-id={id}");
        chiTietHoaDonService.daGiaoHang(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDaGiao";
    }

    //ĐÃ HỦY
    @RequestMapping("ChiTietHoaDon/DaHuy/hoa-don-id={id}")
    public String DaHuy(@PathVariable("id") Long id, Model model) {
        System.out.println("ChiTietHoaDon/DaHuy/hoa-don-id={id}");
        chiTietHoaDonService.daHuy(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDaHuy";
    }
    // ĐÃ HOÀN ĐƠN
    @RequestMapping("ChiTietHoaDon/DaHoan/hoa-don-id={id}")
    public String DaHoan(@PathVariable("id") Long id, Model model){
        System.out.println("ChiTietHoaDon/DaHoan/hoa-don-id={id}");
        chiTietHoaDonService.daHoan(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDaHoan";
    }

    // Duyệt đơn hoàn trả của khách hàng
    @RequestMapping("ChiTietHoaDon/DuyetDonHoanTra/hoa-don-id={id}")
    public String DuyetDonHoanTra(@PathVariable("id") Long id, Model model){
        System.out.println("ChiTietHoaDon/DuyetDonHoanTra/hoa-don-id={id}");
        chiTietHoaDonService.duyetDonHoanTra(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDuyetDonHoanTra";
    }
}
