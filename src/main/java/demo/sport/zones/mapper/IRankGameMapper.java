package demo.sport.zones.mapper;

import demo.sport.zones.domain.RankGameInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lifeng
 * @description 针对表【t_review_rank_game(榜单游戏配置)】的数据库操作Mapper
 * @createDate 2022-10-06 14:39:49
 * @Entity com.psdn.review.rank.domain.TReviewRankGame
 */
@Mapper
public interface IRankGameMapper
{

    List<RankGameInfoPO> selectGameList();
}
