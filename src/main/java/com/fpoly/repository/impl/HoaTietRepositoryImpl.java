package com.fpoly.repository.impl;

import com.fpoly.QEntityGenarate.QHoaTiet;
import com.fpoly.entity.HoaTiet;
import com.fpoly.entity.ThuongHieu;
import com.fpoly.repository.HoaTietRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class HoaTietRepositoryImpl {
    private EntityManager entityManager;

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QHoaTiet qHoaTiet = QHoaTiet.hoaTiet;

    public Page<HoaTiet> findAllHoaTietExist(int pageNumber, int pageSize) {
        int offSet = (pageNumber-1) * pageSize;

        List<HoaTiet> hoaTiets = queryFactory
                .selectFrom(qHoaTiet)
                .orderBy(qHoaTiet.ngayTao.desc())
                .offset(offSet)
                .limit(pageSize)
                .fetch();
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        int totalElements = (int) queryFactory.selectFrom(qHoaTiet).stream().count();
        return new PageImpl<HoaTiet>(hoaTiets, pageable, totalElements);
    }

    public List<HoaTiet> findAllHoaTietExist() {
        List<HoaTiet> hoaTiets = queryFactory
                .selectFrom(qHoaTiet)
                .orderBy(qHoaTiet.ngayTao.desc())
                .fetch();
        return hoaTiets;
    }

    public Page<HoaTiet> getHoaTietExistByName(String tenThuongHieu, Pageable pageable) {
//        int offSet = (pageNumber-1) * pageSize;
//
//        List<HoaTiet> hoaTiets = queryFactory
//                .selectFrom(qHoaTiet)
//                .orderBy(qHoaTiet.ngayTao.desc())
//                .offset(offSet)
//                .limit(pageSize)
//                .fetch();
//        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
//        int totalElements = (int) queryFactory.selectFrom(qHoaTiet).stream().count();
        return null;
    }

}
