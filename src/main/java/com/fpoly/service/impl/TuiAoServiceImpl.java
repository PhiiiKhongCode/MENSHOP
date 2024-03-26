package com.fpoly.service.impl;

import com.fpoly.entity.TuiAo;
import com.fpoly.repository.TuiAoRepository;
import com.fpoly.service.TuiAoService;
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
public class TuiAoServiceImpl implements TuiAoService, ValidateService<TuiAo> {
	private TuiAoRepository tuiAoRepository;
	private static Logger logger = LoggerFactory.getLogger(TuiAoServiceImpl.class);
	
	@Override
	public Page<TuiAo> selectAllTuiAoExist(Pageable pageable) {
		logger.info("Select all tui ao exist");
		return tuiAoRepository.selectAllTuiAoExist(pageable);
	}
	
	@Override
	public <S extends TuiAo> S save(S entity) {
		entity.setDaXoa(false);
		return tuiAoRepository.save(entity);
	}

	@Override
	public List<TuiAo> selectAllTuiAoExist() {
		logger.info("Select all tui ao exist");
		return tuiAoRepository.selectAllTuiAoExist();
	}

	@Override
	public Optional<TuiAo> findById(Long id) {
		return tuiAoRepository.findById(id);
	}
	
	@Override
	public Page<TuiAo> getTuiAoExistByName(String tenTuiAo, Pageable pageable) {
		return tuiAoRepository.getTuiAoExistByName(tenTuiAo, pageable);
	}

	@Override
	public void delete(TuiAo entity) {
		entity.setDaXoa(true);
		tuiAoRepository.save(entity);
	}


	@Override
	public boolean checkTrung(String ten, List<TuiAo> existingList) {
		return existingList.stream().anyMatch(ct -> ten.equals(ct.getTenTuiAo()));
	}
}
