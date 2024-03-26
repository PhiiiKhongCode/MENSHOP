package com.fpoly.controller.customer.HoaDon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fpoly.dto.*;
import com.fpoly.entity.*;
import com.fpoly.repository.*;
import com.fpoly.service.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Controller
public class TraHangCusController {
    @Autowired
    TraHangService traHangService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @RequestMapping(value = "/customer/DonHang/TraHang", method = RequestMethod.GET)
    public String DaGiaoCustomer(
            Model model,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "3", required = false) int size) {
        traHangService.danhSachTraHangCustomer(page, size, model);
        return "/customer/HoaDon/DanhSach/traHangCustomer";
    }

    @RequestMapping("/customer/DonHang/ChiTietHoaDon/ChoTraHang/hoa-don-id={id}")
    public String CTChoTraHangCustomer(@PathVariable("id") Long id,
                                     Model model) {
        traHangService.chiTietChoTraHangCustomer(id, model);
        return "customer/HoaDon/ChiTietHoaDon/CTChoTraHangCustomer";
    }

    @RequestMapping("/customer/DonHang/ChiTietHoaDon/DaHoanHang/hoa-don-id={id}")
    public String CTDaHoanCustomer(@PathVariable("id") Long id,
                                       Model model) {
        traHangService.chiTietDaHoanCustomer(id, model);
        return "customer/HoaDon/ChiTietHoaDon/CTDaHoanCustomer";
    }

    @GetMapping("/customer/TraHang/createOrUpdate")
    public String layHoaDonChiTiet(Model model,RedirectAttributes redirectAttributes) {
//        GioHangDTO gioHangDTO = null;
//        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
//        KhachHangDTO khachHangDT0 = khachHangService.findByEmail(auth);
//        if(khachHangDT0 != null) {
//            gioHangDTO = gioHangService.findByKhachHangId(khachHangDT0.getId());
//            if (gioHangDTO != null) {
//                if (gioHangDTO.getSoTienGiamGia() == null) {
//                    gioHangDTO.setSoTienGiamGia(0);
//                    if (gioHangChiTietService.getTongTienByKhachHangID(gioHangDTO.getId()) != null) {
//                        gioHangDTO.setTongTien(gioHangChiTietService.getTongTienByKhachHangID(gioHangDTO.getId()));
//                    } else {
//                        gioHangDTO.setTongTien(gioHangDTO.getTongTien());
//                    }
//                    gioHangDTO.setThanhTien(gioHangDTO.getTongTien() - gioHangDTO.getSoTienGiamGia());
//                }
//            } else {
//                gioHangDTO = new GioHangDTO();
//                gioHangDTO.setListGioHangChiTiets(null);
//            }
//            model.addAttribute("gioHangDTO", gioHangDTO);
//            return "/customer/khach-hang/gio-hang-chi-tiet";
//        }else{
//            redirectAttributes.addFlashAttribute("message","Phiên đăng nhập đã hết hạn !");
//            return "redirect:/security/login/form";
//        }
        HoaDonDTO hoaDonDTO = null;
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        KhachHangDTO khachHangDT0 = khachHangService.findByEmail(auth);
        if(khachHangDT0 != null) {
            hoaDonDTO = traHangService.findByKhachHangId(khachHangDT0.getId());
            if (hoaDonDTO != null) {
                if (hoaDonDTO.getTienShip() == null) {
                    hoaDonDTO.setTienShip(BigDecimal.ONE);
                    if (traHangService.getTongTienByKhachHangID(hoaDonDTO.getId()) != null) {
                        hoaDonDTO.setTongTienHoaDon(traHangService.getTongTienByKhachHangID(hoaDonDTO.getId()));
                    } else {
                        hoaDonDTO.setTongTienHoaDon(hoaDonDTO.getTongTienHoaDon());
                    }
                    hoaDonDTO.setTongTienDonHang(hoaDonDTO.getTongTienHoaDon().subtract(hoaDonDTO.getTienShip()));
                }
            } else {
                hoaDonDTO = new HoaDonDTO();
                hoaDonDTO.setListHoaDonChiTietDTOs(null);
            }
            model.addAttribute("gioHangDTO", hoaDonDTO);
            return CTChoTraHangCustomer(khachHangDT0.getId(), model);
        }else{
            redirectAttributes.addFlashAttribute("message","Phiên đăng nhập đã hết hạn !");
            return "redirect:/security/login/form";
        }
    }

    @RequestMapping(value = "/customer/TraHang/createOrUpdate", method = RequestMethod.POST)
    public String createOrUpdate(@RequestParam("ids") Long[] ids,
                                 @RequestParam("soLuongs") Integer[] soLuongs,
                                 @RequestParam("donGias") Integer[] donGias,
                                 @RequestParam(name = "lyDo", required = false) String lyDo,
                                 Model model,RedirectAttributes redirectAttributes) {
        log.info("RequestMapping POST /customer/TraHang/createOrUpdate");
        log.info("ids: {}",ids != null? Arrays.stream(ids).toArray() : 0);
        log.info("soLuongs: {}",soLuongs != null? Arrays.stream(soLuongs).toArray() : 0);
        log.info("donGias: {}",donGias != null? Arrays.stream(donGias).toArray() : 0);

        layHoaDonChiTiet(model, redirectAttributes);
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        KhachHangDTO khachHangDT0 = khachHangService.findByEmail(auth);
        List<Integer> listSoLuong = toListInterger(soLuongs);
        if (ids != null && soLuongs != null) {
            for (int i = 0; i < ids.length; i++) {
                Long id = (Long) Array.get(ids, i);
                Integer soLuong = listSoLuong.get((int) i);
                if (soLuong > 0) {
                    HoaDonChiTietDTO hoaDonChiTietDTO = traHangService.findById(id);
                    if (soLuong > hoaDonChiTietDTO.getSanPhamChiTietDTO().getSoLuong() + hoaDonChiTietDTO.getSoLuong()) {
                        model.addAttribute("message", "Số lượng  sản phẩm " + hoaDonChiTietDTO.getSanPhamChiTietDTO().getSanPhamDTO().getTenSanPham() +
                                " ,kích cỡ " + hoaDonChiTietDTO.getSanPhamChiTietDTO().getTenKichCo() + " ,màu " + hoaDonChiTietDTO.getSanPhamChiTietDTO().getTenMauSac() +
                                " không đủ !");
                        return CTChoTraHangCustomer(id,model);
                    }
                }
//                Optional<HoaDonChiTiet> optionalHoaDonCT = hoaDonChiTietRepository.findById(id);
//                if (optionalHoaDonCT.isPresent()) {
//                    HoaDonChiTiet hoaDonCT = optionalHoaDonCT.get();
//                    traHangService.capNhatTongTien(khachHangDT0.getId());
//                    HoaDon hoaDon = hoaDonCT.getHoaDon();
//                    BigDecimal tongTienDonHang = hoaDon.getHoaDonChiTiets().stream()
//                            .filter(hdct -> !hdct.isDaXoa())
//                            .map(HoaDonChiTiet::getTongTien)
//                            .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//                    BigDecimal tienShip = hoaDon.getTienShip();
//                    hoaDon.setTongTienDonHang(tongTienDonHang);
//                    hoaDon.setTongTienHoaDon(tongTienDonHang.add(tienShip));
//                    hoaDonRepository.save(hoaDon);
//                }
            }
            for (Integer soLuong : soLuongs) {
                if (soLuong == null) {
                    model.addAttribute("message", "Số lượng sản phẩm trống !");
                    return CTChoTraHangCustomer(khachHangDT0.getId(), model);
                } else if (soLuong <= 0) {
                    model.addAttribute("message", "Số lượng sản phẩm phải lớn hơn 0");
                    return CTChoTraHangCustomer(khachHangDT0.getId(), model);
                } else if (soLuong > 5) {
                    model.addAttribute("message", "Khách hàng muốn đặt số lượng lơn vui lòng liên hệ SĐT: 0363439953 ");
                    return CTChoTraHangCustomer(khachHangDT0.getId(), model);
                }
            }
            traHangService.capNhatSoLuongHoaDonChiTiet(ids, soLuongs, donGias);



//            TraHangDTO traHangDTO = new TraHangDTO();
//            if(traHangDTO.getId() != null){
//                Optional<TraHang> opt = traHangService.findByTHId(Long.valueOf(traHangDTO.getId()));
//                if(opt.isPresent()){
//                    TraHang traHang = opt.get();
//                traHang.setLyDo(traHangDTO.getLyDoTraHang());
//                traHang.setHinhAnh(null);
//                model.addAttribute("traHang", traHang);
//                redirectAttributes.addFlashAttribute("messageSuccess", "Trả hàng thành công");
//                traHangService.save(traHang);
//                }else{
//                    TraHang traHang = new TraHang();
//            traHang.setLyDo(traHangDTO.getLyDoTraHang());
//            traHang.setHinhAnh(null);
//            redirectAttributes.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi cập nhật đơn Trả hàng");
//            return CTChoTraHangCustomer(traHangDTO.getKhachHangId(), model);
//                }
//            }

            //Mốt là thay bằng spring security

            return CTChoTraHangCustomer(khachHangDT0.getId(), model);
        }
//        Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(Long.parseLong(traHangDto.getHoaDonChiTietId()));
//        if(!hoaDonChiTiet.isPresent()){
//            redirect.addFlashAttribute("messageDanger", "Hoá đơn không tô tại ");
//            return redirectUrl;
//        }
//
//        if(!hoaDonChiTiet.isPresent()){
//            redirect.addFlashAttribute("messageDanger", "Hoá đơn không tô tại ");
//            return redirectUrl;
//        }
//
//        if (traHangDto.getTraHangId() != null) {
//            // update
//            Optional<TraHang> opt = traHangService.findById(Long.parseLong(traHangDto.getTraHangId()));
//            if (opt.isPresent()) {
//                TraHang traHang = opt.get();
//                traHang.setLyDo(traHangDto.getLyDo());
//                traHang.setHinhAnh(null);
//                redirect.addFlashAttribute("messageSuccess", "Trả hàng thành công");
//                traHangService.save(traHang);
//                return redirectUrl;
//            }
//            redirect.addFlashAttribute("messageDanger", "Đơn hàng đang trong quá trình sử lý");
//            return redirectUrl;
//        } else {
//            //create
//            TraHang traHang = new TraHang();
//            traHang.setLyDo(traHangDto.getLyDo());
//            traHang.setHinhAnh(null);
//            redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi cập nhật đơn Trả hàng");
//            return redirectUrl;
//        }
//        redirectAttributes.addFlashAttribute("messageSuccess", "Trả hàng thành công");
       return "redirect:/customer/TraHang/createOrUpdate";
    }

