package com.basic.validate;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangzhilong on 2016/11/18.
 */
public class TestStringLink {

    public static void main(String[] args) {
        List<TenderBiddingInfo> tenderBiddingInfos = Arrays.asList(
                new TenderBiddingInfo(1),
                new TenderBiddingInfo(2),
                new TenderBiddingInfo(3)
        ) ;

        List<CarResultOutDto> carResultOutDtos = Arrays.asList(
                new CarResultOutDto(1),
                new CarResultOutDto(2),
                new CarResultOutDto(3)
        );
        int len = 2 ;
        for (int i = 0; i < len; i++) {
            for (TenderBiddingInfo tenderBiddingInfo : tenderBiddingInfos){
                for (CarResultOutDto carResultOutDto : carResultOutDtos){
                    if(carResultOutDto.getCarId() != tenderBiddingInfo.getCarId()){
                        continue;
                    }
                    String succesAging = tenderBiddingInfo.getFstAging() == null ? "" : tenderBiddingInfo.getFstAging().toString() ;
                    String lastSuccesAging = carResultOutDto.getSuccessAging() == null ? "" : carResultOutDto.getSuccessAging();
                    lastSuccesAging = lastSuccesAging + succesAging ;
                    if(i < len-1){
                        lastSuccesAging = lastSuccesAging + "|" ;
                    }
                    carResultOutDto.setSuccessAging(lastSuccesAging);
                }
            }
        }
        System.out.println(JSON.toJSONString(carResultOutDtos));
    }

}

class CarResultOutDto {

    private int id ;
    private String successAging ;
    private int carId ;

    public CarResultOutDto(int carId) {
        this.carId = carId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuccessAging() {
        return successAging;
    }

    public void setSuccessAging(String successAging) {
        this.successAging = successAging;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}

class TenderBiddingInfo {

    private int carId ;
    private BigDecimal fstAging ;

    public TenderBiddingInfo(int carId) {
        this.carId = carId ;
    }

    public TenderBiddingInfo(BigDecimal fstAging) {
        this.fstAging = fstAging;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public BigDecimal getFstAging() {
        return fstAging;
    }

    public void setFstAging(BigDecimal fstAging) {
        this.fstAging = fstAging;
    }
}
