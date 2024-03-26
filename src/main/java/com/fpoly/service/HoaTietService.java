package com.fpoly.service;

import com.fpoly.entity.HoaTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface HoaTietService {

	Page<HoaTiet> selectAllHoaTietExist(Pageable pageable);

	Optional<HoaTiet> findById(Long id);

	<S extends HoaTiet> S save(S entity);

	List<HoaTiet> selectAllHoaTietExist();

	Page<HoaTiet> getHoaTietExistByName(String tenHoaTiet, Pageable pageable);

	void delete(HoaTiet entity);

}
