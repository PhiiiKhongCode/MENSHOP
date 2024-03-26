package com.fpoly.service.impl;

import com.fpoly.entity.CucAo;
import com.fpoly.entity.KichCo;
import com.fpoly.entity.VanAo;
import com.fpoly.repository.CucAoRepository;
import com.fpoly.repository.KichCoRepository;
import com.fpoly.repository.VanAoRepository;
import com.fpoly.service.KichCoService;
import com.fpoly.service.ValidateService;
import com.fpoly.service.VanAoService;
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
public class VanAoServiceImpl implements VanAoService, ValidateService<VanAo> {
	private VanAoRepository vanAoRepository;
	private static Logger logger = LoggerFactory.getLogger(VanAoServiceImpl.class);

	@Override
	public Page<VanAo> selectAllVanAoExist(Pageable pageable) {
		logger.info("Select all van ao exist");
		return vanAoRepository.selectAllVanAoExist(pageable);
	}

	@Override
	public List<VanAo> selectAllVanAoExist() {
		logger.info("Select all van ao exist");
		return vanAoRepository.selectAllVanAoExist();
	}

	@Override
	public <S extends VanAo> S save(S entity) {
		entity.setDaXoa(false);
		return vanAoRepository.save(entity);
	}

	@Override
	public Optional<VanAo> findById(Long id) {
		return vanAoRepository.findById(id);
	}

	@Override
	public Page<VanAo> getVanAoExistByName(String tenVanAo, Pageable pageable) {
		return vanAoRepository.getVanAoExistByName(tenVanAo, pageable);
	}

	@Override
	public void delete(VanAo entity) {
		entity.setDaXoa(true);
		vanAoRepository.save(entity);
	}


	@Override
	public boolean checkTrung(String ten, List<VanAo> existingList) {
		return existingList.stream().anyMatch(ct -> ten.equals(ct.getTenVanAo()));
	}
}
