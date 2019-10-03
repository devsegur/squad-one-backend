package dev.codenation.logs.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.codenation.logs.domain.vo.LogDetail;
import dev.codenation.logs.domain.vo.Origin;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @NotNull
    private Integer hash;

    @NotNull
    @Embedded
    private LogDetail logDetail;

    @NotNull
    @Embedded
    private Origin origin;

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

    public void setLogDetail(LogDetail detail) {
        this.logDetail = detail;
        setHash();
    }

    public void setHash() {
        if (logDetail != null && !logDetail.getMessage().isEmpty()) {
            this.hash = logDetail.getMessage().hashCode();
        }
    }

}
