package com.fpoly.controller.customer.HoaDon;

import com.fpoly.entity.HinhAnhTraHang;
import com.fpoly.service.HinhAnhTraHangService;
import com.fpoly.service.HoaDonCustomerService;
import com.fpoly.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.UUID;

@Controller
public class DaGiaoCustomerController {
    @Autowired
    HoaDonCustomerService hoaDonCustomerService;

    @Autowired
    HinhAnhTraHangService hinhAnhTraHangService;

    @Autowired
    private StorageService storageService;

    @RequestMapping("customer/DonHang/DaGiaoHang")
    public String DaGiaoCustomer(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        hoaDonCustomerService.danhSachDaGiaoCustomer(page, size, model);
        return "customer/HoaDon/DanhSach/daGiaoCustomer";
    }

    @RequestMapping("customer/DonHang/ChiTietHoaDon/DaGiaoHang/hoa-don-id={id}")
    public String DaGiaoHang(@PathVariable("id") Long id, Model model) {
        hoaDonCustomerService.chiTietDaGiaoCustomer(id, model);
        return "customer/HoaDon/ChiTietHoaDon/CTDaGiaoCustomer";
    }

    @RequestMapping("customer/DonHang/ChiTietHoaDon/DaHoanDon/hoa-don-id={id}")
    public String DaTraHang(@PathVariable("id") Long id, Model model) {
        hoaDonCustomerService.chiTietDaGiaoCustomer(id, model);
        return "customer/HoaDon/ChiTietHoaDon/CTDaHoanDonCustomer";
    }

    @RequestMapping("/files/upload")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("hoaDonId") Long id) {
        try {
            System.out.println("uploadFiles trả hàng hóa đơn: " + id);
            System.out.println("so lượng hình ảnh: "+files.length);
            Arrays.asList(files).stream().forEach(img -> {
                UUID uuid = UUID.randomUUID();
                String uuString = uuid.toString();
                HinhAnhTraHang hinhAnh = new HinhAnhTraHang();
                hinhAnh.setHoaDonId(id);
                hinhAnh.setName(storageService.getStoredFileName(img, uuString));
                // lưu vào ảnh vào local
                storageService.store(img, hinhAnh.getName());
                System.out.println("tên hình ảnh: "+hinhAnh.getName());
                hinhAnhTraHangService.save(hinhAnh);
            });
            return "redirect:/customer/DonHang/ChiTietHoaDon/ChoTraHang/hoa-don-id=" + id;
            //return "upload_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/customer/DonHang/ChiTietHoaDon/ChoTraHang/hoa-don-id=" + id;
        }
    }


//                System.out.println(file.getOriginalFilename());
//                StringBuilder fileNames = new StringBuilder();
//                Path fileNameAndPath = Paths.get("C:\\Users\\NamTV\\Downloads\\upload", file.getOriginalFilename());
//                fileNames.append(file.getOriginalFilename());
//                try {
//                    Files.write(fileNameAndPath, file.getBytes());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
}
