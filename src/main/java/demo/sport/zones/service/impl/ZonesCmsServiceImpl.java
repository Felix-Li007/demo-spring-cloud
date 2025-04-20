package demo.sport.zones.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hrms.core.domian.PageRequest;
import com.hrms.core.domian.PageResponse;
import com.hrms.core.domian.ResultOut;
import com.hrms.core.exception.HrmsException;
import com.hrms.core.exception.ServiceValidate;
import com.hrms.frame.sequence.HrmsSequenceGenerator;
import demo.sport.constants.zones.OpenStateEnum;
import com.hrms.sport.domain.cms.*;
import demo.sport.domain.cms.*;
import demo.sport.domain.zones.ZonesZoneDetailOUT;
import demo.sport.zones.common.constant.ZonesSequenEnum;
import demo.sport.zones.convert.cms.IDateCmsConvert;
import demo.sport.zones.convert.cms.IZonesCmsConvert;
import demo.sport.zones.domain.ZonesZoneDatePO;
import demo.sport.zones.domain.ZonesZoneInfoPO;
import demo.sport.zones.domain.ZonesZoneTimePO;
import demo.sport.zones.entity.PlaceZoneInfoBO;
import demo.sport.zones.manager.IDateCmsManager;
import demo.sport.zones.manager.ITimeCmsManager;
import demo.sport.zones.manager.IZonesCmsManager;
import demo.sport.zones.service.IZoneInfoService;
import demo.sport.zones.service.IZonesCmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static demo.sport.zones.common.exception.ErrorMessageEnum.RESULT_CODE_998;

/**
 * @author: houpengyi
 * @date: 2022/9/30 12:58
 * @description:
 */