//    @RequestMapping("customer/DonHang/TraHang")
//    public String chinhSuaKhachHangForm(
//            @RequestParam(value = "id", required = false) Long id,
//            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
//            @RequestParam(value = "lyDoTraHang", required = false) String lyDoTraHang,
//            @RequestParam(value = "soLuong", required = false) Integer soLuong,
//            @RequestParam(value = "sanPhamChiTietId") Long sanPhamChiTietId,
//            @RequestParam(value = "hoaDonChiTietId") Long hoaDonChiTietId,
//            Model model,
//            HttpServletRequest request) {
//        String message = request.getParameter("message");
//        TraHang traHang = new TraHang();
//        traHang.setDaXoa(false);
//        traHang.setNgayTra(new Date());
//        traHang.setLyDo(lyDoTraHang);
//        traHang.setNgayTao(new Date());
//        traHang.setNgayCapNhat(new Date());
//        traHang.setSoLuong(soLuong);
//        System.out.println(traHang.toString());
//
//        model.addAttribute("message", message);
//        model.addAttribute("model", khachHangDTO);
//        model.addAttribute("diaChiDTO", diaChiDTO);
//        model.addAttribute("email", email);
//        model.addAttribute("diaChiId", diaChiId);
//        return "customer/TraHang/DanhSach";
//    }

    public List<Integer> toListInterger(Integer[] integers) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < integers.length; i++) {
            Integer integer = integers[i];
            list.add(integer);
        }
        return list;
    }

    public static void main(String[] args) {
        String jsonString = "{\"TraHang\":{\"lyDo\":\"hang loi\",\"ngayTra\":\"2024-02-26 18:04:50.0\",\"soLuong\":1,\"hinhAnh\":null,\"status\":4,\"sanPhamChiTiet\":{\"maSanPhamChiTiet\":\"f37da374-23bd-4c9d-9995-2270e7cbb60b\",\"sanPham\":{\"id\":4,\"ngayTao\":\"2024-02-25 21:47:00.7\",\"nguoiTao\":\"staff@gmail.com\",\"ngayCapNhat\":\"2024-02-25 21:47:00.7\",\"nguoiCapNhat\":\"staff@gmail.com\"},\"soLuong\":60,\"hoaDonChiTiets\":2,\"daXoa\":false},\"hoaDonChiTiet\":{\"hoaDon\":10,\"sanPhamChiTiet\":12,\"donGia\":3000000,\"soLuong\":5,\"tongTien\":15000000,\"daXoa\":false},\"daXoa\":true}}";

        Gson gson = new Gson();
        Object jsonObject = gson.fromJson(jsonString, Object.class);
        String prettyJsonString = gson.toJson(jsonObject);
        System.out.println(prettyJsonString);
    }
}
