package team.flow.upload.file.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFixedFileExtension is a Querydsl query type for FixedFileExtension
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFixedFileExtension extends EntityPathBase<FixedFileExtension> {

    private static final long serialVersionUID = -1992458331L;

    public static final QFixedFileExtension fixedFileExtension = new QFixedFileExtension("fixedFileExtension");

    public final EnumPath<team.flow.upload.file.domain.constants.FileExtensionType> fileExtensionType = createEnum("fileExtensionType", team.flow.upload.file.domain.constants.FileExtensionType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isRestricted = createBoolean("isRestricted");

    public QFixedFileExtension(String variable) {
        super(FixedFileExtension.class, forVariable(variable));
    }

    public QFixedFileExtension(Path<? extends FixedFileExtension> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFixedFileExtension(PathMetadata metadata) {
        super(FixedFileExtension.class, metadata);
    }

}

