package com.fpoly.service.impl;

import com.fpoly.entity.HoaTiet;
import com.fpoly.repository.HoaTietRepository;
import com.fpoly.service.HoaTietService;
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
public class HoaTietServiceImpl implements HoaTietService, ValidateService<HoaTiet> {
	private HoaTietRepository hoaTietRepository;
	private static Logger logger = LoggerFactory.getLogger(HoaTietServiceImpl.class);
	
	@Override
	public Page<HoaTiet> selectAllHoaTietExist(Pageable pageable) {
		logger.info("Select all hoa tiet exist");
		return hoaTietRepository.findAllHoaTietExist(pageable);
	}
	
	@Override
	public <S extends HoaTiet> S save(S entity) {
		entity.setDaXoa(false);
		return  hoaTietRepository.save(entity);
	}

	@Override
	public List<HoaTiet> selectAllHoaTietExist() {
		logger.info("Select all hoa tiet exist");
		return hoaTietRepository.findAllHoaTietExist();
	}

	@Override
	public Optional<HoaTiet> findById(Long id) {
		return hoaTietRepository.findById(id);
	}
	
	@Override
	public Page<HoaTiet> getHoaTietExistByName(String tenHoaTiet, Pageable pageable) {
		return hoaTietRepository.getHoaTietExistByName(tenHoaTiet, pageable);
	}

	@Override
	public void delete(HoaTiet entity) {
		entity.setDaXoa(true);
		hoaTietRepository.save(entity);
	}


	@Override
	public boolean checkTrung(String ten, List<HoaTiet> existingList) {
		return existingList.stream().anyMatch(ct -> ten.equals(ct.getTenHoaTiet()));
	}
}
