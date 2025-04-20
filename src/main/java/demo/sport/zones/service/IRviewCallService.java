package demo.sport.zones.service;


import com.hrms.core.domian.PageResponse;
import com.psdn.review.domain.rank.ReviewRankItemOUT;

import java.time.LocalDateTime;

public interface IRviewCallService
{
    PageResponse<ReviewRankItemOUT> queryRankItem(String rankCode, String rangCode, String metriCode, String rangValue, LocalDateTime rankTime, int pageNum, int pageSize);
}
