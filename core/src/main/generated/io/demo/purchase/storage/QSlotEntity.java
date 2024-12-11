package io.demo.purchase.storage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSlotEntity is a Querydsl query type for SlotEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSlotEntity extends EntityPathBase<SlotEntity> {

    private static final long serialVersionUID = -724903618L;

    public static final QSlotEntity slotEntity = new QSlotEntity("slotEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> coachId = createNumber("coachId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final DateTimePath<java.time.LocalDateTime> eventDatetime = createDateTime("eventDatetime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> total = createNumber("total", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final EnumPath<io.demo.purchase.support.WorkoutType> workoutType = createEnum("workoutType", io.demo.purchase.support.WorkoutType.class);

    public QSlotEntity(String variable) {
        super(SlotEntity.class, forVariable(variable));
    }

    public QSlotEntity(Path<? extends SlotEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSlotEntity(PathMetadata metadata) {
        super(SlotEntity.class, metadata);
    }

}

