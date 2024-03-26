package com.fpoly.service.impl;

import com.fpoly.entity.CucAo;
import com.fpoly.repository.CucAoRepository;
import com.fpoly.service.CucAoService;
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
public class CucAoServiceImpl implements CucAoService, ValidateService<CucAo> {
	private CucAoRepository cucAoRepository;
	private static Logger logger = LoggerFactory.getLogger(CucAoServiceImpl.class);
	
	@Override
	public Page<CucAo> selectAllCucAoExist(Pageable pageable) {
		logger.info("Select all cuc ao exist");
		return cucAoRepository.selectAllCucAoExist(pageable);
	}
	
	@Override
	public List<CucAo> selectAllCucAoExist() {
		logger.info("Select all cuc ao exist");
		return cucAoRepository.selectAllCucAoExist();
	}
	
	@Override
	public <S extends CucAo> S save(S entity) {
		entity.setDaXoa(false);
		return cucAoRepository.save(entity);
	}

	@Override
	public Optional<CucAo> findById(Long id) {
		return cucAoRepository.findById(id);
	}
	
	@Override
	public Page<CucAo> getCucAoExistByName(String tenCucAo, Pageable pageable) {
		return cucAoRepository.getCucAoExistByName(tenCucAo, pageable);
	}

	@Override
	public void delete(CucAo entity) {
		entity.setDaXoa(true);
		cucAoRepository.save(entity);
	}


	@Override
	public boolean checkTrung(String ten, List<CucAo> existingList) {
		return existingList.stream().anyMatch(ct -> ten.equals(ct.getTenCucAo()));
	}
}
