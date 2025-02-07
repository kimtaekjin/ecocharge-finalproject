package com.example.demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 133795676L;

    public static final QUser user = new QUser("user");

    public final StringPath accessToken = createString("accessToken");

    public final ListPath<Board, QBoard> boardList = this.<Board, QBoard>createList("boardList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath identify = createString("identify");

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final EnumPath<User.LoginMethod> loginMethod = createEnum("loginMethod", User.LoginMethod.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImg = createString("profileImg");

    public final ListPath<Qna, QQna> qnaList = this.<Qna, QQna>createList("qnaList", Qna.class, QQna.class, PathInits.DIRECT2);

    public final StringPath refreshToken = createString("refreshToken");

    public final DateTimePath<java.util.Date> refreshTokenExpiryDate = createDateTime("refreshTokenExpiryDate", java.util.Date.class);

    public final ListPath<Reservation, QReservation> reservationList = this.<Reservation, QReservation>createList("reservationList", Reservation.class, QReservation.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

