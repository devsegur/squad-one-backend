package dev.codenation.logs.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.codenation.logs.domain.vo.LogDetailVO;
import dev.codenation.logs.domain.vo.OriginVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(max = 100)
    private String hash;

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
}
