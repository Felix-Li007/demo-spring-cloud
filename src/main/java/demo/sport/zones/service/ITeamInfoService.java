package demo.sport.zones.service;


import com.hrms.account.domain.team.AccntTeamInfoOUT;
import com.hrms.account.domain.team.TeamAccntInfoOUT;
import com.hrms.account.domain.team.TeamCrewInfoOUT;
import com.hrms.account.domain.team.WhereTeamPageIN;
import com.hrms.core.domian.PageResponse;

public interface ITeamInfoService
{

    TeamAccntInfoOUT queryAccntDetail(String accntNo);

    AccntTeamInfoOUT queryTeamDetail(String accntNo, String teamNo);

    PageResponse<TeamCrewInfoOUT> queryCrewList(WhereTeamPageIN whereTeamPageReq);
}
