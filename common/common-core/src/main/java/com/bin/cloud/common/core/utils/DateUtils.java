package com.bin.cloud.common.core.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * @Description 时间处理工具
 * @Author hubin
 * @Date 2019-10-18 14:59
 * @Version 1.0
 **/
@NoArgsConstructor
public class DateUtils {
    private static final int S = 1000;
    private static final int MIN = S * 60;
    private static final int H = MIN * 60;
    private static final int D = H * 24;
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



    /**
     * 指定日期格式
     */
    public static Date ofDate(String date) {
        return ofDate(date, ZONE_ID, DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 指定日期格式
     */
    public static Date ofDate(String date, String format) {
        return ofDate(date, ZONE_ID, DateTimeFormatter.ofPattern(format));
    }

    public static Date ofDate(String date, ZoneId zoneId, DateTimeFormatter formatter) {
        return Date.from(LocalDateTime.parse(date, formatter).atZone(zoneId).toInstant());
    }


    /**
     * 日期类型按'yyyy-MM-dd'格式转化成字符串
     */
    public static String formatYyyyMmDd(Date date) {
        if (date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID).format(DEFAULT_DATE_FORMAT);
    }

    public static String format(Date date) {
        if (date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID).format(DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 日期类型按自定义格式进行字符串化
     */
    public static String format(Date date, String format) {
        if (date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID).format(DateTimeFormatter.ofPattern(format));
    }


    /**
     * 检查日期是否是今天
     */
    public static boolean checkIsCurrentDate(Date date) {
        return date.after(Date.from(LocalDate.now().atStartOfDay(ZONE_ID).toInstant())) &&
                date.before(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZONE_ID).toInstant()));
    }

    /**
     * @param dayCount 要加减的天数,为正则加,为负则减,为0则表示当前天
     * @return 获取当前天的任意前后天数的时间
     */
    public static Date anyTimeByCurrentDay(int dayCount) {
        long resultTimeStamp = System.currentTimeMillis() + (D * dayCount);
        return new Date(resultTimeStamp);
    }

    /**
     * 字符串转日期
     */
    public static Date doStringToDate(String dateStr, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取该日期的相关日期
     */
    public static Date getDayOfByDate(Date date, int dayOf) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(dayOf, 1);
        return calendar.getTime();
    }

    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     */
    public static DayCompare dayComparePrecise(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear - fromYear;
        int month = toMonth - fromMonth;
        int day = toDay - fromDay;
        return DayCompare.builder().day(day).month(month).year(year).build();
    }

    /**
     * 计算2个日期相差多少年
     * 列：2011-02-02  ~  2017-03-02 大约相差 6.1 年
     */
    public static String yearCompare(Date fromDate, Date toDate) {
        DayCompare result = dayComparePrecise(fromDate, toDate);
        double month = result.getMonth();
        double year = result.getYear();
        //返回2位小数，并且四舍五入
        DecimalFormat df = new DecimalFormat("######0.0");
        return df.format(year + month / 12);
    }

    /**
     * 根据长度返回日期,不符合则返回
     * @param dateStr
     * @return
     */
    public static Date doStringToDateByLength(String dateStr){
        if(Objects.isNull(dateStr)) return new Date();
        if(dateStr.length()==4){
            return doStringToDate(dateStr,"yyMM");
        }else if(dateStr.length()==6){
            return doStringToDate(dateStr,"yyyyMM");
        }
        return new Date();
    }

    /**
     * @param month 月数
     * 设置当前时间月份加month
     */
    public static Date getAddMonthDate(Date startDate,int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);   //设置为当前时间
        c.add(Calendar.MONTH, month); //日期月 加 month
        Date date = c.getTime(); //结果
        return date;
    }
}
