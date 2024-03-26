package com.fpoly.QEntityGenarate;

import com.fpoly.entity.CoAo;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.entity.TayAo;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTayAo is a Querydsl query type for TayAo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTayAo extends EntityPathBase<TayAo> {

    private static final long serialVersionUID = 4097721625191813922L;

    public static final QTayAo tayAo = new QTayAo("tayAo");

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

    public final StringPath tenTayAo = createString("tenTayAo");

    public QTayAo(String variable) {
        super(TayAo.class, forVariable(variable));
    }

    public QTayAo(Path<? extends TayAo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTayAo(PathMetadata metadata) {
        super(TayAo.class, metadata);
    }

}

