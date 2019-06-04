package com.yulintu.business.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTbDatJtcy is a Querydsl query type for TbDatJtcy
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QJtcy extends com.querydsl.sql.RelationalPathBase<Jtcy> {

    private static final long serialVersionUID = -1347855515;

    public static final QJtcy jtcy = new QJtcy("tb_dat_jtcy");

    public final StringPath bh = createString("bh");

    public final StringPath bz = createString("bz");

    public final StringPath id = createString("id");

    public final StringPath jtid = createString("jtid");

    public final StringPath mc = createString("mc");

    public final NumberPath<Integer> nl = createNumber("nl", Integer.class);

    public final EnumPath<eXb> xb = createEnum("xb", eXb.class);

    public QJtcy(String variable) {
        super(Jtcy.class, forVariable(variable), "public", "tb_dat_jtcy");
        addMetadata();
    }

    public QJtcy(String variable, String schema, String table) {
        super(Jtcy.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QJtcy(String variable, String schema) {
        super(Jtcy.class, forVariable(variable), schema, "tb_dat_jtcy");
        addMetadata();
    }

    public QJtcy(Path<? extends Jtcy> path) {
        super(path.getType(), path.getMetadata(), "public", "tb_dat_jtcy");
        addMetadata();
    }

    public QJtcy(PathMetadata metadata) {
        super(Jtcy.class, metadata, "public", "tb_dat_jtcy");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(bh, ColumnMetadata.named("bh").withIndex(4).ofType(Types.VARCHAR).withSize(50));
        addMetadata(bz, ColumnMetadata.named("bz").withIndex(7).ofType(Types.VARCHAR).withSize(2147483647));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.VARCHAR).withSize(38).notNull());
        addMetadata(jtid, ColumnMetadata.named("jtid").withIndex(2).ofType(Types.VARCHAR).withSize(38).notNull());
        addMetadata(mc, ColumnMetadata.named("mc").withIndex(3).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(nl, ColumnMetadata.named("nl").withIndex(6).ofType(Types.INTEGER).withSize(10));
        addMetadata(xb, ColumnMetadata.named("xb").withIndex(5).ofType(Types.SMALLINT).withSize(5));
    }

}

