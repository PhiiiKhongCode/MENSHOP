package com.fpoly.service.impl;

import com.fpoly.entity.PhongCach;
import com.fpoly.entity.ThuongHieu;
import com.fpoly.repository.PhongCachRepository;
import com.fpoly.repository.ThuongHieuRepository;
import com.fpoly.service.PhongCachService;
import com.fpoly.service.ThuongHieuService;
import com.fpoly.service.ValidateService;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ThuongHieuServiceImpl implements ThuongHieuService, ValidateService<ThuongHieu> {
	private ThuongHieuRepository thuongHieuRepository;
	private static Logger logger = LoggerFactory.getLogger(ThuongHieuServiceImpl.class);
	
	@Override
	public List<ThuongHieu> selectAllThuongHieuExist() {
		return thuongHieuRepository.findAllThuongHieuExist();
	}
	@Override
	public <S extends ThuongHieu> S save(S entity) {
		entity.setDaXoa(false);
		return thuongHieuRepository.save(entity);
	}
	
	@Override
	public Optional<ThuongHieu> findById(Long id) {
		return thuongHieuRepository.findById(id);
	}
	@Override
	public Page<ThuongHieu> selectAllThuongHieuExist(Pageable page) {
		return thuongHieuRepository.findAllThuongHieuExist(page);
	}
	
	@Override
	public Page<ThuongHieu> getThuongHieuExistByName(String tenThuongHieu, Pageable page) {
		return thuongHieuRepository.getThuongHieuExistByName(tenThuongHieu, page);
	}
	
	@Override
	public void delete(ThuongHieu entity) {
		thuongHieuRepository.delete(entity);
	}


	@Override
	public boolean checkTrung(String ten, List<ThuongHieu> existingList) {
		return existingList.stream().anyMatch(ct -> ten.equals(ct.getTenThuongHieu()));
	}
}
