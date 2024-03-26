package com.fpoly.service.impl;

import com.fpoly.entity.CoAo;
import com.fpoly.repository.CoAoRepository;
import com.fpoly.service.CoAoService;
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
public class CoAoServiceImpl implements CoAoService, ValidateService<CoAo> {
	private CoAoRepository coAoRepository;
	private static Logger logger = LoggerFactory.getLogger(CoAoServiceImpl.class);
	
	@Override
	public Page<CoAo> selectAllCoAoExist(Pageable pageable) {
		logger.info("Select all co ao exist");
		return coAoRepository.selectAllCoAoExist(pageable);
	}
	
	@Override
	public <S extends CoAo> S save(S entity) {
		entity.setDaXoa(false);
		return coAoRepository.save(entity);
	}

	@Override
	public List<CoAo> selectAllCoAoExist() {
		logger.info("Select all co ao exist");
		return coAoRepository.selectAllCoAoExist();
	}

	@Override
	public Optional<CoAo> findById(Long id) {
		return coAoRepository.findById(id);
	}
	
	@Override
	public Page<CoAo> getCoAoExistByName(String tenCoAo, Pageable pageable) {
		return coAoRepository.getCoAoExistByName(tenCoAo, pageable);
	}

	@Override
	public void delete(CoAo entity) {
		entity.setDaXoa(true);
		coAoRepository.save(entity);
	}


	@Override
	public boolean checkTrung(String ten, List<CoAo> existingList) {
		return existingList.stream().anyMatch(ct -> ten.equals(ct.getTenCoAo()));
	}
}
