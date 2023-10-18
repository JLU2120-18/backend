package com.salary.vo;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class ReportVO<T> {

    private T data;

    public ReportVO(T data) {
        this.data = data;
    }

    public static <T> ReportVO<T> success(T data){
        return new ReportVO<>(data);
    }

//    public static <T> AdminReportVO<T> error(){
//        return new AdminReportVO<>(ResponeEnum.ERROR.getDesc());
//    }
}