@Service
public class ZonesCmsServiceImpl implements IZonesCmsService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IZonesCmsService.class);

    @Resource
    private IZonesCmsManager zonesCmsManager;

    @Resource
    private IDateCmsManager dateCmsManager;

    @Resource
    private ITimeCmsManager timeCmsManager;

    @Resource
    private IZonesCmsConvert zonesCmsConvert;

    @Resource
    private IDateCmsConvert dateCmsConvert;

    @Resource
    private IZoneInfoService zoneInfoService;


    @Transactional(rollbackFor = Exception.class)
    public ResultOut<Void> addZoneInfo(ZoneAddIN zoneAddIN)
    {

        //构建开放日期列表
        List<ZoneDateAddIN> zoneDateList = Lists.newArrayList();
        zoneAddIN.getZoneDateList().forEach(dateEditIn -> {
            ZoneDateAddIN zoneDateAddIN = new ZoneDateAddIN();
            BeanUtils.copyProperties(dateEditIn, zoneDateAddIN);
            zoneDateList.add(zoneDateAddIN);
        });
        //生成场馆编号
        String zoneNo = HrmsSequenceGenerator.dictionaryNo(ZonesSequenEnum.ZONE_CODE);
        if (!CollectionUtils.isEmpty(zoneDateList)) {
            this.saveDateAndTimePOS(zoneDateList, zoneNo);
        }
        Integer limitNum = Optional.ofNullable(zoneAddIN).map(ZoneAddIN::getLimitNum).orElse(-1);
        ZonesZoneInfoPO zonesZoneInfoPO = zonesCmsConvert.convert2ZonesZonePO(zoneAddIN);
        zonesZoneInfoPO.setLimitNum(limitNum);
        //设置默认场馆状态
        String openState = Optional.ofNullable(zoneAddIN.getZoneState()).orElse(OpenStateEnum.ZONE_STATE_WAITING.getCode());
        zonesZoneInfoPO.setZoneState(openState);
        zonesZoneInfoPO.setZoneNo(zoneNo);

        int count = zonesCmsManager.insertSelective(zonesZoneInfoPO);
        LOGGER.info("成功插入场馆信息，条数{}", count);


        return new ResultOut<Void>().buildSuccess(null);
    }


    public ResultOut<ZoneCmsOUT> queryCmsZoneDetail(ZoneDetailIN zoneDetailIN)
    {
        ZonesZoneInfoPO zonesZoneInfoPO = zonesCmsManager.selectByZoneNo(zoneDetailIN.getZoneNo());

        //如果场馆信息不存在则返回空对象
        if (Objects.isNull(zonesZoneInfoPO)) {
            return new ResultOut<ZoneCmsOUT>().buildSuccess(new ZoneCmsOUT());
        }
        ZoneCmsOUT zonesZoneOUT = zonesCmsConvert.convert2ZonesDetail(zonesZoneInfoPO);
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        zonesZoneOUT.setCreateTime(zonesZoneInfoPO.getCreateTime());
        List<ZonesZoneDatePO> zonesZoneDatePOList = dateCmsManager.selectZonesDateList(zoneDetailIN.getZoneNo());
        //如果没有开放时间信息则返回场馆信息
        if (CollectionUtils.isEmpty(zonesZoneDatePOList)) {
            return new ResultOut<ZoneCmsOUT>().buildSuccess(zonesZoneOUT);
        }

        List<ZonesDateCmsOUT> zonesZoneDatePOS = new ArrayList<>();
        zonesZoneDatePOList.stream().forEach(f -> {
            List<ZonesZoneTimePO> zonesZoneTimePOList = timeCmsManager.selectZonesTimeList(f.getDateNo());
            ZonesDateCmsOUT zonesDateOUT = this.convert2ZoneDateOUT(f, zonesZoneTimePOList);
            zonesZoneDatePOS.add(zonesDateOUT);
        });

        zonesZoneOUT.setZoneDateList(zonesZoneDatePOS);
        LOGGER.info("查询场馆场馆详情,场馆编号{}", zoneDetailIN.getZoneNo());
        return new ResultOut<ZoneCmsOUT>().buildSuccess(zonesZoneOUT);
    }

    @Override
    public ResultOut<Void> deleteZoneInfo(ZoneDelIn zoneDelIn)
    {
        String strZoneNo = zoneDelIn.getZoneNo();
        ZonesZoneInfoPO po = zonesCmsManager.selectByZoneNo(strZoneNo);
        ServiceValidate.isTrue(Objects.nonNull(po), RESULT_CODE_998);
        //场馆状态校验,开放状态无法删除
        /*ServiceValidate.isTrue(Objects.equals(po.getZoneState(), OpenStateEnum.ZONE_STATE_OPENING.getCode()), RESULT_CODE_996);*/
        int count = zonesCmsManager.updateZoneDelFlag(strZoneNo);
        LOGGER.info("删除场馆信息，成功条数{}", count);
        //清理缓存
        zoneInfoService.evictZoneInfo(strZoneNo);
        return new ResultOut<Void>().buildSuccess(null);
    }

    @Override
    public ResultOut<PageResponse<ZoneCmsOUT>> queryZoneList(WhereZonePageCmsIN whereZonePageCmsIN)
    {
        String zoneNo = Optional.ofNullable(whereZonePageCmsIN).map(WhereZonePageCmsIN::getZoneNo).orElse(null);
        String zoneName = Optional.ofNullable(whereZonePageCmsIN).map(WhereZonePageCmsIN::getZoneName).orElse(null);
        int nSequenceId = Optional.ofNullable(whereZonePageCmsIN).map(WhereZonePageCmsIN::getSequenceId).orElse(-1);


        int pageNum = Optional.ofNullable(whereZonePageCmsIN).map(WhereZonePageCmsIN::getPageNum).orElse(1);
        int pageSize = Optional.ofNullable(whereZonePageCmsIN).map(WhereZonePageCmsIN::getPageSize).orElse(PageRequest.PAGE_SIZE_MAX_LIMIT);

        String startTime = Optional.ofNullable(whereZonePageCmsIN).map(WhereZonePageCmsIN::getQueryStartTime).orElse(null);
        String endTime = Optional.ofNullable(whereZonePageCmsIN).map(WhereZonePageCmsIN::getQueryEndTime).orElse(null);

        PageResponse<ZoneCmsOUT> pageResponse = new PageResponse<>();
        IPage<ZonesZoneInfoPO> page = zonesCmsManager.selectParentZonePage(new Page<ZonesZoneInfoPO>(pageNum, pageSize), zoneNo, zoneName, startTime, endTime);

        List<ZonesZoneInfoPO> parentList = page.getRecords();
        if (CollectionUtils.isEmpty(parentList)) {
            return new ResultOut<PageResponse<ZoneCmsOUT>>().buildSuccess(null);
        }

        //构建多级结构
        List<PlaceZoneInfoBO> parentBOS = this.convertToZoneInfoBOS(parentList);
        List<PlaceZoneInfoBO> zoneCmsBOS = this.buildChildList(parentBOS);
        List<ZoneCmsOUT> zoneDetailOUTS = zonesCmsConvert.convert2ZoneOUTS(zoneCmsBOS);

        pageResponse.setPageNum(pageNum);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalNum((int) page.getTotal());
        pageResponse.setRecords(zoneDetailOUTS);
        LOGGER.info("查询场馆场馆分页列表");
        return new ResultOut<PageResponse<ZoneCmsOUT>>().buildSuccess(pageResponse);
    }

    private List<PlaceZoneInfoBO> convertToZoneInfoBOS(List<ZonesZoneInfoPO> parentList)
    {
        List<PlaceZoneInfoBO> parentBOS = new ArrayList<>();
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        parentList.forEach(zonePo -> {
            PlaceZoneInfoBO zonesZoneInfoCmsBO = zonesCmsConvert.convert2ZoneBO(zonePo);
            zonesZoneInfoCmsBO.setCreateTime(zonePo.getCreateTime());
            parentBOS.add(zonesZoneInfoCmsBO);
        });
        return parentBOS;
    }

    @Override
    public ResultOut<List<ZoneCmsOUT>> queryZoneParentList()
    {
        List<ZonesZoneInfoPO> zoneInfoPOS = zonesCmsManager.selectAllParentZoneList();
        List<ZoneCmsOUT> zoneCmsOUTS = zonesCmsConvert.convert2ZonesOUTS(zoneInfoPOS);
        LOGGER.info("查询场馆场馆顶级列表");
        return new ResultOut<List<ZoneCmsOUT>>().buildSuccess(zoneCmsOUTS);
    }


    @Override
    public ResultOut<Void> updateZoneState(ZoneEditIN zoneEditIN)
    {
        String strZoneNo = zoneEditIN.getZoneNo();
        ZonesZoneInfoPO po = zonesCmsManager.selectByZoneNo(strZoneNo);
        ServiceValidate.isTrue(Objects.nonNull(po), RESULT_CODE_998);
        //场馆状态校验,开放状态无法删除
        /*ServiceValidate.isTrue(Objects.equals(po.getZoneState(), OpenStateEnum.ZONE_STATE_OPENING.getCode()), RESULT_CODE_996);*/
        zonesCmsManager.updateZoneState(strZoneNo, zoneEditIN.getZoneState());
        LOGGER.info("更新场馆开放状态,场馆编号{},更新状态为{}", zoneEditIN.getZoneNo(), zoneEditIN.getZoneState());
        //清理缓存
        zoneInfoService.evictZoneInfo(strZoneNo);
        return new ResultOut<Void>().buildSuccess(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultOut<Void> updateZoneInfo(ZoneEditIN zoneEditIN)
    {
        String strZoneNo = zoneEditIN.getZoneNo();
        ZonesZoneInfoPO zonesZoneInfoPO = zonesCmsManager.selectByZoneNo(strZoneNo);
        if (Objects.isNull(zonesZoneInfoPO.getParentId()) && !Objects.isNull(zoneEditIN.getParentId())) {
            throw new HrmsException("一级场馆无法修改为二级场馆,请确认后重试!");
        }
        if (Objects.isNull(zonesZoneInfoPO)) {
            return new ResultOut<Void>().buildSuccess(null);
        }

        List<ZoneDateAddIN> zoneDateList = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(zoneEditIN.getZoneDateList())) {
            zoneEditIN.getZoneDateList().forEach(dateEditIn -> {
                ZoneDateAddIN zoneDateAddIN = new ZoneDateAddIN();
                BeanUtils.copyProperties(dateEditIn, zoneDateAddIN);
                zoneDateList.add(zoneDateAddIN);
            });
        }

        int dateCount = dateCmsManager.deleteDateByZoneNo(strZoneNo);
        LOGGER.info("删除场馆开放日期信息条数{}", dateCount);
        //调用存放日期与时间周数的方法
        this.saveDateAndTimePOS(zoneDateList, strZoneNo);
        ZonesZoneInfoPO po = zonesCmsConvert.convertEditIn2ZonePO(zoneEditIN);
        //if (Objects.isNull(zoneEditIN.getZoneState())) {
        po.setZoneState(zonesZoneInfoPO.getZoneState());
        //}
        int count = zonesCmsManager.updateZoneInfo(po);
        LOGGER.info("更新场馆详情信息条数{}", count);
        //清理缓存
        zoneInfoService.evictZoneInfo(strZoneNo);
        return new ResultOut<Void>().buildSuccess(null);
    }


    @Transactional(rollbackFor = Exception.class)
    public void saveDateAndTimePOS(List<ZoneDateAddIN> zoneDateAddINS, String zoneNo)
    {
        zoneDateAddINS.stream().forEach(zoneDateAddIN -> {
            String strDateNo = HrmsSequenceGenerator.dictionaryNo(ZonesSequenEnum.ZONE_DATE_CODE);
            List<ZonesZoneTimePO> zonesZoneTimePOS = this.convert2TimePOS(zoneDateAddIN);
            if (!CollectionUtils.isEmpty(zonesZoneTimePOS)) {
                for (ZonesZoneTimePO zonesZoneTimePO : zonesZoneTimePOS) {
                    zonesZoneTimePO.setDateNo(strDateNo);
                    int nInsertNum = timeCmsManager.insertSelective(zonesZoneTimePO);
                    LOGGER.info("成功插入场馆开放周数信息条数{}", nInsertNum);
                }
            }
            ZonesZoneDatePO zonesZoneDatePO = dateCmsConvert.convert2DatePO(zoneDateAddIN);
            zonesZoneDatePO.setDateNo(strDateNo);
            zonesZoneDatePO.setZoneNo(zoneNo);
            int dateInserCount = dateCmsManager.insertSelective(zonesZoneDatePO);
            LOGGER.info("成功插入场馆开放时间信息条数{}", dateInserCount);
            //清理缓存
            zoneInfoService.evictZoneInfo(zoneNo);
        });
    }

    private List<ZonesZoneTimePO> convert2TimePOS(ZoneDateAddIN zoneDateAddIN)
    {
        String dateWeekStr = zoneDateAddIN.getDateWeekStr();
        if (Objects.isNull(dateWeekStr)) {
            return null;
        }
        List<String> dateWeekStrs = Arrays.asList(dateWeekStr.split(","));
        List<ZonesZoneTimePO> zoneTimePOS = new ArrayList<>();
        for (String weekStr : dateWeekStrs) {
            ZonesZoneTimePO zonesZoneTimePO = new ZonesZoneTimePO();
            zonesZoneTimePO.setStartTime(LocalTime.parse(zoneDateAddIN.getDayOpenTime()));
            zonesZoneTimePO.setCloseTime(LocalTime.parse(zoneDateAddIN.getDayCloseTime()));
            zonesZoneTimePO.setWeekCode(weekStr);
            zoneTimePOS.add(zonesZoneTimePO);
        }
        return zoneTimePOS;
    }


    private List<PlaceZoneInfoBO> buildChildList(List<PlaceZoneInfoBO> parentList)
    {
        for (PlaceZoneInfoBO zonesZoneInfoCmsBO : parentList) {
            List<ZonesZoneInfoPO> childPOList = zonesCmsManager.selectZoneChildList(zonesZoneInfoCmsBO.getZoneNo());
            List<PlaceZoneInfoBO> childBOlist = this.convertToZoneInfoBOS(childPOList);
            if (!CollectionUtils.isEmpty(childBOlist)) {
                List<PlaceZoneInfoBO> zoneInfoCmsBOS = this.buildChildList(childBOlist);
                List<ZonesZoneDetailOUT> zoneCmsOUTS = zonesCmsConvert.convert2ZoneDetailOUTS(zoneInfoCmsBOS);
                zonesZoneInfoCmsBO.setChildZonesList(zoneCmsOUTS);
            }
        }
        return parentList;
    }


    //转换开放时间及开放周数对象
    private ZonesDateCmsOUT convert2ZoneDateOUT(ZonesZoneDatePO zonesZoneDatePO, List<ZonesZoneTimePO> zonesZoneTimePOList)
    {
        ZonesDateCmsOUT zonesDateOUT = dateCmsConvert.convert2ZonesCmsDateOUT(zonesZoneDatePO);
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        zonesDateOUT.setOpenTime(dateTimeFormatter.format(zonesZoneDatePO.getOpenTime()));
        zonesDateOUT.setCloseTime(dateTimeFormatter.format(zonesZoneDatePO.getCloseTime()));
        String dateWeekStr = zonesZoneTimePOList.stream().filter(f -> Optional.ofNullable(f).isPresent()).map(ZonesZoneTimePO::getWeekCode).collect(Collectors.joining(","));
        zonesDateOUT.setDateWeekStr(dateWeekStr);
        ZonesZoneTimePO zonesZoneTimePO = zonesZoneTimePOList.stream().filter(f -> Optional.ofNullable(f).isPresent()).collect(Collectors.toList()).get(0);
        zonesDateOUT.setDayOpenTime(zonesZoneTimePO.getStartTime());
        zonesDateOUT.setDayCloseTime(zonesZoneTimePO.getCloseTime());
        return zonesDateOUT;
    }
}
