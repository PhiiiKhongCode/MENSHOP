package com.fpoly.controller.admin.SanPham;

import com.fpoly.dto.HoaTietDTO;
import com.fpoly.entity.CoAo;
import com.fpoly.entity.HoaTiet;
import com.fpoly.repository.HoaTietRepository;
import com.fpoly.service.HoaTietService;
import com.fpoly.service.SanPhamChiTietService;
import com.fpoly.service.impl.HoaTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
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
@RequestMapping("admin/hoa-tiet")
public class HoaTietController {
    @Autowired
    private HoaTietServiceImpl hoaTietService;

    @GetMapping("")
    public String hoaTiet(Model model, HttpServletRequest request, @RequestParam("page")Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
                          @RequestParam("messageDanger") Optional<String> messageDanger){
        String[] tenHoaTietSearchs = request.getParameterValues("tenHoaTietSearch");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<HoaTiet> resultPage = null;
        if(tenHoaTietSearchs == null){
            List<HoaTietDTO> dtos = new ArrayList<>();
            resultPage = hoaTietService.selectAllHoaTietExist(pageable);
            for (HoaTiet hoaTiet : resultPage.getContent()){
                HoaTietDTO dto = new HoaTietDTO();
                dto.setId(hoaTiet.getId());
                dto.setTenHoaTiet(hoaTiet.getTenHoaTiet());
                dtos.add(dto);
            }
            model.addAttribute("hoatiets", dtos);
        }else{
            if(!tenHoaTietSearchs[0].isEmpty()){
                List<HoaTietDTO> dtos = new ArrayList<>();
                resultPage = hoaTietService.getHoaTietExistByName(tenHoaTietSearchs[0], pageable);
                for(HoaTiet hoaTiet : resultPage.getContent()){
                    HoaTietDTO dto = new HoaTietDTO();
                    dto.setId(hoaTiet.getId());
                    dto.setTenHoaTiet(hoaTiet.getTenHoaTiet());
                    dtos.add(dto);
                }
                model.addAttribute("hoaTiets", dtos);
            }else{
                List<HoaTietDTO> dtos = new ArrayList<>();
                resultPage = hoaTietService.selectAllHoaTietExist(pageable);
                for (HoaTiet hoaTiet : resultPage.getContent()){
                    HoaTietDTO dto = new HoaTietDTO();
                    dto.setId(hoaTiet.getId());
                    dto.setTenHoaTiet(hoaTiet.getTenHoaTiet());
                    dtos.add(dto);
                }
                model.addAttribute("hoatiets", dtos);
            }
        }
        int totalPage = resultPage.getTotalPages();
        if(totalPage > 0){
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPage);
            if(totalPage > 5){
                if(end == totalPage){
                    start = end - 5;
                }else if(start == 1){
                    end = start + 5;
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        if(messageSuccess.isPresent()){
            model.addAttribute("messageSuccess", messageSuccess.get());
        }
        if(messageDanger.isPresent()){
            model.addAttribute("messageDanger", messageDanger.get());
        }
        model.addAttribute("resultPage", resultPage);
        return "admin/hoaTiet/hoaTietManage";
    }

    @PostMapping("createOrUpdate")
    public String createOrUpdate(RedirectAttributes redirects, @RequestParam("tenHoaTietCreateOrUpdate") String tenHoaTiet
            , @RequestParam("hoaTietIdCreateOrUpdate") String hoaTietId) {
        final String redirectUrl = "redirect:/admin/hoa-tiet";
        if(tenHoaTiet != null && hoaTietId != null){
            if(tenHoaTiet.isEmpty()){
                redirects.addFlashAttribute("messageDanger", "Tên họa tiết không được trống");
                return redirectUrl;
            }
            List<HoaTiet> listHoaTiet = hoaTietService.selectAllHoaTietExist();
            if (hoaTietService.checkTrung(tenHoaTiet, listHoaTiet)) {
                redirects.addFlashAttribute("messageDanger", "Tên họa tiết áo không được trùng");
                return redirectUrl;
            }
            Optional<HoaTiet> opt = hoaTietService.findById(Long.parseLong(hoaTietId));
            if(opt.isPresent()){
                opt.get().setTenHoaTiet(tenHoaTiet);
                redirects.addFlashAttribute("messageSuccess", "Cập nhật họa tiết thành công");
                hoaTietService.save(opt.get());
                return redirectUrl;
            }else{
                HoaTiet ht = new HoaTiet();
                ht.setTenHoaTiet(tenHoaTiet);
                redirects.addFlashAttribute("messageSuccess", "Thêm mới họa tiết thành công");
                ht.setDaXoa(false);
                hoaTietService.save(ht);
                return redirectUrl;
            }
        }else {
            redirects.addFlashAttribute("messageDanger", "Đã xảy ra lỗi");
            return redirectUrl;
        }
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") Long id, Model model, RedirectAttributes redirects){
        Optional<HoaTiet> opt = hoaTietService.findById(id);
        if(opt.isPresent()){
            model.addAttribute("hoaTiet", opt.get());

        }else{
            redirects.addFlashAttribute("MessageDanger", "Đã xảy ra lỗi khi tìm chi tiết họa tiết");
            return "redirect:/admin/hoa-tiet";
        }
        return "admin/hoaTiet/infoHoaTiet";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirects){
        Optional<HoaTiet> opt = hoaTietService.findById(id);
        if(opt.isPresent()){
            hoaTietService.delete(opt.get());
            redirects.addFlashAttribute("messageSuccess", "Xóa họa tiết thành công");
            return "redirect:/admin/hoa-tiet";
        }else{
            redirects.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi xóa họa tiết");
            return "redirect:/admin/hoa-tiet";
        }
    }
}
