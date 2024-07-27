package org.sfx.domain.VO;

import lombok.Builder;
import lombok.Data;
import org.sfx.domain.entity.Content;
import org.sfx.domain.entity.Workflow;

import java.util.List;

@Data
@Builder
public class WorkflowPageVO {
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer total;
    private List<Content> content;
}
