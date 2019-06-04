package com.yulintu.business.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbDatJt is a Querydsl query type for TbDatJt
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QJt extends com.querydsl.sql.RelationalPathBase<Jt> {

    private static final long serialVersionUID = 1178484399;

    public static final QJt jt = new QJt("tb_dat_jt");

    public final StringPath bh = createString("bh");

    public final StringPath bz = createString("bz");

    public final StringPath dz = createString("dz");

    public final StringPath id = createString("id");

    public final StringPath mc = createString("mc");

    public QJt(String variable) {
        super(Jt.class, forVariable(variable), "public", "tb_dat_jt");
        addMetadata();
    }

    public QJt(String variable, String schema, String table) {
        super(Jt.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QJt(String variable, String schema) {
        super(Jt.class, forVariable(variable), schema, "tb_dat_jt");
        addMetadata();
    }

    public QJt(Path<? extends Jt> path) {
        super(path.getType(), path.getMetadata(), "public", "tb_dat_jt");
        addMetadata();
    }

    public QJt(PathMetadata metadata) {
        super(Jt.class, metadata, "public", "tb_dat_jt");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(bh, ColumnMetadata.named("bh").withIndex(3).ofType(Types.VARCHAR).withSize(50));
        addMetadata(bz, ColumnMetadata.named("bz").withIndex(5).ofType(Types.VARCHAR).withSize(2147483647));
        addMetadata(dz, ColumnMetadata.named("dz").withIndex(4).ofType(Types.VARCHAR).withSize(200));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.VARCHAR).withSize(38).notNull());
        addMetadata(mc, ColumnMetadata.named("mc").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
    }

}

