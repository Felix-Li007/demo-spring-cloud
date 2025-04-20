package demo.sport.zones.schedu;

import com.hrms.frame.utils.DateTimeUtils;
import demo.sport.zones.service.IGatheInfoService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class SportZoneScheduTask
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SportZoneScheduTask.class);

    @Resource
    private IGatheInfoService gatheInfoService;

    /**
     * 上传指定榜单的数据
     */
    @XxlJob("gatherRankItemHandler")
    public void gatherRankItem()
    {
        String strRankTime = XxlJobHelper.getJobParam();
        LOGGER.info("启动榜单数据上传任务：RANK_TIME={}", strRankTime);
        LocalDateTime ldtRankTime = Optional.ofNullable(DateTimeUtils.parseDateByDefault(strRankTime)).map(DateTimeUtils::date2LocalDateTime).orElse(null);
        gatheInfoService.gatheRankTask(ldtRankTime);
        LOGGER.info("完成榜单数据上报任务");
    }

}
