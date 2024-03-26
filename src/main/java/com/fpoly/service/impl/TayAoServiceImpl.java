package com.fpoly.service.impl;

import com.fpoly.entity.TayAo;
import com.fpoly.repository.TayAoRepository;
import com.fpoly.service.TayAoService;
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
public class TayAoServiceImpl implements TayAoService, ValidateService<TayAo> {
	private TayAoRepository tayAoRepository;
	private static Logger logger = LoggerFactory.getLogger(TayAoServiceImpl.class);
	
	@Override
	public Page<TayAo> selectAllTayAoExist(Pageable pageable) {
		logger.info("Select all tay ao exist");
		return tayAoRepository.selectAllTayAoExist(pageable);
	}
	
	@Override
	public <S extends TayAo> S save(S entity) {
		entity.setDaXoa(false);
		return tayAoRepository.save(entity);
	}

	@Override
	public List<TayAo> selectAllTayAoExist() {
		logger.info("Select all tay ao exist");
		return tayAoRepository.selectAllTayAoExist();
	}

	@Override
	public Optional<TayAo> findById(Long id) {
		return tayAoRepository.findById(id);
	}
	
	@Override
	public Page<TayAo> getTayAoExistByName(String tenTayAo, Pageable pageable) {
		return tayAoRepository.getTayAoExistByName(tenTayAo, pageable);
	}

	@Override
	public void delete(TayAo entity) {
		entity.setDaXoa(true);
		tayAoRepository.save(entity);
	}


	@Override
	public boolean checkTrung(String ten, List<TayAo> existingList) {
		return existingList.stream().anyMatch(ct -> ten.equals(ct.getTenTayAo()));
	}
}
