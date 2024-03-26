package com.fpoly.QEntityGenarate;

import com.fpoly.entity.CoAo;
import com.fpoly.entity.SanPhamChiTiet;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCoAo is a Querydsl query type for CoAo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoAo extends EntityPathBase<CoAo> {

    private static final long serialVersionUID = 4097721625191813922L;

    public static final QCoAo coAo = new QCoAo("coAo");

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

    public final StringPath tenCoAo = createString("tenCoAo");

    public QCoAo(String variable) {
        super(CoAo.class, forVariable(variable));
    }

    public QCoAo(Path<? extends CoAo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoAo(PathMetadata metadata) {
        super(CoAo.class, metadata);
    }

}

