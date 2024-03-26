package com.fpoly.QEntityGenarate;

import com.fpoly.entity.CoAo;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.entity.TuiAo;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTuiAo is a Querydsl query type for TuiAo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTuiAo extends EntityPathBase<TuiAo> {

    private static final long serialVersionUID = 4097721625191813922L;

    public static final QTuiAo tuiAo = new QTuiAo("tuiAo");

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

    public final StringPath tenTuiAo = createString("tenTuiAo");

    public QTuiAo(String variable) {
        super(TuiAo.class, forVariable(variable));
    }

    public QTuiAo(Path<? extends TuiAo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTuiAo(PathMetadata metadata) {
        super(TuiAo.class, metadata);
    }

}

