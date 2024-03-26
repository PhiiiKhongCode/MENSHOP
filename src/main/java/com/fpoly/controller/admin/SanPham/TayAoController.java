package com.fpoly.controller.admin.SanPham;

import com.fpoly.dto.TayAoDTO;
import com.fpoly.entity.TayAo;
import com.fpoly.service.TayAoService;
import com.fpoly.service.impl.TayAoServiceImpl;
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
@RequestMapping("admin/tay-ao")
public class TayAoController {
	@Autowired
	private TayAoServiceImpl tayAoService;

	@GetMapping("")
	public String tayAo(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
			@RequestParam("messageDanger") Optional<String> messageDanger) {
		String[] tenTayAoSearch = request.getParameterValues("tenTayAoSearch");
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<TayAo> resultPage = null;
		if (tenTayAoSearch == null) {
			List<TayAoDTO> dtos = new ArrayList<>();
			resultPage = tayAoService.selectAllTayAoExist(pageable);
			for (TayAo tayAo : resultPage.getContent()) {
				TayAoDTO dto = new TayAoDTO();
				dto.setId(tayAo.getId());
				dto.setTenTayAo(tayAo.getTenTayAo());
				dtos.add(dto);
			}
			model.addAttribute("tayAos", dtos);
		} else {
			if(!tenTayAoSearch[0].isEmpty()) {
				List<TayAoDTO> dtos = new ArrayList<>();
				resultPage = tayAoService.getTayAoExistByName(tenTayAoSearch[0], pageable);
				for (TayAo tayAo : resultPage.getContent()) {
					TayAoDTO dto = new TayAoDTO();
					dto.setId(tayAo.getId());
					dto.setTenTayAo(tayAo.getTenTayAo());
					dtos.add(dto);
				}
				model.addAttribute("tayAos", dtos);
			}else {
				List<TayAoDTO> dtos = new ArrayList<>();
				resultPage = tayAoService.selectAllTayAoExist(pageable);
				for (TayAo tayAo : resultPage.getContent()) {
					TayAoDTO dto = new TayAoDTO();
					dto.setId(tayAo.getId());
					dto.setTenTayAo(tayAo.getTenTayAo());
					dtos.add(dto);
				}
				model.addAttribute("tayAos", dtos);
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
		return "admin/tayAo/tayAoManage";
	}
	
	@PostMapping("createOrUpdate")
	public String createOrUpdate(RedirectAttributes redirect,@RequestParam("tenTayAoCreateOrUpdate") String tenTayAo,
			@RequestParam("tayAoIdCreateOrUpdate") String tayAoId) {
		final String redirectUrl = "redirect:/admin/tay-ao";
		if(tenTayAo != null && tayAoId != null) {
			if(tenTayAo.isEmpty()) {
				redirect.addFlashAttribute("messageDanger","Tên tay áo không được để trống");
				return redirectUrl;
			}
			List<TayAo> listTayAo = tayAoService.selectAllTayAoExist();
			if (tayAoService.checkTrung(tenTayAo, listTayAo)) {
				redirect.addFlashAttribute("messageDanger", "Tên tay áo không được trùng");
				return redirectUrl;
			}
			Optional<TayAo> opt = tayAoService.findById(Long.parseLong(tayAoId));
			if(opt.isPresent()) {
				opt.get().setTenTayAo(tenTayAo);
				redirect.addFlashAttribute("messageSuccess","Cập nhật tay áo thành công");
				tayAoService.save(opt.get());
				return redirectUrl;
			}else {
				TayAo cl = new TayAo();
				cl.setTenTayAo(tenTayAo);
				redirect.addFlashAttribute("messageSuccess","Thêm mới tay áo thành công");
				tayAoService.save(cl);
				return redirectUrl;
			}
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi cập nhật tay áo");
			return redirectUrl;
		}
	}
	
	@GetMapping("info/{id}")
	public String info(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<TayAo> opt = tayAoService.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("tayAo", opt.get());
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi tìm chi tiết tay áo");
			return "redirect:/admin/tay-ao";
		}
		return "admin/tayAo/infoTayAo";
	}
	

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<TayAo> opt = tayAoService.findById(id);
		if(opt.isPresent()) {
			tayAoService.delete(opt.get());
			redirect.addFlashAttribute("messageSuccess","Xóa tay áo thành công");
			return "redirect:/admin/tay-ao";
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi xóa tay áo");
			return "redirect:/admin/tay-ao";
		}
	}
}
