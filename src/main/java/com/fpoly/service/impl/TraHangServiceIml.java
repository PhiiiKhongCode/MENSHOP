package com.fpoly.service.impl;

import com.fpoly.convertor.HoaDonChiTietConvertor;
import com.fpoly.convertor.HoaDonConvertor;
import com.fpoly.convertor.SanPhamChiTietConvertor;
import com.fpoly.convertor.SanPhamConvertor;
import com.fpoly.dto.HoaDonChiTietDTO;
import com.fpoly.dto.HoaDonDTO;
import com.fpoly.dto.SanPhamChiTietDTO;
import com.fpoly.entity.*;
import com.fpoly.repository.*;
import com.fpoly.service.EmailService;
import com.fpoly.service.HinhAnhTraHangService;
import com.fpoly.service.HoaDonService;
import com.fpoly.service.TraHangService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@AllArgsConstructor
@Service
@Slf4j
public class TraHangServiceIml implements TraHangService {
    private TraHangRepository traHangRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    private SanPhamChiTietConvertor sanPhamChiTietConvertor;

    @Autowired
    private SanPhamConvertor sanPhamConvertor;

    @Autowired
    private HoaDonChiTietConvertor hoaDonChiTietConvertor;

    @Autowired
    private HoaDonConvertor hoaDonConvertor;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private HinhAnhTraHangService hinhAnhTraHangService;

    @Override
    public <S extends TraHang> S save(S entity) {
        entity.setDaXoa(false);
        return traHangRepository.save(entity);
    }

    @Override
    public void danhSachTraHangCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        System.out.println("ok");
//        Page<TraHang> traHangs = traHangRepository.findAllTraHang(pageable);
        List<Integer> trangThai = Arrays.asList(9,11,12,13,15);
        Page<HoaDon> DaHoan = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(trangThai, pageable);
        System.out.println("getContent - {}"+DaHoan.getContent());
        System.out.println("getTotalPages - {}"+DaHoan.getTotalPages());

