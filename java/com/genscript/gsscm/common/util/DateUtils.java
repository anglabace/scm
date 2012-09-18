package com.genscript.gsscm.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtils {

	/**
	 * 静态常量
	 */
	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";

	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

	/**
	 * 计算当前月份的最大天数
	 * 
	 * @return
	 */
	public static int findMaxDayInMonth() {
		return findMaxDayInMonth(0, 0);
	}

	/**
	 * 计算指定日期月份的最大天数
	 * 
	 * @param date
	 * @return
	 */
	public static int findMaxDayInMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return findMaxDayInMonth(date2Calendar(date));
	}

	/**
	 * 计算指定日历月份的最大天数
	 * 
	 * @param calendar
	 * @return
	 */
	public static int findMaxDayInMonth(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}

		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算当前年某月份的最大天数
	 * 
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int month) {
		return findMaxDayInMonth(0, month);
	}

	/**
	 * 计算某年某月份的最大天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}

		if (month > 0) {
			calendar.set(Calendar.MONTH, month - 1);
		}

		return findMaxDayInMonth(calendar);
	}

	/**
	 * Calendar 转换为 Date
	 * 
	 * @param calendar
	 * @return
	 */
	public static Date calendar2Date(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.getTime();
	}

	/**
	 * Date 转换为 Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar date2Calendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 拿到默认格式的SimpleDateFormat
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return getSimpleDateFormat(null);
	}

	/**
	 * 拿到指定输出格式的SimpleDateFormat
	 * 
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
		} else {
			sdf = new SimpleDateFormat(format);
		}

		return sdf;
	}

	/**
	 * 转换当前时间为默认格式
	 * 
	 * @return
	 */
	public static String formatDate2Str() {
		return formatDate2Str(new Date());
	}

	/**
	 * 转换指定时间为默认格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate2Str(Date date) {
		return formatDate2Str(date, C_TIME_PATTON_DEFAULT);
	}

	/**
	 * 转换指定时间为指定格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate2Str(Date date, String format) {
		if (date == null) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 转换默认格式的时间为Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date formatStr2Date(String dateStr) {
		return formatStr2Date(dateStr, null);
	}

	/**
	 * 转换指定格式的时间为Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatStr2Date(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.parse(dateStr, new ParsePosition(0));
	}

	/**
	 * 转换默认格式的时间为指定格式时间
	 * 
	 * @param dateStr
	 * @param defineFormat
	 * @return
	 */
	public static String formatDefault2Define(String dateStr,
			String defineFormat) {
		return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);
	}

	/**
	 * 转换源格式的时间为目标格式时间
	 * 
	 * @param dateStr
	 * @param sourceFormat
	 * @param targetFormat
	 * @return
	 */
	public static String formatSource2Target(String dateStr,
			String sourceFormat, String targetFormat) {
		Date date = formatStr2Date(dateStr, sourceFormat);
		return formatDate2Str(date, targetFormat);
	}

	/**
	 * 计算当天是该年中的第几周
	 * 
	 * @return
	 */
	public static int findWeeksNoInYear() {
		return findWeeksNoInYear(new Date());
	}

	/**
	 * 计算指定日期是该年中的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int findWeeksNoInYear(Date date) {
		if (date == null) {
			return 0;
		}
		return findWeeksNoInYear(date2Calendar(date));
	}

	/**
	 * 计算指定日历是该年中的第几周
	 * 
	 * @param calendar
	 * @return
	 */
	public static int findWeeksNoInYear(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 计算一年中的第几星期是几号
	 * 
	 * @param year
	 * @param weekInYear
	 * @param dayInWeek
	 * @return
	 */
	public static Date findDateInWeekOnYear(int year, int weekInYear,
			int dayInWeek) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}

		calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
		calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);

		return calendar.getTime();
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @return
	 */
	public static Date dayBefore2Date(int days) {
		Date date = new Date();
		return dayBefore2Object(days, null, date);
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @author wangsf
	 * @param days
	 * @return
	 */
	public static Date dayBefore2Date(Date date, int days) {
		return dayBefore2Object(days, null, date);
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @return
	 */
	public static String dayBefore2Str(int days) {
		String string = formatDate2Str();
		return dayBefore2Object(days, null, string);
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @author wangsf
	 * @param days
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T dayBefore2Object(int days,
			String format, T instance) {
		Calendar calendar = Calendar.getInstance();
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		if (instance instanceof Date) {
			calendar.setTime((Date) instance);
			calendar.add(Calendar.DATE, days);// 增加x天
			Date date = calendar.getTime();
			return (T) date;
		} else if (instance instanceof String) {
			calendar.add(Calendar.DATE, days);
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date defineDayBefore2Date(Date date, int days) {
		Date dateInstance = new Date();
		return defineDayBefore2Object(date, days, null, dateInstance);
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String defineDayBefore2Str(Date date, int days) {
		String stringInstance = formatDate2Str();
		return defineDayBefore2Object(date, days, null, stringInstance);
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param <T>
	 * @param date
	 * @param days
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineDayBefore2Object(Date date,
			int days, String format, T instance) {
		if (date == null || days == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}

		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param months
	 * @return
	 */
	public static Date monthBefore2Date(int months) {
		Date date = new Date();
		return monthBefore2Object(months, null, date);
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param months
	 * @return
	 */
	public static String monthBefore2Str(int months) {
		String string = formatDate2Str();
		return monthBefore2Object(months, null, string);
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param <T>
	 * @param months
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T monthBefore2Object(int months,
			String format, T instance) {
		if (months == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);

		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}

		return null;
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date defineMonthBefore2Date(Date date, int months) {
		Date dateInstance = new Date();
		return defineMonthBefore2Object(date, months, null, dateInstance);
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static String defineMonthBefore2Str(Date date, int months) {
		String stringInstance = formatDate2Str();
		return defineMonthBefore2Object(date, months, null, stringInstance);
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param <T>
	 * @param date
	 * @param months
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineMonthBefore2Object(Date date,
			int months, String format, T instance) {
		if (months == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}

		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.MONTH, months);

		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}

		return null;
	}

	/**
	 * 计算两个日期直接差的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int caculate2Days(Date firstDate, Date secondDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(secondDate);
		int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
		return Math.abs(dayNum1 - dayNum2);
	}

	/**
	 * 计算两个日期直接差的天数
	 * 
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return
	 */
	public static int caculate2Days(Calendar firstCalendar,
			Calendar secondCalendar) {
		if (firstCalendar.after(secondCalendar)) {
			Calendar calendar = firstCalendar;
			firstCalendar = secondCalendar;
			secondCalendar = calendar;
		}

		long calendarNum1 = firstCalendar.getTimeInMillis();
		long calendarNum2 = secondCalendar.getTimeInMillis();
		return Math.abs((int) ((calendarNum1 - calendarNum2) / C_ONE_DAY));
	}

	/**
	 * @author wangsf 获得当前日期所在周的最后一天.
	 * @return
	 */
	public static Date getWeekEndDay(final Date fromDate) {
		Date toDate = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		int tempWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (tempWeek == 1) {
			toDate = fromDate;
		} else {
			tempWeek = 7 - (tempWeek - 1);
			toDate = DateUtils.defineDayBefore2Object(fromDate, tempWeek,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
		}
		return toDate;
	}
	
	/**
	 * @author wangsf 获得当前日期所在月的最后一天.
	 * @return
	 */
	public static Date getMonthEndDay(final Date fromDate) {
		Date toDate = null;
		int maxDay = DateUtils.findMaxDayInMonth(fromDate);
		String fromDateStr = DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd");
		String maxDayStr = maxDay < 10 ? "0" + maxDay : "" + maxDay;
		toDate = DateUtils.formatStr2Date(fromDateStr.substring(0, 8)
				+ maxDayStr, "yyyy-MM-dd");
		return toDate;
	}
	
	/**
	 * @author wangsf 获得当前日期所在季度的最后一天.
	 * @return
	 */
	public static Date getQuarterEndDay(final Date fromDate) {
		Date toDate = null;
		String fromDateStr = DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd");
		int iMonth = Integer.parseInt(fromDateStr.substring(5, 7));
		int iMaxMonth =iMonth+2;
		/*if (iMonth <= 3) {
			iMaxMonth = 3;
		} else if (iMonth <= 6) {
			iMaxMonth = 6;
		} else if (iMonth <= 9) {
			iMaxMonth = 9;
		} else {
			iMaxMonth = 12;
		}*/
		String monthStr = iMonth < 10 ? "0" + iMaxMonth : "" + iMaxMonth;
		Date quarter = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
				+ monthStr, "yyyy-MM");
		int maxDay = DateUtils.findMaxDayInMonth(quarter);
		toDate = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
				+ monthStr + "-" + maxDay, "yyyy-MM-dd");
		return toDate;
	}

	/**
	 * @author mingrs 获得当前日期所在季度的第一天.
	 * @return
	 */
	public static Date getQuarterStartDay(final Date fromDate) {
		Date toDate = null;
		String fromDateStr = DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd");
		int iMonth = Integer.parseInt(fromDateStr.substring(5, 7));
		int iMaxMonth = iMonth;
		/*if (iMonth <= 3) {
			iMaxMonth = 1;
		} else if (iMonth <= 6) {
			iMaxMonth = 4;
		} else if (iMonth <= 9) {
			iMaxMonth = 7;
		} else {
			iMaxMonth = 10;
		}*/
		String monthStr = iMonth < 10 ? "0" + iMaxMonth : "" + iMaxMonth;
		toDate = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
				+ monthStr + "-" + "01", "yyyy-MM-dd");
		return toDate;
	}

	/**
	 * @author mingrs 获得当前日期所在半年的最后一天.
	 * @return
	 */
	public static Date getLast6MonthsEndDay(final Date fromDate) {
		Date toDate = null;
		String fromDateStr = DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd");
		int iMonth = Integer.parseInt(fromDateStr.substring(5, 7));
		int iMaxMonth = iMonth+5;
		/*if (iMonth <= 6) {
			iMaxMonth = 6;
		} else {
			iMaxMonth = 12;
		}*/
		String monthStr = iMonth < 10 ? "0" + iMaxMonth : "" + iMaxMonth;
		Date quarter = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
				+ monthStr, "yyyy-MM");
		int maxDay = DateUtils.findMaxDayInMonth(quarter);
		toDate = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
				+ monthStr + "-" + maxDay, "yyyy-MM-dd");
		return toDate;
	}

	/**
	 * @author mingrs 获得当前日期所在半年的第一天.
	 * @return
	 */
	public static Date getLast6MonthsStartDay(final Date fromDate) {
		Date toDate = null;
		String fromDateStr = DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd");
		int iMonth = Integer.parseInt(fromDateStr.substring(5, 7));
		int iMaxMonth = iMonth;
		/*if (iMonth <= 6) {
			iMaxMonth = 1;
		} else {
			iMaxMonth = 7;
		}*/
		String monthStr = iMonth < 10 ? "0" + iMaxMonth : "" + iMaxMonth;
		// Date quarter = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
		// + monthStr, "yyyy-MM");
		toDate = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
				+ monthStr + "-" + "01", "yyyy-MM-dd");
		return toDate;
	}

	/**
	 * @author wangsf 获得当前日期所在年的最后一天.
	 * @return
	 */
	public static Date getYearEndDay(final Date fromDate) {
		Date toDate = null;
		String fromDateStr = DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd");
		Date lastMonth = DateUtils.formatStr2Date(fromDateStr.substring(0, 5)
				+ "12", "yyyy-MM");
		int maxDay = DateUtils.findMaxDayInMonth(lastMonth);
		toDate = DateUtils.formatStr2Date(fromDateStr.substring(0, 5) + 12
				+ "-" + maxDay, "yyyy-MM-dd");
		return toDate;
	}

	/**
	 * @author mingrs 获得当前日期所在年的第一天.
	 * @return
	 */
	public static Date getYearStartDay(final Date fromDate) {
		Date toDate = null;
		String fromDateStr = DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd");
		toDate = DateUtils.formatStr2Date(fromDateStr.substring(0, 5) + 01
				+ "-" + 01, "yyyy-MM-dd");
		return toDate;
	}

	/**
	 * 根据传过来的时间值返回AM, 或PM;
	 * 
	 * @param time
	 *            如： 08:00:00
	 * @return
	 */
	public static String getAPM(String time) {
		if (time == null || time.trim().length() < 1) {
			return "";
		}
		if (time.equals("12:00:00")) {
			return "AM";
		}
		Integer hTime = Integer.parseInt(time.substring(0, 2));
		if (hTime >= 12) {
			return "PM";
		}
		return "AM";
	}

	/**
	 * 根据传过来的时间返回AM,PM对应的值, 并保留相应长度.
	 * 
	 * @param time
	 *            如： 08:00:00
	 * @param length
	 *            如: 5,则返回 08:00;
	 * @return
	 */
	public static String getAPMTime(String time, int length) {
		if (time == null || time.trim().length() < 1) {
			return "";
		}
		String amPm = DateUtils.getAPM(time);
		Integer hTime = Integer.parseInt(time.substring(0, 2));
		String minAndSec = time.substring(2);
		String realTime;
		if (amPm.equalsIgnoreCase("AM")) {
			realTime = time;
		} else {
			hTime = hTime - 12;
			if (hTime < 10) {
				realTime = "0" + hTime + minAndSec;
			} else {
				realTime = hTime + minAndSec;
			}
		}
		realTime = realTime.substring(0, length);
		return realTime;
	}

	public static String apmStr2Time(String timeStr, String apm) {
		if (timeStr == null || timeStr.trim().length() < 1 || apm == null) {
			return null;
		}
		String realTime;
		if (apm.equalsIgnoreCase("AM")) {
			realTime = timeStr;
		} else {
			String minAndSec = timeStr.substring(2);
			Integer hTime = Integer.parseInt(timeStr.substring(0, 2));
			hTime = hTime + 12;
			realTime = hTime + minAndSec;
		}
		realTime += ":00";
		if (realTime.equals("24:00:00")) {
			return "00:00:00";
		}
		return realTime;
	}

	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 获得上周星期一的日期
	 * 
	 * @author golf
	 * @return String
	 */
	public static String getPreviousMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus - 7);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	/**
	 * 获得上周星期日的日期
	 * 
	 * @author golf
	 * @return String
	 */
	public static String getPreviousSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus - 1);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preSunday = sdf.format(monday);
		return preSunday;
	}

	/**
	 * 获得本周星期一的日期
	 * 
	 * @author golf
	 * @return
	 */
	public static String getCurrentMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currMonday = sdf.format(monday);
		return currMonday;
	}

	/**
	 * 获得上月的第一天的日期
	 * 
	 * @author golf
	 * @return
	 */
	public static String getLastMonth() {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.MONTH, -1);
		currentDate.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date day = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastMonth = sdf.format(day);
		return lastMonth;
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @author golf
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date day = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(day);
	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @author golf
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		Date day = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(day);
	}

	public static void main(String[] args) {
		// System.out.println("当前月份的最大天数:" + findMaxDayInMonth(new Date()));
		// System.out.println("6月份的最大天数:" + findMaxDayInMonth(6));
		// System.out.println("1999-02月份的最大天数:" + findMaxDayInMonth(1999, 2));
		// System.out.println("2000-02月份的最大天数:" + findMaxDayInMonth(2000, 2));

		// System.out.println(formatSource2Target("2009-07-24 11:02:35", null,
		// "yyyy/MM/dd"));
		// System.out.println(findWeeksNoInYear());

		// System.out.println("2003年的第30个星期一是那天:" + findDateInWeekOnYear(2003,
		// 30, Calendar.MONDAY));
		// System.out.println("2009年的第30个星期一是那天:" + findDateInWeekOnYear(2009,
		// 30, Calendar.FRIDAY));

		// System.out.println("【日期格式】当前日期的前7天是:" + dayBefore2Date(-7));
		// System.out.println("【字符格式】当前日期的前7天是:" + dayBefore2Str(-7));
		// System.out.println("【日期格式】当前日期的后7天是:" + dayBefore2Date(7));
		// System.out.println("【字符格式】当前日期的后7天是:" + dayBefore2Str(7));

		// System.out.println(formatStr2Date("2009-07-22", "yyyy-MM-dd"));

		// System.out.println("【日期格式】2009-07-22的前7天是:" +
		// defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// -7));
		// System.out.println("【字符格式】2009-07-22的前7天是:" +
		// defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -7));
		// System.out.println("【日期格式】2009-07-22的后7天是:" +
		// defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));
		// System.out.println("【字符格式】2009-07-22的后7天是:" +
		// defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));

		// System.out.println("【日期格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
		// System.out.println("【字符格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
		// System.out.println("【日期格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));
		// System.out.println("【字符格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));

		// System.out.println("【日期格式】2009-07-22的前2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// -2));
		// System.out.println("【字符格式】2009-07-22的前2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// -2));
		// System.out.println("【日期格式】2009-07-22的后2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// 2));
		// System.out.println("【字符格式】2009-07-22的后2月日期是:" +
		// defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"),
		// 2));

		// Date firstDate = formatStr2Date("2009-07-22", "yyyy-MM-dd");
		// Date secondDate = formatStr2Date("2009-07-18", "yyyy-MM-dd");
		// System.out.println(caculate2Days(firstDate, secondDate));

		Calendar firstC = date2Calendar(formatStr2Date("2009-07-22",
				"yyyy-MM-dd"));
		Calendar secondC = date2Calendar(formatStr2Date("2009-07-18",
				"yyyy-MM-dd"));
		System.out.println(caculate2Days(firstC, secondC));
	}

	/**
	 * 说明：把Date对象转为XMLGregorianCalendar
	 * 
	 * @author Golf
	 * 
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		if(date != null){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return gc;
		}
		return null;
	}
}