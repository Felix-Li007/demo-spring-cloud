package demo.sport.zones.service;


import java.time.LocalDateTime;


public interface IGatheInfoService
{
    void gathePlayTask(String accntNo, String playCode);

    void gatheRankTask(LocalDateTime rankTime);

}
