package com.fpoly.controller.admin.SanPham;

import com.fpoly.dto.TuiAoDTO;
import com.fpoly.entity.TuiAo;
import com.fpoly.service.TuiAoService;
import com.fpoly.service.impl.TuiAoServiceImpl;
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
@RequestMapping("admin/tui-ao")
public class TuiAoController {
	@Autowired
	private TuiAoServiceImpl tuiAoService;

	@GetMapping("")
	public String tuiAo(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
			@RequestParam("messageDanger") Optional<String> messageDanger) {
		String[] tenTuiAoSearch = request.getParameterValues("tenTuiAoSearch");
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<TuiAo> resultPage = null;
		if (tenTuiAoSearch == null) {
			List<TuiAoDTO> dtos = new ArrayList<>();
			resultPage = tuiAoService.selectAllTuiAoExist(pageable);
			for (TuiAo tuiAo : resultPage.getContent()) {
				TuiAoDTO dto = new TuiAoDTO();
				dto.setId(tuiAo.getId());
				dto.setTenTuiAo(tuiAo.getTenTuiAo());
				dtos.add(dto);
			}
			model.addAttribute("tuiAos", dtos);
		} else {
			if(!tenTuiAoSearch[0].isEmpty()) {
				List<TuiAoDTO> dtos = new ArrayList<>();
				resultPage = tuiAoService.getTuiAoExistByName(tenTuiAoSearch[0], pageable);
				for (TuiAo tuiAo : resultPage.getContent()) {
					TuiAoDTO dto = new TuiAoDTO();
					dto.setId(tuiAo.getId());
					dto.setTenTuiAo(tuiAo.getTenTuiAo());
					dtos.add(dto);
				}
				model.addAttribute("tuiAos", dtos);
			}else {
				List<TuiAoDTO> dtos = new ArrayList<>();
				resultPage = tuiAoService.selectAllTuiAoExist(pageable);
				for (TuiAo tuiAo : resultPage.getContent()) {
					TuiAoDTO dto = new TuiAoDTO();
					dto.setId(tuiAo.getId());
					dto.setTenTuiAo(tuiAo.getTenTuiAo());
					dtos.add(dto);
				}
				model.addAttribute("tuiAos", dtos);
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
		return "admin/tuiAo/tuiAoManage";
	}
	
	@PostMapping("createOrUpdate")
	public String createOrUpdate(RedirectAttributes redirect,@RequestParam("tenTuiAoCreateOrUpdate") String tenTuiAo,
			@RequestParam("tuiAoIdCreateOrUpdate") String tuiAoId) {
		final String redirectUrl = "redirect:/admin/tui-ao";
		if(tenTuiAo != null && tuiAoId!= null) {
			if(tenTuiAo.isEmpty()) {
				redirect.addFlashAttribute("messageDanger","Tên túi áo không được để trống");
				return redirectUrl;
			}
			List<TuiAo> listTuiAo = tuiAoService.selectAllTuiAoExist();
			if (tuiAoService.checkTrung(tenTuiAo, listTuiAo)) {
				redirect.addFlashAttribute("messageDanger", "Tên túi áo không được trùng");
				return redirectUrl;
			}
			Optional<TuiAo> opt = tuiAoService.findById(Long.parseLong(tuiAoId));
			if(opt.isPresent()) {
				opt.get().setTenTuiAo(tenTuiAo);
				redirect.addFlashAttribute("messageSuccess","Cập nhật túi áo thành công");
				tuiAoService.save(opt.get());
				return redirectUrl;
			}else {
				TuiAo cl = new TuiAo();
				cl.setTenTuiAo(tenTuiAo);
				redirect.addFlashAttribute("messageSuccess","Thêm mới túi áo thành công");
				tuiAoService.save(cl);
				return redirectUrl;
			}
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi cập nhật túi áo");
			return redirectUrl;
		}
	}
	
	@GetMapping("info/{id}")
	public String info(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<TuiAo> opt = tuiAoService.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("tuiAo", opt.get());
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi tìm chi tiết túi áo");
			return "redirect:/admin/tui-ao";
		}
		return "admin/tuiAo/infoTuiAo";
	}
	

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<TuiAo> opt = tuiAoService.findById(id);
		if(opt.isPresent()) {
			tuiAoService.delete(opt.get());
			redirect.addFlashAttribute("messageSuccess","Xóa túi áo thành công");
			return "redirect:/admin/tui-ao";
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi xóa túi áo");
			return "redirect:/admin/tui-ao";
		}
	}
}
