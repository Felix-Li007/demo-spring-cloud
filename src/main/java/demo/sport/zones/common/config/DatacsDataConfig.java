package demo.sport.zones.common.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RefreshScope
@ConfigurationProperties(prefix = "datacs")
public class DatacsDataConfig implements InitializingBean
{

    private List<PlayDataConfig> playConfig;
    private List<RankDataConfig> rankConfig;
    private Map<String, List<RankDataConfig>> mapRankConf;

    private Map<String, List<PlayDataConfig>> mapPlayConf;

    public List<PlayDataConfig> getPlayConfig()
    {
        return playConfig;
    }

    public void setPlayConfig(List<PlayDataConfig> playConfig)
    {
        this.playConfig = playConfig;
    }

    public List<RankDataConfig> queryRankConfig(String themeNo)
    {
        return mapRankConf.get(themeNo);
    }

    public List<PlayDataConfig> queryPlayConfig(String themeNo)
    {
        return mapPlayConf.get(themeNo);
    }

    public void setRankConfig(List<RankDataConfig> rankConfig)
    {
        this.rankConfig = rankConfig;
    }


    @Override
    public void afterPropertiesSet() throws Exception
    {
        this.mapRankConf = Optional.ofNullable(rankConfig).map(e -> e.stream().collect(Collectors.groupingBy(RankDataConfig::getThemeNo))).orElse(Collections.emptyMap());
        this.mapPlayConf = Optional.ofNullable(playConfig).map(e -> e.stream().collect(Collectors.groupingBy(PlayDataConfig::getThemeNo))).orElse(Collections.emptyMap());
    }


    public static class PlayDataConfig
    {
        private String themeNo;
        private String playCode;

        private String playName;

        private String playDcmi;

        public String getPlayDcmi()
        {
            return playDcmi;
        }

        public void setPlayDcmi(String playDcmi)
        {
            this.playDcmi = playDcmi;
        }

        public String getPlayName()
        {
            return playName;
        }

        public void setPlayName(String playName)
        {
            this.playName = playName;
        }

        public String getThemeNo()
        {
            return themeNo;
        }

        public void setThemeNo(String themeNo)
        {
            this.themeNo = themeNo;
        }

        public String getPlayCode()
        {
            return playCode;
        }

        public void setPlayCode(String playCode)
        {
            this.playCode = playCode;
        }

    }

    public static class RankDataConfig
    {
        private String themeNo;
        private String rankCode;
        private String rankDcmi;
        private String rangCode;
        private String rangValue;
        private String metriCode;
        private Integer rankTopn;

        public String getRankDcmi()
        {
            return rankDcmi;
        }

        public void setRankDcmi(String rankDcmi)
        {
            this.rankDcmi = rankDcmi;
        }

        public String getRankCode()
        {
            return rankCode;
        }

        public void setRankCode(String rankCode)
        {
            this.rankCode = rankCode;
        }

        public String getRangCode()
        {
            return rangCode;
        }

        public void setRangCode(String rangCode)
        {
            this.rangCode = rangCode;
        }

        public String getRangValue()
        {
            return rangValue;
        }

        public void setRangValue(String rangValue)
        {
            this.rangValue = rangValue;
        }

        public String getMetriCode()
        {
            return metriCode;
        }

        public void setMetriCode(String metriCode)
        {
            this.metriCode = metriCode;
        }

        public Integer getRankTopn()
        {
            return rankTopn;
        }

        public void setRankTopn(Integer rankTopn)
        {
            this.rankTopn = rankTopn;
        }

        public String getThemeNo()
        {
            return themeNo;
        }

        public void setThemeNo(String themeNo)
        {
            this.themeNo = themeNo;
        }

    }
}
