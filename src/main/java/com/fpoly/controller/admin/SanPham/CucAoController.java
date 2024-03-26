package com.fpoly.controller.admin.SanPham;

import com.fpoly.dto.CucAoDTO;
import com.fpoly.dto.VanAoDTO;
import com.fpoly.entity.CoAo;
import com.fpoly.entity.CucAo;
import com.fpoly.entity.VanAo;
import com.fpoly.service.CucAoService;
import com.fpoly.service.VanAoService;
import com.fpoly.service.impl.CucAoServiceImpl;
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
@RequestMapping("admin/cuc-ao")
public class CucAoController {
	@Autowired
	private CucAoServiceImpl cucAoService;

	@GetMapping("")
	public String cucAo(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
			@RequestParam("messageDanger") Optional<String> messageDanger) {
		String[] tenCucAoSearch = request.getParameterValues("tenCucAoSearch");
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<CucAo> resultPage = null;
		if (tenCucAoSearch == null) {
			List<CucAoDTO> dtos = new ArrayList<>();
			resultPage = cucAoService.selectAllCucAoExist(pageable);
			for (CucAo cucAo : resultPage.getContent()) {
				CucAoDTO dto = new CucAoDTO();
				dto.setId(cucAo.getId());
				dto.setTenCucAo(cucAo.getTenCucAo());
				dtos.add(dto);
			}
			model.addAttribute("cucAos", dtos);
		} else {
			if(!tenCucAoSearch[0].isEmpty()) {
				List<CucAoDTO> dtos = new ArrayList<>();
				resultPage = cucAoService.getCucAoExistByName(tenCucAoSearch[0], pageable);
				for (CucAo cucAo : resultPage.getContent()) {
					CucAoDTO dto = new CucAoDTO();
					dto.setId(cucAo.getId());
					dto.setTenCucAo(cucAo.getTenCucAo());
					dtos.add(dto);
				}
				model.addAttribute("cucAos", dtos);
			}else {
				List<CucAoDTO> dtos = new ArrayList<>();
				resultPage = cucAoService.selectAllCucAoExist(pageable);
				for (CucAo cucAo : resultPage.getContent()) {
					CucAoDTO dto = new CucAoDTO();
					dto.setId(cucAo.getId());
					dto.setTenCucAo(cucAo.getTenCucAo());
					dtos.add(dto);
				}
				model.addAttribute("cucAos", dtos);
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
		return "admin/cucAo/cucAoManage";
	}
	
	@PostMapping("createOrUpdate")
	public String createOrUpdate(RedirectAttributes redirect,@RequestParam("tenCucAoCreateOrUpdate") String tenCucAo,
			@RequestParam("cucAoIdCreateOrUpdate") String cucAoId) {
		final String redirectUrl = "redirect:/admin/cuc-ao";
		if(tenCucAo != null && cucAoId!= null) {
			if(tenCucAo.isEmpty()) {
				redirect.addFlashAttribute("messageDanger","Tên cúc áo không được để trống");
				return redirectUrl;
			}
			List<CucAo> listCucAo = cucAoService.selectAllCucAoExist();
			if (cucAoService.checkTrung(tenCucAo, listCucAo)) {
				redirect.addFlashAttribute("messageDanger", "Tên cúc áo không được trùng");
				return redirectUrl;
			}
			Optional<CucAo> opt = cucAoService.findById(Long.parseLong(cucAoId));
			if(opt.isPresent()) {
				opt.get().setTenCucAo(tenCucAo);
				redirect.addFlashAttribute("messageSuccess","Cập nhật cúc áo thành công");
				cucAoService.save(opt.get());
				return redirectUrl;
			}else {
				CucAo cl = new CucAo();
				cl.setTenCucAo(tenCucAo);
				redirect.addFlashAttribute("messageSuccess","Thêm mới cúc áo thành công");
				cucAoService.save(cl);
				return redirectUrl;
			}
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi cập nhật cúc áo");
			return redirectUrl;
		}
	}
	
	@GetMapping("info/{id}")
	public String info(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<CucAo> opt = cucAoService.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("cucAo", opt.get());
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi tìm chi tiết cúc áo");
			return "redirect:/admin/cuc-ao";
		}
		return "admin/cucAo/infoCucAo";
	}
	

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model,RedirectAttributes redirect) {
		Optional<CucAo> opt = cucAoService.findById(id);
		if(opt.isPresent()) {
			cucAoService.delete(opt.get());
			redirect.addFlashAttribute("messageSuccess","Xóa cúc áo thành công");
			return "redirect:/admin/cuc-ao";
		}else {
			redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi xóa cúc áo");
			return "redirect:/admin/cuc-ao";
		}
	}
}
