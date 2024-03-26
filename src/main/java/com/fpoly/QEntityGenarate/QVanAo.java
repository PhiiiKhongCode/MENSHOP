package com.fpoly.QEntityGenarate;

import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.entity.VanAo;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QVanAo is a Querydsl query type for VanAo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVanAo extends EntityPathBase<VanAo> {

    private static final long serialVersionUID = 7851207593253580622L;

    public static final QVanAo vanAo = new QVanAo("vanAo");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath daXoa = createBoolean("daXoa");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final ListPath<SanPhamChiTiet, QSanPhamChiTiet> sanPhamChiTiets = this.<SanPhamChiTiet, QSanPhamChiTiet>createList("sanPhamChiTiets", SanPhamChiTiet.class, QSanPhamChiTiet.class, PathInits.DIRECT2);

    public final StringPath tenVanAo = createString("tenVanAo");

    public QVanAo(String variable) {
        super(VanAo.class, forVariable(variable));
    }

    public QVanAo(Path<? extends VanAo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVanAo(PathMetadata metadata) {
        super(VanAo.class, metadata);
    }

}

