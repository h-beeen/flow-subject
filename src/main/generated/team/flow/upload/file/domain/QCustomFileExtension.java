package team.flow.upload.file.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomFileExtension is a Querydsl query type for CustomFileExtension
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomFileExtension extends EntityPathBase<CustomFileExtension> {

    private static final long serialVersionUID = 1575911516L;

    public static final QCustomFileExtension customFileExtension = new QCustomFileExtension("customFileExtension");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCustomFileExtension(String variable) {
        super(CustomFileExtension.class, forVariable(variable));
    }

    public QCustomFileExtension(Path<? extends CustomFileExtension> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomFileExtension(PathMetadata metadata) {
        super(CustomFileExtension.class, metadata);
    }

}

