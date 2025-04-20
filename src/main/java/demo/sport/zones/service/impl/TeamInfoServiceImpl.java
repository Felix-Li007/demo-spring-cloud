package demo.sport.zones.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hrms.account.domain.team.*;
import com.hrms.core.domian.PageResponse;
import demo.sport.zones.call.ITeamServiceCaller;
import demo.sport.zones.call.ResultOutHandler;
import demo.sport.zones.service.ITeamInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeamInfoServiceImpl implements ITeamInfoService
{
    private final static Logger LOGGER = LoggerFactory.getLogger(TeamInfoServiceImpl.class);
    @Resource
    private ITeamServiceCaller teamServiceCaller;


    public TeamAccntInfoOUT queryAccntDetail(String accntNo)
    {
        TeamAccntInfoOUT teamAccntInfoOUT = ResultOutHandler.handler(teamServiceCaller::queryAccntDetail, accntNo, "查询用户帐号数据");
        LOGGER.info("查询用户帐号数据：ACCNT_NO={}, ACCNT_INFO={}", accntNo, JSONObject.toJSONString(teamAccntInfoOUT));
        return teamAccntInfoOUT;
    }


    public AccntTeamInfoOUT queryTeamDetail(String accntNo, String teamNo)
    {
        WhereTeamBaseIN whereTeamBaseIN = new WhereTeamBaseIN();
        whereTeamBaseIN.setAccntNo(accntNo);
        whereTeamBaseIN.setTeamNo(teamNo);
        AccntTeamInfoOUT accntTeamInfoOut = ResultOutHandler.handler(teamServiceCaller::queryTeamDetail, whereTeamBaseIN, "查询用户团队数据");
        return accntTeamInfoOut;
    }


    public PageResponse<TeamCrewInfoOUT> queryCrewList(WhereTeamPageIN whereTeamPageIN)
    {
        PageResponse<TeamCrewInfoOUT> teamCrewInfoOuts = ResultOutHandler.handler(teamServiceCaller::queryCrewList, whereTeamPageIN, "查询用户团队数据");
        return teamCrewInfoOuts;
    }
}
