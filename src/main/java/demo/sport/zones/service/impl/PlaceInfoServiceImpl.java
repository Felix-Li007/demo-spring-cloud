package demo.sport.zones.service.impl;


import demo.sport.zones.common.config.StorageBaseConfig;
import demo.sport.zones.common.config.StoragePathConfig;
import demo.sport.zones.convert.ICionConvert;
import demo.sport.zones.domain.PlaceCionInfoPO;
import demo.sport.zones.entity.PlaceCionInfoBO;
import demo.sport.zones.mapper.ICionInfoMapper;
import demo.sport.zones.service.IPlaceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 */
@Service
public class PlaceInfoServiceImpl implements IPlaceInfoService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceInfoServiceImpl.class);

    @Resource
    private ICionConvert cionConvert;

    @Resource
    private StorageBaseConfig storageBaseConfig;

    @Resource
    private StoragePathConfig storagePathConfig;

    @Resource
    private ICionInfoMapper placeCionInfoMapper;


    private String combineImageURL(String imageFile)
    {
        if (ObjectUtils.isEmpty(imageFile)) {
            return null;
        }
        String strImageURL = storageBaseConfig.getOssCname() + "/" + storagePathConfig.getRootPath() + storagePathConfig.getIconsPath() + "/" + imageFile;
        return strImageURL;
    }


    @Override
    public List<PlaceCionInfoBO> queryCionList()
    {
        List<PlaceCionInfoPO> placeCionInfoPOS = placeCionInfoMapper.queryCionList();
        if (ObjectUtils.isEmpty(placeCionInfoPOS)) {
            return null;
        }
        List<PlaceCionInfoBO> placeCionInfoBOS = placeCionInfoPOS.stream().map(placeCionInfoPO -> {
            String strCionIcon = combineImageURL(placeCionInfoPO.getCionIcon());
            PlaceCionInfoBO placeCionInfoBO = cionConvert.convertCionInfoPO(placeCionInfoPO);
            placeCionInfoBO.setCionIcon(strCionIcon);
            return placeCionInfoBO;
        }).collect(Collectors.toList());
        return placeCionInfoBOS;
    }
}
