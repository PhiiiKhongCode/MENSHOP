package com.fpoly.controller.admin.SanPham;

import com.fpoly.dto.ThuongHieuDTO;
import com.fpoly.entity.ChatLieu;
import com.fpoly.entity.ThuongHieu;
import com.fpoly.repository.ThuongHieuRepository;
import com.fpoly.service.ChatLieuService;
import com.fpoly.service.SanPhamService;
import com.fpoly.service.ThuongHieuService;
import com.fpoly.service.impl.ThuongHieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuServiceImpl thuongHieuService;

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping()
    public String thuongHieu(Model model, HttpServletRequest request, @RequestParam("page")Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
                             @RequestParam("messageDanger") Optional<String> messageDanger){
        String[] tenThuongHieuSearchs = request.getParameterValues("tenThuongHieuSearch");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

        Page<ThuongHieu> resultPage = null;
        if(tenThuongHieuSearchs == null){
            List<ThuongHieuDTO> dtos = new ArrayList<>();
            resultPage = thuongHieuService.selectAllThuongHieuExist(pageable);
            for(ThuongHieu thuongHieu : resultPage.getContent()){
                ThuongHieuDTO dto = new ThuongHieuDTO();
                int soSanPhamCungThuongHieu = sanPhamService.selectCountSanPhamByThuongHieuId(thuongHieu.getId());
                dto.setId(thuongHieu.getId());
                dto.setTenThuongHieu(thuongHieu.getTenThuongHieu());
                dto.setSoSanPhamCungThuongHieu(soSanPhamCungThuongHieu);
                dtos.add(dto);
            }
            model.addAttribute("thuongHieus", dtos);
        }else{
            if(!tenThuongHieuSearchs[0].isEmpty()){
                List<ThuongHieuDTO> dtos = new ArrayList<>();
                resultPage = thuongHieuService.getThuongHieuExistByName(tenThuongHieuSearchs[0], pageable);
                for(ThuongHieu thuongHieu : resultPage.getContent()){
                    ThuongHieuDTO dto = new ThuongHieuDTO();
                    int soSanPhamCungThuongHieu = sanPhamService.selectCountSanPhamByThuongHieuId(thuongHieu.getId());
                    dto.setId(thuongHieu.getId());
                    dto.setTenThuongHieu(thuongHieu.getTenThuongHieu());
                    dto.setSoSanPhamCungThuongHieu(soSanPhamCungThuongHieu);
                    dtos.add(dto);
                }
                model.addAttribute("thuongHieus", dtos);
            }else{
                List<ThuongHieuDTO> dtos = new ArrayList<>();
                resultPage = thuongHieuService.selectAllThuongHieuExist(pageable);
                for(ThuongHieu thuongHieu : resultPage.getContent()){
                    ThuongHieuDTO dto = new ThuongHieuDTO();
                    int soSanPhamCungThuongHieu = sanPhamService.selectCountSanPhamByThuongHieuId(thuongHieu.getId());
                    dto.setId(thuongHieu.getId());
                    dto.setTenThuongHieu(thuongHieu.getTenThuongHieu());
                    dto.setSoSanPhamCungThuongHieu(soSanPhamCungThuongHieu);
                    dtos.add(dto);
                }
                model.addAttribute("thuongHieus", dtos);
            }

        }
        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0){
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if(totalPages > 5){
                if(end == totalPages){
                    start = end - 5;
                }else if(start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNUmbers", pageNumbers);
        }
        if(messageSuccess.isPresent()){
            model.addAttribute("messageSuccess", messageSuccess.get());
        }
        if(messageDanger.isPresent()){
            model.addAttribute("messageDanger", messageDanger.get());
        }
        System.out.println(resultPage.getTotalElements());
        model.addAttribute("resultPage", resultPage);
        return "admin/thuongHieu/thuongHieuManage";
    }

    @PostMapping("createOrUpdate")
    public String createOrUpdate(RedirectAttributes redirects, @RequestParam("tenThuongHieuCreateOrUpdate") String tenThuongHieu,
                                 @RequestParam("thuongHieuIdCreateOrUpdate") String thuongHieuId) {
        final String redirectUrl = "redirect:/admin/thuong-hieu";
        if (tenThuongHieu != null && thuongHieuId != null) {
            if (tenThuongHieu.isEmpty()) {
                redirects.addFlashAttribute("messageDanger", "Tên thương hiệu không được để trống");
                return redirectUrl;
            }
            List<ThuongHieu> listTH = thuongHieuService.selectAllThuongHieuExist();
            if (thuongHieuService.checkTrung(tenThuongHieu, listTH)) {
                redirects.addFlashAttribute("messageDanger", "Tên thương hiệu không được trùng");
                return redirectUrl;
            }
            Optional<ThuongHieu> opt = thuongHieuService.findById(Long.parseLong(thuongHieuId));
            if (opt.isPresent()) {
                opt.get().setTenThuongHieu(tenThuongHieu);
                redirects.addFlashAttribute("messageSuccess", "Cập nhật thương hiệu thành công");
                thuongHieuService.save(opt.get());
                return redirectUrl;
            } else {
                ThuongHieu thuongHieu = new ThuongHieu();
                thuongHieu.setTenThuongHieu(tenThuongHieu);
                thuongHieu.setDaXoa(false);
                redirects.addFlashAttribute("messageSuccess", "Thêm mới thương hiệu thành công");
                thuongHieuService.save(thuongHieu);
                return redirectUrl;
            }
        }else {
            redirects.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi cập nhật thương hiệu");
            return redirectUrl;
        }
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") Long id, Model model, RedirectAttributes redirects){
        Optional<ThuongHieu> opt = thuongHieuService.findById(id);
        if(opt.isPresent()){
            model.addAttribute("thuongHieu", opt.get());
        }else{
            redirects.addFlashAttribute("messageDanger", "Lỗi khi tìm kiếm thương hiệu");
            return "redirect:/admin/thuong-hieu";
        }
        return "admin/thuongHieu/infoThuongHieu";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model,RedirectAttributes redirects) {
        Optional<ThuongHieu> opt = thuongHieuService.findById(id);
        if(opt.isPresent()) {
            thuongHieuService.delete(opt.get());
            redirects.addFlashAttribute("messageSuccess","Xóa thương hiệu thành công");
            return "redirect:/admin/thuong-hieu";
        }else {
            redirects.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi xóa thương hiệu");
            return "redirect:/admin/thuong-hieu";
        }
    }
}
