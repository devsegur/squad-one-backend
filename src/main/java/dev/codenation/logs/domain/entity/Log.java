package dev.codenation.logs.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
<<<<<<< HEAD

import dev.codenation.logs.domain.vo.LogDetailVO;
import dev.codenation.logs.domain.vo.OriginVO;
=======
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
>>>>>>> 7efef6dc928ad80860cf991796c3de8583233c9d
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    private Integer hash;

    @NotNull
    @Embedded
    private LogDetailVO logDetailVO;

    @NotNull
    @Embedded
    private OriginVO originVO;

    @NotNull
    @Builder.Default
    private Boolean archived = false;

    @ManyToOne
    @JoinColumn(name = "archived_by")
    private User archivedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime archivedAt;

    @ManyToOne
    @JoinColumn(name = "reported_by")
    private User reportedBy;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public void setHash(){
        this.hash = this.logDetail.getMessage().hashCode();
    }
}
