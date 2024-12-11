package io.demo.purchase.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookingEntity is a Querydsl query type for BookingEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookingEntity extends EntityPathBase<BookingEntity> {

    private static final long serialVersionUID = 2007901887L;

    public static final QBookingEntity bookingEntity = new QBookingEntity("bookingEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> slotId = createNumber("slotId", Long.class);

    public final EnumPath<io.demo.purchase.support.BookingStatus> status = createEnum("status", io.demo.purchase.support.BookingStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBookingEntity(String variable) {
        super(BookingEntity.class, forVariable(variable));
    }

    public QBookingEntity(Path<? extends BookingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookingEntity(PathMetadata metadata) {
        super(BookingEntity.class, metadata);
    }

}

