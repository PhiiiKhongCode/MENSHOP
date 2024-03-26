package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.ThuongHieu;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.Path;

import java.util.Date;

/**
 * QThuongHieu is a Querydsl query type for ThuongHieu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThuongHieu extends EntityPathBase<ThuongHieu> {

    private static final long serialVersionUID = -246473869L;

    public static final QThuongHieu thuongHieu = new QThuongHieu("thuongHieu");

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

    public final ListPath<SanPham, QSanPham> sanPhams = this.<SanPham, QSanPham>createList("sanPhams", SanPham.class, QSanPham.class, PathInits.DIRECT2);

    public final StringPath tenThuongHieu = createString("tenThuongHieu");

    public QThuongHieu(String variable){
        super(ThuongHieu.class, forVariable(variable));
    }

    public QThuongHieu(Path<? extends ThuongHieu> path){
        super(path.getType(),
                path.getMetadata());
    }

    public QThuongHieu(PathMetadata metadata){
        super(ThuongHieu.class, metadata);
    }
}