        model.addAttribute("DaHoanCustomer", DaHoan.getContent());
        model.addAttribute("pageDaHoan", DaHoan.getTotalPages());
        model.addAttribute("page", page);
        System.out.println("ok");
    }

    @Override
    public void chiTietDaHoanCustomer(Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineHoanDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(9, id);
        List<GiaoDich> timeLinePheDuyetHoanDon = giaoDichRepository.findByTrangThaiIdAndHoaDonId(11,id);
        List<GiaoDich> timeLineChapNhanHoanDon = giaoDichRepository.findByTrangThaiIdAndHoaDonId(12,id);
        List<GiaoDich> timeLineKhongChapNhanHoanDon = giaoDichRepository.findByTrangThaiIdAndHoaDonId(13,id);
        List<GiaoDich> timeLineDangGiaoHoanTra = giaoDichRepository.findByTrangThaiIdAndHoaDonId(14,id);
        List<GiaoDich> timeLineHoanTraThanhCong = giaoDichRepository.findByTrangThaiIdAndHoaDonId(15,id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        List<lichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineHoanDonHang", timeLineHoanDonHang);
        model.addAttribute("timeLinePheDuyetHoanDon", timeLinePheDuyetHoanDon);
        model.addAttribute("timeLineChapNhanHoanDon", timeLineChapNhanHoanDon);
        model.addAttribute("timeLineKhongChapNhanHoanDon", timeLineKhongChapNhanHoanDon);
        model.addAttribute("timeLineDangGiaoHoanTra", timeLineDangGiaoHoanTra);
        model.addAttribute("timeLineHoanTraThanhCong", timeLineHoanTraThanhCong);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void chiTietChoTraHangCustomer(Long id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(id);
//        if (hoaDonOptional.isPresent()) {
//            HoaDon hoaDon = hoaDonOptional.get();
//            HoaDonDTO hoaDonDTO = new HoaDonDTO();
//            hoaDonDTO.setLyDo(hoaDon.getLyDo());
//            hoaDonRepository.save(hoaDon);
            List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
            List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
            List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
            List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, id);
            List<GiaoDich> timeLineDaTraHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(9, id);
            List<GiaoDich> timeLinePheDuyetHoanDon = giaoDichRepository.findByTrangThaiIdAndHoaDonId(11, id);
            List<GiaoDich> timeLineDangGiaoHoanTra = giaoDichRepository.findByTrangThaiIdAndHoaDonId(14, id);

        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        List<HinhAnhTraHang> hinhAnhTraHangs = hinhAnhTraHangService.findAllByHoaDonId(hoaDon.getId());
        if (hinhAnhTraHangs != null && hinhAnhTraHangs.size() > 0) {
            model.addAttribute("hinhAnhTraHangs", hinhAnhTraHangs);
        }
        List<lichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        model.addAttribute("timeLineDaTraHang", timeLineDaTraHang);
        model.addAttribute("timeLinePheDuyetHoanDon", timeLinePheDuyetHoanDon);
        model.addAttribute("timeLineDangGiaoHoanTra", timeLineDangGiaoHoanTra);
        model.addAttribute("hoaDon", hoaDon);
//        }else {
//            // Handle case when HoaDon with given id is not found
//            // For example, you can add an attribute to the model indicating the error
//            model.addAttribute("error", "HoaDon not found with id: " + id);
//        }
    }

    @Override
    @Transactional
    public void capNhatSoLuongHoaDonChiTiet(Long[] ids, Integer[] soLuongs, Integer[] donGias) {
        List<Integer> listSoLuong = Arrays.asList(soLuongs);
        List<Integer> listDonGia = Arrays.asList(donGias);

        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            Integer soLuong = listSoLuong.get(i);
            Integer donGia = listDonGia.get(i);

            if (soLuong > 0) {
                try {
                    HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("HoaDonChiTiet not found with id: " + id));

                    int soLuongDifference = soLuong - hoaDonChiTiet.getSoLuong();
                    int updatedSanPhamSoLuong = hoaDonChiTiet.getSanPhamChiTiet().getSoLuong() - soLuongDifference;

                    hoaDonChiTiet.getSanPhamChiTiet().setSoLuong(updatedSanPhamSoLuong);
                    hoaDonChiTiet.setSoLuong(soLuong);
                    hoaDonChiTiet.setDonGia(BigDecimal.valueOf(donGia));
                    hoaDonChiTiet.setTongTien(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong())
                            .multiply(hoaDonChiTiet.getDonGia())
                            .abs());

                    hoaDonChiTietRepository.save(hoaDonChiTiet);
                } catch (EntityNotFoundException e) {
                    // Handle the exception or log it appropriately
                    e.printStackTrace(); // Replace this with proper logging
                }
            }
        }
    }

    @Override
    public void capNhatTongTien(Long id) {
        List<HoaDon> hoaDonList = hoaDonRepository.findHoaDonByKhachHangId(id);

        if (hoaDonList != null && !hoaDonList.isEmpty()) {
            HoaDon hoaDon = hoaDonList.get(0); // Lấy hoặc xác định cách lấy đối tượng HoaDon từ danh sách

            BigDecimal tongTien = BigDecimal.valueOf(hoaDonChiTietRepository.tongTien(hoaDon.getId()));
            hoaDon.setTongTienHoaDon(tongTien);

            hoaDonRepository.save(hoaDon);
        }
    }

    @Override
    public int tinhTienHoaDonTheoMaHoaDonChiTiet(long[] idHoaDonChiTiet) {
            int thanhTien = 0;
            for (int i = 0; i < idHoaDonChiTiet.length; i++) {
                Long id = (Long) Array.get(idHoaDonChiTiet, i);
                HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id).get();
                thanhTien += Integer.parseInt(hoaDonChiTiet.getTongTien().toString());
            }
            return thanhTien;
    }

    @Override
    public HoaDonDTO findByKhachHangId(Long id) {
        HoaDonDTO hoaDonDTO = null;
        if (id != null) {
            List<HoaDon> hoaDonEntityList = hoaDonRepository.findHoaDonByKhachHangId(id);
            if (hoaDonEntityList != null && !hoaDonEntityList.isEmpty()) {
                HoaDon hoaDonEntity = hoaDonEntityList.get(0);

                List<HoaDonChiTietDTO> listHoaDonChiTietDTO = new ArrayList<>();

                List<HoaDonChiTiet> hoaDonChiTiets = hoaDonEntity.getHoaDonChiTiets();
                if (hoaDonChiTiets != null) {
                    // Tiếp tục xử lý danh sách hoaDonChiTietDTO
                    for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                        if (!hoaDonChiTiet.isDaXoa()) {
                            HoaDonChiTietDTO hoaDonChiTietDTO = hoaDonChiTietConvertor.toDTO(hoaDonChiTiet);

                            // Xử lý các thao tác khác với hoaDonChiTietDTO
                            SanPhamChiTietDTO sanPhamChiTietDTO = sanPhamChiTietConvertor.toDTO(hoaDonChiTiet.getSanPhamChiTiet());
                            sanPhamChiTietDTO.setSanPhamDTO(sanPhamConvertor.toDTO(hoaDonChiTiet.getSanPhamChiTiet().getSanPham()));
                            HinhAnh hinhAnh = hinhAnhRepository.findByHinhAnhByMauSacIdVaLaAnhChinh(
                                    hoaDonChiTiet.getSanPhamChiTiet().getMauSac().getId(),
                                    hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getId()
                            );
                            sanPhamChiTietDTO.getSanPhamDTO().setTenHinhAnh(hinhAnh.getTenAnh());
                            hoaDonChiTietDTO.setSanPhamChiTietDTO(sanPhamChiTietDTO);

                            listHoaDonChiTietDTO.add(hoaDonChiTietDTO);
                        }
                    }

                    hoaDonDTO = hoaDonConvertor.toDTO(hoaDonEntity);
                    hoaDonDTO.setListHoaDonChiTietDTOs(listHoaDonChiTietDTO);

                    return hoaDonDTO;
                }
            }
        }
        return null;
    }

    @Override
    public BigDecimal getTongTienByKhachHangID(Long id) {
        List<HoaDonChiTiet> hdct = hoaDonChiTietRepository.findAllByHoaDonId(id);
        if(!hdct.isEmpty()) {
            if(hoaDonChiTietRepository.tongTien(id) != null ) {
                return BigDecimal.valueOf(hoaDonChiTietRepository.tongTien(id));
            }
        }else {
            return BigDecimal.ONE ;
        }
        return null ;
    }

    @Override
    public Long taoTraDonBanHangOnline(List<Long> selectedCartItemIds, RedirectAttributes redirectAttributes) {
        List<HoaDonChiTiet> selectedCartItemDetails = new ArrayList<>();

        Optional<HoaDonChiTiet> optionalCartItemDetail = null;
        for (Long cartItemId : selectedCartItemIds) {
            optionalCartItemDetail = hoaDonChiTietRepository.findById(cartItemId);
            if (optionalCartItemDetail.isPresent()) {
                selectedCartItemDetails.add(optionalCartItemDetail.get());
            }
        }

        HoaDon hoaDon = new HoaDon();
        Integer maxId = hoaDonService.getMaxId();
        int idMax;
        String ma;

        if (maxId != null) {
            idMax = maxId + 1;
        } else {
            idMax = 1;
        }

        DecimalFormat df = new DecimalFormat("00");
        String formattedId = df.format(idMax);
        ma = "HD" + formattedId;

        hoaDon.setMaDon(ma);
        hoaDon.setNgayTao(new Date());
        hoaDon.setNguoiTao("namtv");
        hoaDon.setKhachHang(optionalCartItemDetail.get().getHoaDon().getKhachHang());
        hoaDon.setLoaiHoaDon(2);
        hoaDon.setDaXoa(false);
        hoaDonRepository.save(hoaDon);

        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();

        BigDecimal tongTienDonhang = BigDecimal.ZERO;

        for (HoaDonChiTiet hoaDonChiTiet : selectedCartItemDetails) {
            HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet();
            hoaDonChiTiet1.setSanPhamChiTiet(hoaDonChiTiet.getSanPhamChiTiet());
            hoaDonChiTiet1.setSoLuong(hoaDonChiTiet.getSoLuong());
            hoaDonChiTiet1.setDonGia(hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getGia());
            hoaDonChiTiet1.setTongTien(hoaDonChiTiet.getTongTien());
            hoaDonChiTiet1.setHoaDon(hoaDon);
            hoaDonChiTietList.add(hoaDonChiTiet);
            hoaDonChiTietRepository2.save(hoaDonChiTiet);

            tongTienDonhang = tongTienDonhang.add(hoaDonChiTiet.getTongTien()); // Cộng dồn tổng tiền đơn hàng
        }
        hoaDon.setTongTienDonHang(tongTienDonhang);
        hoaDon.setHoaDonChiTiets(hoaDonChiTietList);
        hoaDonRepository.save(hoaDon);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);

        if (OptNguoiDung.isPresent()) {
            NguoiDung nguoiDung = OptNguoiDung.get();
            //Lưu lịch sử hóa đơn
            lichSuHoaDon ls = new lichSuHoaDon();
            ls.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
            ls.setHoaDon(hoaDon);
            ls.setThaoTac("Tạo đơn hàng");
            lichSuHoaDonRepository.save(ls);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Chi tiết hóa đơn đã được lưu thành công");
        return hoaDon.getId();
    }

    @Override
    public HoaDonChiTietDTO findById(Long id) {
        HoaDonChiTietDTO hoaDonChiTietDTO = null ;
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id).get();
        if(hoaDonChiTiet != null) {
            hoaDonChiTietDTO = new HoaDonChiTietDTO();
            hoaDonChiTietDTO = hoaDonChiTietConvertor.toDTO(hoaDonChiTiet);
            if(hoaDonChiTiet.getSanPhamChiTiet() != null) {
                hoaDonChiTietDTO.setSanPhamChiTietDTO(sanPhamChiTietConvertor.toDTO(hoaDonChiTiet.getSanPhamChiTiet()));
                hoaDonChiTietDTO.getSanPhamChiTietDTO().setSanPhamDTO(sanPhamConvertor.toDTO(hoaDonChiTiet.getSanPhamChiTiet().getSanPham()));
            }
            return hoaDonChiTietDTO;
        }
        return hoaDonChiTietDTO;
    }

    public List<Integer> toListInterger(Integer[] integers) {
        return Arrays.asList(integers);
    }

    @Override
    public Optional<TraHang> findByTHId(Long id) {
         return traHangRepository.findById(id);
    }

}
