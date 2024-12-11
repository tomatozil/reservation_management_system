package io.demo.purchase.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStockEntity is a Querydsl query type for StockEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStockEntity extends EntityPathBase<StockEntity> {

    private static final long serialVersionUID = 349061244L;

    public static final QStockEntity stockEntity = new QStockEntity("stockEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> slotId = createNumber("slotId", Long.class);

    public final NumberPath<Long> stock = createNumber("stock", Long.class);

    public final NumberPath<Long> total = createNumber("total", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QStockEntity(String variable) {
        super(StockEntity.class, forVariable(variable));
    }

    public QStockEntity(Path<? extends StockEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStockEntity(PathMetadata metadata) {
        super(StockEntity.class, metadata);
    }

}

