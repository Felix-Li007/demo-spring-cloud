package demo.sport.zones.call;


import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import com.psdn.review.api.rank.IRanksServiceApi;
import com.psdn.review.domain.rank.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@FeignClient(value = "${service.call.review.rank}", path = "/review/ranks/", contextId = "review-ranks", fallbackFactory = IReviewServiceCaller.ReviewServiceCallerFallbackFactory.class)
public interface IReviewServiceCaller extends IRanksServiceApi
{

    @Component
    class ReviewServiceCallerFallbackFactory implements FallbackFactory<IReviewServiceCaller>
    {

        private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceCallerFallbackFactory.class);

        @Override
        public IReviewServiceCaller create(Throwable e)
        {
            return new IReviewServiceCaller()
            {

                /**
                 * @param whereRankBaseIN
                 * @return
                 */
                @Override
                public ResultOut<ReviewRankInfoOUT> queryRankDetail(WhereRankBaseIN whereRankBaseIN)
                {
                    return null;
                }


                /**
                 * @param whereConfBaseIN
                 * @return
                 */
                @Override
                public ResultOut<ReviewRankConfOUT> queryConfDetail(WhereConfBaseIN whereConfBaseIN)
                {
                    return null;
                }

                /**
                 * @param whereRankPageIN
                 * @return
                 */
                @Override
                public ResultOut<PageResponse<ReviewRankItemOUT>> queryRankData(WhereRankPageIN whereRankPageIN)
                {
                    return null;
                }

                /**
                 * @param whereConfInfoIN
                 * @return
                 */
                @Override
                public ResultOut<ReviewRankItemOUT> queryAccntRank(WhereRankItemIN whereConfInfoIN)
                {
                    return null;
                }
            };
        }
    }

}
