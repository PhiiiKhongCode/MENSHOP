package com.fpoly.controller.admin.SanPham;

import com.fpoly.dto.CoAoDTO;
import com.fpoly.entity.ChatLieu;
import com.fpoly.entity.CoAo;
import com.fpoly.service.CoAoService;
import com.fpoly.service.impl.CoAoServiceImpl;
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
@RequestMapping("admin/co-ao")
public class CoAoController {
	@Autowired
	private CoAoServiceImpl coAoService;

	@GetMapping("")
	public String coAo(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
			@RequestParam("messageDanger") Optional<String> messageDanger) {
		String[] tenCoAoSearch = request.getParameterValues("tenCoAoSearch");
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<CoAo> resultPage = null;
		if (tenCoAoSearch == null) {
			List<CoAoDTO> dtos = new ArrayList<>();
			resultPage = coAoService.selectAllCoAoExist(pageable);
			for (CoAo coAo : resultPage.getContent()) {
				CoAoDTO dto = new CoAoDTO();
				dto.setId(coAo.getId());
				dto.setTenCoAo(coAo.getTenCoAo());
				dtos.add(dto);
			}
			model.addAttribute("coAos", dtos);
		} else {
			if(!tenCoAoSearch[0].isEmpty()) {
				List<CoAoDTO> dtos = new ArrayList<>();
				resultPage = coAoService.getCoAoExistByName(tenCoAoSearch[0], pageable);
				for (CoAo coAo : resultPage.getContent()) {
					CoAoDTO dto = new CoAoDTO();
					dto.setId(coAo.getId());
					dto.setTenCoAo(coAo.getTenCoAo());
					dtos.add(dto);
				}
				model.addAttribute("coAos", dtos);
			}else {
				List<CoAoDTO> dtos = new ArrayList<>();
				resultPage = coAoService.selectAllCoAoExist(pageable);
				for (CoAo coAo : resultPage.getContent()) {
					CoAoDTO dto = new CoAoDTO();
					dto.setId(coAo.getId());
					dto.setTenCoAo(coAo.getTenCoAo());
					dtos.add(dto);
				}
				model.addAttribute("coAos", dtos);
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
		return "admin/coAo/coAoManage";
	}
	
	@PostMapping("createOrUpdate")
	public String createOrUpdate(RedirectAttributes redirect,@RequestParam("tenCoAoCreateOrUpdate") String tenCoAo,
			@RequestParam("coAoIdCreateOrUpdate") String coAoId) {
		final String redirectUrl = "redirect:/admin/co-ao";
		if(tenCoAo != null && coAoId!= null) {
			if(tenCoAo.isEmpty()) {
				redirect.addFlashAttribute("messageDanger","Tên cổ áo không được để trống");
				return redirectUrl;
			}
			List<CoAo> listCoAo = coAoService.selectAllCoAoExist();
			if (coAoService.checkTrung(tenCoAo, listCoAo)) {
				redirect.addFlashAttribute("messageDanger", "Tên cổ áo không được trùng");
				return redirectUrl;
			}
//			if (listCoAo.stream().anyMatch(ct -> tenCoAo.equals(ct.getTenCoAo()))) {
//				redirect.addFlashAttribute("messageDanger", "Tên cổ áo không được trùng");
//				return redirectUrl;
//			}
			Optional<CoAo> opt = coAoService.findById(Long.parseLong(coAoId));
			if(opt.isPresent()) {
				opt.get().setTenCoAo(tenCoAo);
				redirect.addFlashAttribute("messageSuccess","Cập nhật cổ áo thành công");
				coAoService.save(opt.get());
				return redirectUrl;
			}else {
				CoAo cl = new CoAo();
				cl.setTenCoAo(tenCoAo);
				redirect.addFlashAttribute("messageSuccess","Thêm mới cổ áo thành công");
				coAoService.save(cl);
				return redirectUrl;
			}
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi cập nhật cổ áo");
			return redirectUrl;
		}
	}
	
	@GetMapping("info/{id}")
	public String info(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<CoAo> opt = coAoService.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("coAo", opt.get());
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi tìm chi tiết cổ áo");
			return "redirect:/admin/co-ao";
		}
		return "admin/coAo/infoCoAo";
	}
	

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<CoAo> opt = coAoService.findById(id);
		if(opt.isPresent()) {
			coAoService.delete(opt.get());
			redirect.addFlashAttribute("messageSuccess","Xóa cổ áo thành công");
			return "redirect:/admin/co-ao";
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi xóa cổ áo");
			return "redirect:/admin/co-ao";
		}
	}
}
