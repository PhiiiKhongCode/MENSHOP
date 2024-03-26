package com.fpoly.service;

import com.fpoly.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ThuongHieuService {

	List<ThuongHieu> selectAllThuongHieuExist();

	Optional<ThuongHieu> findById(Long id);

	<S extends ThuongHieu> S save(S entity);

	Page<ThuongHieu> selectAllThuongHieuExist(Pageable page);

	Page<ThuongHieu> getThuongHieuExistByName(String tenThuongHieu, Pageable page);

	void delete(ThuongHieu entity);

}
