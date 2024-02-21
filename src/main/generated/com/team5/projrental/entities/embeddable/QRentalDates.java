package com.team5.projrental.entities.embeddable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRentalDates is a Querydsl query type for RentalDates
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRentalDates extends BeanPath<RentalDates> {

    private static final long serialVersionUID = -2079318949L;

    public static final QRentalDates rentalDates = new QRentalDates("rentalDates");

    public final DatePath<java.time.LocalDate> rentalEndDate = createDate("rentalEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> rentalStartDate = createDate("rentalStartDate", java.time.LocalDate.class);

    public QRentalDates(String variable) {
        super(RentalDates.class, forVariable(variable));
    }

    public QRentalDates(Path<? extends RentalDates> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRentalDates(PathMetadata metadata) {
        super(RentalDates.class, metadata);
    }

}

