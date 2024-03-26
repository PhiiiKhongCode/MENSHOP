package com.fpoly.QEntityGenarate;

import com.fpoly.entity.CucAo;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.entity.VanAo;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCucAo is a Querydsl query type for CucAo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCucAo extends EntityPathBase<CucAo> {

    private static final long serialVersionUID = -93253580622L;

    public static final QCucAo cucAo = new QCucAo("cucAo");

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

    public final StringPath tenCucAo = createString("tenCucAo");

    public QCucAo(String variable) {
        super(CucAo.class, forVariable(variable));
    }

    public QCucAo(Path<? extends CucAo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCucAo(PathMetadata metadata) {
        super(CucAo.class, metadata);
    }

}

