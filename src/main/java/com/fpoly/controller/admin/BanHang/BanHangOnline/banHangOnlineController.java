package com.fpoly.controller.admin.BanHang.BanHangOnline;

import com.fpoly.service.TraHangService;
import com.fpoly.service.banHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class banHangOnlineController {
    @Autowired
    banHangService banHangService;

    @Autowired
    TraHangService traHangService;

    //erro_validate
    @PostMapping("/customer/gio-hang-chi-tiet/tao-hoa-don")
    public String taoHoaDon(@RequestBody List<Long> selectedCartItemIds, RedirectAttributes redirectAttributes) {
        System.out.println("/customer/gio-hang-chi-tiet/tao-hoa-don");
        if (banHangService.validateTaoHoaDonBanHangOnline(selectedCartItemIds)) {
            return "redirect:/customer/checkout/erro_validate";
        }
        long hoaDonID = banHangService.taoHoaDonBanHangOnline(selectedCartItemIds, redirectAttributes);
        return "redirect:/customer/checkout/" + hoaDonID;
    }



    @RequestMapping("customer/checkout/{id}")
    public String banHangBanHangOnline(@PathVariable("id") Long id, Model model) {
        System.out.println("customer/checkout/{id}");
        banHangService.BanHangBanHangOnline(id, model);
        return "admin/banHang/banHangOnline/DatHang";
    }

    @PostMapping("/customer/TraHang/createOrUpdate/tao-hoa-don")
    public String taoTraDon(@RequestBody List<Long> selectedCartItemIds, RedirectAttributes redirectAttributes) {
        System.out.println("/customer/TraHang/createOrUpdate/tao-hoa-don");
        long hoaDonID = traHangService.taoTraDonBanHangOnline(selectedCartItemIds, redirectAttributes);
        return "redirect:/customer/checkout/" + hoaDonID;
    }

}
