package com.genscript.gsscm.tools.service;

import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.order.service.OrderQuoteUtil;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.shipment.dao.ShipZoneDao;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.tools.dto.ProductServiceDto;
import com.genscript.gsscm.tools.dto.WarehousingShipmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 7/21/11
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class WarehousingShipmentService {

    @Autowired
    private ShipZoneDao shipZoneDao;
    @Autowired
	private OrderQuoteUtil orderQuoteUtil;


    public WarehousingShipmentDto getRate(WarehousingShipmentDto dto,List<ProductServiceDto> productServiceDtos) throws BussinessException{
        String zipCode = dto.getToZipCode();
		ShipZone shipZone = this.shipZoneDao.getShipZone(dto.getShippingMethod(), 1, zipCode, dto.getToCountry());
        if(shipZone == null){
            throw new BussinessException("SE0001", "shipZone is null");
        }
        double totalAmount = 0.0;
        double rate = 0.0;
        double handlingFee = 0.0;
        for (int i = 0; i < productServiceDtos.size(); i++) {
             ProductServiceDto productServiceDto = productServiceDtos.get(i);
             totalAmount+= productServiceDto.getPrice();
        }

        for (int i = 0; i < productServiceDtos.size(); i++) {
             ProductServiceDto productServiceDto = productServiceDtos.get(i);
            double shippingFeeTemp = this.orderQuoteUtil.getShippingFee(dto.getShippingMethod(), 1, dto.getWeight().doubleValue(), shipZone.getZoneCode(), productServiceDto.getClsId(), dto.getItemType(), 1, (Double)totalAmount, 1).doubleValue();
             rate =  Arith.add(shippingFeeTemp, rate);
            double handlingFeeTemp = orderQuoteUtil.getHandlingFee(productServiceDto.getClsId(), dto.getItemType(), dto.getWeight().doubleValue(), 1, totalAmount);
            handlingFee = Arith.add(handlingFeeTemp, handlingFee);
        }
        dto.setCarrierRate(handlingFee);
        dto.setGenScriptRate(rate);
        return dto;
    }

}
