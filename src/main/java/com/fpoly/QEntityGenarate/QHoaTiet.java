package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.fpoly.entity.HoaTiet;
import com.fpoly.entity.SanPhamChiTiet;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.Path;

import java.util.Date;

/**
 * QHoaTiet is a Querydsl query type for HoaTiet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHoaTiet extends EntityPathBase<HoaTiet> {

    private static final long serialVersionUID = -157376947L;

    public static final QHoaTiet hoaTiet = new QHoaTiet("hoaTiet");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath daXoa = createBoolean("daXoa");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final ListPath<SanPhamChiTiet, QSanPhamChiTiet> sanPhamChiTiets = this.<SanPhamChiTiet, QSanPhamChiTiet>createList("sanPhamChiTiets", SanPhamChiTiet.class, QSanPhamChiTiet.class, PathInits.DIRECT2);

    public final StringPath tenHoaTiet = createString("tenHoaTiet");

    public QHoaTiet(String variable){
        super(HoaTiet.class, forVariable(variable));
    }

    public QHoaTiet(Path<? extends HoaTiet> path){
        super(path.getType(),
                path.getMetadata());
    }

    public QHoaTiet(PathMetadata metadata){
        super(HoaTiet.class, metadata);
    }
}
