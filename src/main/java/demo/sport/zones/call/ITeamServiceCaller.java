package demo.sport.zones.call;

import com.hrms.account.api.team.ITeamServiceApi;
import com.hrms.account.domain.team.*;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@FeignClient(value = "${service.call.account.team}", path = "/account/team/", contextId = "account-team", fallbackFactory = ITeamServiceCaller.TeamServiceCallerFallbackFactory.class)
public interface ITeamServiceCaller extends ITeamServiceApi
{

    @Component
    class TeamServiceCallerFallbackFactory implements FallbackFactory<ITeamServiceCaller>
    {

        private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceCallerFallbackFactory.class);

        @Override
        public ITeamServiceCaller create(Throwable e)
        {
            return new ITeamServiceCaller()
            {

                /**
                 * 查看用户群组信息
                 *
                 * @param whereTeamBaseIN
                 */
                @Override
                public ResultOut<AccntTeamInfoOUT> queryTeamDetail(WhereTeamBaseIN whereTeamBaseIN)
                {
                    return null;
                }

                /**
                 * @param accntNo
                 * @return
                 */
                @Override
                public ResultOut<TeamAccntInfoOUT> queryAccntDetail(String accntNo)
                {
                    return null;
                }

                /**
                 * 根据用户编码查询团队信息
                 *
                 * @param whereTeamPageIN
                 */
                @Override
                public ResultOut<PageResponse<TeamCrewInfoOUT>> queryCrewList(WhereTeamPageIN whereTeamPageIN)
                {
                    return null;
                }

                /**
                 * @param whereEnterPageIN
                 * @return
                 */
                @Override
                public ResultOut<PageResponse<TeamAccntInfoOUT>> queryEmplyList(WhereEnterPageIN whereEnterPageIN)
                {
                    return null;
                }


                /**
                 * 查看群组提醒消息
                 *
                 * @param whereRmindBaseIN
                 */
                @Override
                public ResultOut<TeamRemindInfoOUT> queryRemindDetail(WhereRmindBaseIN whereRmindBaseIN)
                {
                    return null;
                }

                /**
                 * 发送团队提醒消息
                 *
                 * @param whereRmindBaseIN
                 */
                @Override
                public ResultOut<TeamRemindInfoOUT> sendRemindText(WhereRmindBaseIN whereRmindBaseIN)
                {
                    return null;
                }


                /**
                 * 查看用户企业信息
                 *
                 * @param whereEnterBaseIN
                 */
                @Override
                public ResultOut<AccntEnterInfoOUT> queryEnterDetail(WhereEnterBaseIN whereEnterBaseIN)
                {
                    return null;
                }

                /**
                 * 查看部门明细数据
                 *
                 * @param whereDpartBaseIN
                 * @return
                 */
                @Override
                public ResultOut<AccntDpartInfoOUT> queryDpartDetail(WhereDpartBaseIN whereDpartBaseIN)
                {
                    return null;
                }
            };
        }
    }

}
