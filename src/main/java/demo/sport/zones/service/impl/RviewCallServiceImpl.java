package demo.sport.zones.service.impl;

import com.hrms.core.domian.PageResponse;
import demo.sport.zones.call.IReviewServiceCaller;
import demo.sport.zones.call.ResultOutHandler;
import demo.sport.zones.service.IRviewCallService;
import com.psdn.review.domain.rank.ReviewRankItemOUT;
import com.psdn.review.domain.rank.WhereRankPageIN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RefreshScope
@Service
public class RviewCallServiceImpl implements IRviewCallService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RviewCallServiceImpl.class);

    @Resource
    private IReviewServiceCaller reviewServiceCaller;


    public PageResponse<ReviewRankItemOUT> queryRankItem(String rankCode, String rangCode, String metriCode, String rangValue, LocalDateTime rankTime, int pageNum, int pageSize)
    {
        WhereRankPageIN whereRankPageIN = new WhereRankPageIN();
        whereRankPageIN.setRankCode(rankCode);
        whereRankPageIN.setRankTime(rankTime);
        whereRankPageIN.setRangCode(rangCode);
        whereRankPageIN.setMetriCode(metriCode);
        whereRankPageIN.setRangValue(rangValue);
        whereRankPageIN.setPageSize(pageSize);
        whereRankPageIN.setPageNum(pageNum);
        PageResponse<ReviewRankItemOUT> pageResponse = ResultOutHandler.handler(reviewServiceCaller::queryRankData, whereRankPageIN, "查询榜单排行数据");
        return pageResponse;
    }


}
