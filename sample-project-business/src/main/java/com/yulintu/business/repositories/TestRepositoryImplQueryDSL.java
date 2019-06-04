package com.yulintu.business.repositories;

import com.google.common.base.Strings;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yulintu.business.entities.Jt;
import com.yulintu.business.entities.QJt;
import com.yulintu.thematic.data.Provider;
import com.yulintu.thematic.data.ProviderType;
import com.yulintu.thematic.data.models.PagingQuery;
import com.yulintu.thematic.data.querydsl.EntityContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("prototype")
@ProviderType("querydsl")
public class TestRepositoryImplQueryDSL extends TestRepositoryImpl {

    private QJt jtQ = QJt.jt;


    public TestRepositoryImplQueryDSL(Provider provider) {
        super(provider);
    }

    @Override
    public int count() {
        return (int) getJPAQueryFactory()
                .from(jtQ)
                .fetchCount();
    }



    @Override
    public Jt get(String bh) {
        return getSQLQueryFactory(null)
                .selectFrom(QJt.jt)
                .where(QJt.jt.bh.eq(bh))
                .fetchOne();
    }

    @Override
    public List<Jt> paging(PagingQuery paging) {

        JPAQueryFactory factory = getJPAQueryFactory();

        // 获取到查询对象的 context，以便后面根据字段名称找到对应的字段表达式，从而实现排序功能。
        EntityContext context = EntityContext.from(jtQ.getClass());

        String orderField = paging.getOrderField();
        orderField = Strings.isNullOrEmpty(orderField) ? jtQ.bh.getMetadata().getName() : orderField;
        ComparableExpressionBase orderExp = (ComparableExpressionBase) context.getExpression(orderField);
        OrderSpecifier orderSpecifier = paging.getOrder() == Order.ASC ? orderExp.asc() : orderExp.desc();

        List<BooleanExpression> where = new ArrayList<>();

        String key = paging.getKey();
        if (!Strings.isNullOrEmpty(key)) {
            where.add(jtQ.bh.contains(key).or(jtQ.mc.contains(key)));
        }

        JPAQuery<Jt> qc = factory
                .selectFrom(jtQ)
                .where(where.toArray(new Predicate[0]))
                .orderBy(orderSpecifier)
                .offset(paging.getStartIndex())
                .limit(paging.getSize());

        List<Jt> result = qc.fetch();
        return result;
    }

    @Override
    public boolean anyBhExceptById(String id, String bh) {
        return super.any(jtQ, jtQ.bh.eq(bh).and(jtQ.id.ne(id)));
    }

    @Override
    public int deleteAll() {
        return (int) getSQLQueryFactory(null)
                .delete(jtQ)
                .execute();
    }
}
