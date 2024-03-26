package com.fpoly.controller.admin.SanPham;

import com.fpoly.dto.VanAoDTO;
import com.fpoly.entity.VanAo;
import com.fpoly.service.VanAoService;
import com.fpoly.service.impl.VanAoServiceImpl;
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
@RequestMapping("admin/van-ao")
public class VanAoController {
	@Autowired
	private VanAoServiceImpl vanAoService;

	@GetMapping("")
	public String vanAo(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
			@RequestParam("messageDanger") Optional<String> messageDanger) {
		String[] tenVanAoSearch = request.getParameterValues("tenVanAoSearch");
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<VanAo> resultPage = null;
		if (tenVanAoSearch == null) {
			List<VanAoDTO> dtos = new ArrayList<>();
			resultPage = vanAoService.selectAllVanAoExist(pageable);
			for (VanAo vanAo : resultPage.getContent()) {
				VanAoDTO dto = new VanAoDTO();
				dto.setId(vanAo.getId());
				dto.setTenVanAo(vanAo.getTenVanAo());
				dtos.add(dto);
			}
			model.addAttribute("vanAos", dtos);
		} else {
			if(!tenVanAoSearch[0].isEmpty()) {
				List<VanAoDTO> dtos = new ArrayList<>();
				resultPage = vanAoService.getVanAoExistByName(tenVanAoSearch[0], pageable);
				for (VanAo vanAo : resultPage.getContent()) {
					VanAoDTO dto = new VanAoDTO();
					dto.setId(vanAo.getId());
					dto.setTenVanAo(vanAo.getTenVanAo());
					dtos.add(dto);
				}
				model.addAttribute("vanAos", dtos);
			}else {
				List<VanAoDTO> dtos = new ArrayList<>();
				resultPage = vanAoService.selectAllVanAoExist(pageable);
				for (VanAo vanAo : resultPage.getContent()) {
					VanAoDTO dto = new VanAoDTO();
					dto.setId(vanAo.getId());
					dto.setTenVanAo(vanAo.getTenVanAo());
					dtos.add(dto);
				}
				model.addAttribute("vanAos", dtos);
			}
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		if (messageSuccess.isPresent()) {
			model.addAttribute("messageSuccess", messageSuccess.get());
		}
		if (messageSuccess.isPresent()) {
			model.addAttribute("messageDanger", messageDanger.get());
		}
		model.addAttribute("resultPage", resultPage);
		return "admin/vanAo/vanAoManage";
	}
	
	@PostMapping("createOrUpdate")
	public String createOrUpdate(RedirectAttributes redirect,@RequestParam("tenVanAoCreateOrUpdate") String tenVanAo,
			@RequestParam("vanAoIdCreateOrUpdate") String vanAoId) {
		final String redirectUrl = "redirect:/admin/van-ao";
		if(tenVanAo != null && vanAoId!= null) {
			if(tenVanAo.isEmpty()) {
				redirect.addFlashAttribute("messageDanger","Tên vân áo không được để trống");
				return redirectUrl;
			}
			List<VanAo> listVanAo = vanAoService.selectAllVanAoExist();
			if (vanAoService.checkTrung(tenVanAo, listVanAo)) {
				redirect.addFlashAttribute("messageDanger", "Tên vân áo không được trùng");
				return redirectUrl;
			}
			Optional<VanAo> opt = vanAoService.findById(Long.parseLong(vanAoId));
			if(opt.isPresent()) {
				opt.get().setTenVanAo(tenVanAo);
				redirect.addFlashAttribute("messageSuccess","Cập nhật vân áo thành công");
				vanAoService.save(opt.get());
				return redirectUrl;
			}else {
				VanAo cl = new VanAo();
				cl.setTenVanAo(tenVanAo);
				redirect.addFlashAttribute("messageSuccess","Thêm mới vân áo thành công");
				vanAoService.save(cl);
				return redirectUrl;
			}
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi cập nhật vân áo");
			return redirectUrl;
		}
	}
	
	@GetMapping("info/{id}")
	public String info(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<VanAo> opt = vanAoService.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("vanAo", opt.get());
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi tìm chi tiết vân áo");
			return "redirect:/admin/van-ao";
		}
		return "admin/vanAo/infoVanAo";
	}
	

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<VanAo> opt = vanAoService.findById(id);
		if(opt.isPresent()) {
			vanAoService.delete(opt.get());
			redirect.addFlashAttribute("messageSuccess","Xóa vân áo thành công");
			return "redirect:/admin/van-ao";
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi xóa vân áo");
			return "redirect:/admin/van-ao";
		}
	}
}
