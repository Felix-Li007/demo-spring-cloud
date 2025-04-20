//package com.hrms.sport.zones.job;
//
//import cn.hutool.core.collection.CollectionUtil;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.hrms.frame.utils.DateTimeUtils;
//import com.hrms.sport.constants.zones.OpenStateEnum;
//import com.hrms.sport.zones.domain.ZonesZoneDatePO;
//import com.hrms.sport.zones.domain.ZonesZoneInfoPO;
//import com.hrms.sport.zones.domain.ZonesZoneTimePO;
//import com.hrms.sport.zones.mapper.IZoneDateMapper;
//import com.hrms.sport.zones.mapper.IZoneInfoMapper;
//import com.hrms.sport.zones.mapper.IZoneTimeMapper;
//import com.hrms.sport.zones.service.IZoneInfoService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
/// **
// * 定时上架游戏job
// *
// * @author houpengyi
// * @date 2022/10/27 14:24
// * @since 1.0.0
// */
//@Component
//public class ZoneStateUpdateJob {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ZoneStateUpdateJob.class);
//
//    @Resource
//    private IZoneInfoMapper zonesZoneInfoMapper;
//
//    @Resource
//    private IZoneDateMapper zonesZoneDateMapper;
//
//    @Resource
//    private IZoneTimeMapper zonesZoneTimeMapper;
//
//    @Resource
//    private IZoneInfoService zoneInfoService;
//
//
//    public void updateZoneState() {
//        long pageNum = 1;
//        long pageSize = 500;
//        LocalDateTime now = LocalDateTime.now();
//        IPage<ZonesZoneInfoPO> firstPage = zonesZoneInfoMapper.pageListZoneInfo(new Page<>(pageNum, pageSize), now);
//        pageNum = firstPage.getPages();
//        while (pageNum >= 1) {
//            IPage<ZonesZoneInfoPO> pageInfo = pageNum == 1 ? firstPage : zonesZoneInfoMapper.pageListZoneInfo(new Page<>(pageNum, pageSize), now);
//            List<ZonesZoneInfoPO> zonesZoneInfoPOS = pageInfo.getRecords();
//            if (CollectionUtils.isEmpty(zonesZoneInfoPOS)) {
//                return;
//            }
//            List<ZonesZoneInfoPO> canOpen = zonesZoneInfoPOS.stream().filter(r -> {
//                List<ZonesZoneDatePO> dateList = zonesZoneDateMapper.selectZoneDatePOS(r.getZoneNo());
//                if (CollectionUtil.isEmpty(dateList)) {
//                    return false;
//                }
//                return dateList.stream()
//                        .filter(this::judgeDateIsAllowOpen)
//                        .anyMatch(d -> judgeTimeIsAllowOpen(d.getDateNo()));
//            }).collect(Collectors.toList());
//            //排除可以开放的场馆剩余的为需要关闭的
//            zonesZoneInfoPOS.removeAll(canOpen);
//            LOGGER.info("查询到{}个场馆需要关闭", zonesZoneInfoPOS.size());
//            if (CollectionUtil.isNotEmpty(zonesZoneInfoPOS)) {
//                //zonesZoneInfoMapper.batchUpdateZoneStatus(records, OpenStateEnum.ZONE_STATE_WAITING.getCode());
//                zonesZoneInfoPOS.stream().parallel().forEach(zonesZoneInfoPO -> {
//                    String strZoneNo = zonesZoneInfoPO.getZoneNo();
//                    zoneInfoService.modifyZoneState(strZoneNo, OpenStateEnum.ZONE_STATE_WAITING.getCode());
//                });
//            }
//            LOGGER.info("查询到{}个场馆需要开启", canOpen.size());
//            if (CollectionUtil.isNotEmpty(canOpen)) {
//                canOpen.stream().parallel().forEach(zonesZoneInfoPO -> {
//                    String strZoneNo = zonesZoneInfoPO.getZoneNo();
//                    zoneInfoService.modifyZoneState(strZoneNo, OpenStateEnum.ZONE_STATE_OPENING.getCode());
//                    //zonesZoneInfoMapper.batchUpdateZoneStatus(canOpen, OpenStateEnum.ZONE_STATE_OPENING.getCode());
//                });
//            }
//            pageNum--;
//        }
//    }
//
//
//    /**
//     * 判断日期是否允许开放
//     *
//     * @param date 日期
//     * @return 是否允许开放
//     */
//    private boolean judgeDateIsAllowOpen(ZonesZoneDatePO date) {
//        return DateTimeUtils.betweenDateTimeStr(date.getOpenTime(), date.getCloseTime(), DateTimeUtils.now());
//    }
//
//
//    /**
//     * 时间是否允许开放
//     *
//     * @param dateNo 日期编号
//     * @return 是否允许开放
//     */
//    private boolean judgeTimeIsAllowOpen(String dateNo) {
//        List<ZonesZoneTimePO> timeList = zonesZoneTimeMapper.selectAllTimePOList(dateNo);
//        if (CollectionUtil.isEmpty(timeList)) {
//            return false;
//        }
//        String weekCode = getWeekCode(DateTimeUtils.now());
//        return timeList.stream()
//                .filter(t -> Objects.equals(t.getWeekCode(), weekCode))
//                .anyMatch(t -> LocalTime.now().isAfter(t.getStartTime()) && LocalTime.now().isBefore(t.getCloseTime()));
//    }
//
//
//    private String getWeekCode(Date date) {
//        String[] weeks = {"00", "01", "02", "03", "04", "05", "06"};
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
//        if (weekIndex < 0) {
//            weekIndex = 0;
//        }
//        return weeks[weekIndex];
//    }
//
//}
