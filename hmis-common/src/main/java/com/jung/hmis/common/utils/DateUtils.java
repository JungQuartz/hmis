package com.jung.hmis.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_DATE = "yyyy-MM-dd";

	/**
	 * 英文全称 如：2010-12-01 23:15:06
	 */
	public static String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	public static String FORMAT_FULL_T = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	public static String FORMAT_FULL_ORDER="yyyyMMddHHmmssSSS";
	/**
	 * 中文简写 如：2010年12月01日
	 */
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

	/**
	 * 中文简写 如：12月01日
	 */
	public static String FORMAT_SHORT_CN_MD = "MM月dd日";

	/**
	 * 中文全称 如：2010年12月01日 23时15分06秒
	 */
	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	/**
	 * 精确到毫秒的完整中文时间
	 */
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	public static final long ONE_DAY = 24 * 60 * 60 * 1000;

	public static final long ONE_HOUR = 60 * 60 * 1000;

	public static final long ONE_MIN = 60 * 1000;
	private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat(
			FORMAT_DATE);

	private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat(
			FORMAT_TIME);
	private static final SimpleDateFormat SDF_TIME_ORDER = new SimpleDateFormat(
			FORMAT_FULL_ORDER);
	private static final SimpleDateFormat SDF_FORMAT_SHORT_CN_MD = new SimpleDateFormat(FORMAT_SHORT_CN_MD);
	/**
	 * 格式化日期格式为:yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return SDF_DATE.format(date);
	}

	public static String format(Date date, String partten) {
		return new SimpleDateFormat(partten).format(date);
	}

	/**
	 * 字符串转化为日期
	 */
	public static Date convertToDate(String pattern, String strDate)
			throws ParseException {
		return new SimpleDateFormat(pattern).parse(strDate);
	}

	/**
	 * 
	 * @param srcPartten
	 *            目标日期的格式
	 * @param desPartten
	 *            要转化成的格式
	 * @param strDate
	 *            字符串日期
	 * @e.g. ServiceDate.convertToStringPartten("yyyyMMdd", "yyyy/MM/dd",
	 *       "20110909")
	 * @result 2011/09/09
	 * @throws ParseException
	 */
	public static String convertToStringPartten(String srcPartten,
			String desPartten, String strDate) throws ParseException {
		return format(new SimpleDateFormat(srcPartten).parse(strDate),
				desPartten);
	}

	/**
	 * 
	 * @param srcPartten
	 *            目标日期的格式
	 * @param desPartten
	 *            要转化成的格式
	 * @param strDate
	 *            字符串日期
	 * @e.g. ServiceDate.convertToStringPartten("yyyyMMdd", "yyyy/MM/dd",
	 *       "20110909")
	 * @result 2011/09/09
	 * @throws ParseException
	 */
	public static String convertToStringParttenWithLoacale(String srcPartten,
			String desPartten, String strDate, Locale locale)
			throws ParseException {
		return format(new SimpleDateFormat(srcPartten, locale).parse(strDate),
				desPartten);
	}

	/**
	 * 检查日期字符串是否满足给定的格式。
	 * 
	 * @param srcPartten
	 * @param strDate
	 * @param locale
	 * @return
	 */
	public static boolean IsRightDatePattern(String srcPartten, String strDate,
			Locale locale) {
		try {
			new SimpleDateFormat(srcPartten, locale).parse(strDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 转换字符串日期为Date类型 格式为:yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date parse(String date) {
		Date d = null;
		try {
			d = new SimpleDateFormat(FORMAT_DATE).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static Date parse(String date, String partten) {
		Date d = null;
		try {
			d = new SimpleDateFormat(partten).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 在字符串日期上进行日期的增减 格式为:yyyy-MM-dd 可用类型：YEAR、MONTH 和 DAY_OF_MONTH
	 * ServiceDate.add("2011-08-06",Calendar.DAY_OF_MONTH, -5)。 结果：2011-08-01
	 * 
	 * @param date
	 *            格式为:yyyy-MM-dd
	 * @param field
	 *            :Calendar.YEAR(年) Calendar.MONTH（月） Calendar.DAY_OF_MONTH（日）
	 * @param amount
	 * @return
	 */
	public static String add(String date, int field, int amount) {
		return DateUtils.add(parse(date), field, amount);
	}

	public static String add(String date, String partten, int field, int amount) {
		return DateUtils.add(parse(date, partten), partten, field, amount);
	}

	/**
	 * 对字符串类型的日期进行设置具体日期 可用类型：YEAR、MONTH 和 DAY_OF_MONTH
	 * ServiceDate.set("2011-08-06",Calendar.YEAR, 2000)。 结果：2000-08-06
	 * 
	 * @param date
	 *            格式为:yyyy-MM-dd
	 * @param field
	 *            :Calendar.YEAR(年) Calendar.MONTH（月） Calendar.DAY_OF_MONTH（日）
	 * @param value
	 * @return
	 */
	public static String set(String date, int field, int value) {
		return DateUtils.set(parse(date), field, value);
	}

	/**
	 * 获得昨天的字符串日期
	 * 
	 * @param date
	 *            格式为:yyyy-MM-dd
	 * @return
	 */
	public static String getYesterday(String date) {
		return DateUtils.getYesterday(parse(date));
	}

	/**
	 * 获得明天的字符串日期
	 * 
	 * @param date
	 *            格式为:yyyy-MM-dd
	 * @return
	 */
	public static String getTomorrow(String date) {
		return DateUtils.getTomorrow(parse(date));
	}

	/**
	 * @e.g. ServiceDate.getDatesBewteenTwoDate("2011-07-03", "2011-09-09",
	 *       true);
	 * @content 获取两个日期中的所有日期，includeForm为true包括开始日期，为false不包括开始日期，结束日期均包括
	 * @param from
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @param includeForm
	 *            是否包括开始日期
	 */
	public static List<String> getDatesBewteenTwoDate(String from, String end,
			boolean includeForm) throws ParseException {
		List<String> list = new ArrayList<String>();

		int a = getDaiesBetween2Date(from, end);

		if (a != -1) {
			Calendar c = Calendar.getInstance();
			c.setTime(SDF_DATE.parse(from));

			if (includeForm) {
				list.add(from);
			}

			for (int i = 0; i < a; i++) {
				c.add(Calendar.DAY_OF_MONTH, 1);
				list.add(SDF_DATE.format(c.getTime()));
			}
		}

		return list;
	}

	/**
	 * @e.g. ServiceDate.getDaiesBetween2Date("2011-09-09", "2011-09-23")
	 * @result 4
	 * @content 算两个日期直接的差额，不包括开始日期，包括结束日期
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public static int getDaiesBetween2Date(String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);

		try {
			long date_1_time = sdf.parse(startDate).getTime();

			long date_2_time = sdf.parse(endDate).getTime();

			if (date_2_time < date_1_time) {
				return -1;
			}

			return (int) ((date_2_time - date_1_time) / ONE_DAY);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 获取当天日期
	 * 
	 * @return 格式为:yyyy-MM-dd
	 */
	public static String getToday() {
		return SDF_DATE.format(new Date());
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 格式为:yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowTime() {
		return SDF_TIME.format(new Date());
	}

	public static String getCurrentOrder(){
		return  SDF_TIME_ORDER.format(new Date());
	}

	/**
	 * 获取当前时间:MM月DD日
	 * @return
	 */
	public static String getCurrentMD(){
		return  SDF_FORMAT_SHORT_CN_MD.format(new Date());
	}

	/**
	 * @e.g. ServiceDate.get("2011-08-06", Calendar.YEAR)
	 * @result 2011
	 * @param date
	 *            格式为:yyyy-MM-dd
	 * @param field
	 *            :Calendar.YEAR(年) Calendar.MONTH（月） Calendar.DAY_OF_MONTH（日）
	 * @return
	 */
	public static int get(String date, int field) {
		return DateUtils.get(parse(date), field);
	}

	/**
	 * 获取一周内的所有日期
	 * 
	 * @start 从哪天开始算一周的开始，0表示从周日开始，1表示周一，2表示周二，依次类推
	 * @e.g. ServiceDate.dateToWeek(new Date(), "yyyy/MM/dd",1,7);
	 * @result 2011/08/22 到 2011/08/28
	 * @param mdate
	 * @return
	 */
	public static List<String> dateToWeek(Date mdate, String partten,
			int start, int weekLen) {
		int b = mdate.getDay();
		if (b == 0) {
			b = 7;
		}
		Date fdate;
		List<String> list = new ArrayList<String>();
		Long fTime = mdate.getTime() - (b - start) * 24 * 3600000;
		for (int a = 0; a < weekLen; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			list.add(a, format(fdate, partten));
		}
		return list;
	}

	/**
	 * 
	 * @param mdateStr
	 *            日期字符串
	 * @param srcPartten
	 *            日期字符串的格式
	 * @param partten
	 *            获取的目标字符串的格式
	 * @param start
	 *            从周几开始算，0为周日，1为周一
	 * @param weekLen
	 *            一周的长度，一般为7天
	 * @e.g. ServiceDate.dateToWeek("20111231","yyyyMMdd", "yyyy-MM-dd",1,7);
	 * @result 2011-12-26 到 2012-01-01
	 * @throws Exception
	 */
	public static List<String> dateToWeek(String mdateStr, String srcPartten,
			String partten, int start, int weekLen) throws Exception {
		Date mdate = convertToDate(srcPartten, mdateStr);
		int b = mdate.getDay();
		if (b == 0) {
			b = 7;
		}
		Date fdate;
		List<String> list = new ArrayList<String>();
		Long fTime = mdate.getTime() - (b - start) * 24 * 3600000;
		for (int a = 0; a < weekLen; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			list.add(a, format(fdate, partten));
		}
		return list;
	}

	/**
	 * 
	 * @param filed
	 *            表示是当前周向前推几周,正整数表示向后推算,负数表示向前推算（已经过了的日期）
	 * @return ServiceDate.dateToWeek("20111231","yyyyMMdd",
	 *         "yyyy-MM-dd",1,7,-1);
	 * @throws Exception
	 */
	public static List<String> dateToWeek(String mdateStr, String srcPartten,
			String partten, int start, int weekLen, int filed) throws Exception {
		return dateToWeek(
				add(mdateStr, srcPartten, Calendar.DAY_OF_MONTH, weekLen
						* filed), srcPartten, partten, start, weekLen);
	}

	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek(String date, String partten,
			String desPartten) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, partten));
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
            dayofweek = 7;
        }
		c.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat sdf = new SimpleDateFormat(desPartten);
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周日
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek(String date, String partten,
			String desPartten) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, partten));
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
            dayofweek = 7;
        }
		c.add(Calendar.DATE, -dayofweek + 7);
		SimpleDateFormat sdf = new SimpleDateFormat(desPartten);
		return sdf.format(c.getTime());
	}

	/**
	 * @content 计算两个日期差
	 * @param startTime
	 *            小
	 * @param endTime
	 *            大
	 * @param format
	 * @param type
	 * @e.g. ServiceDate.dateDiff("2011-08-29", "2011-8-28", "yyyy-MM-dd","day")
	 * @return -1
	 */
	public static int dateDiff(String startTime, String endTime, String format,
			String type) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			long sec = diff / ns;// 计算差多少秒
			// 输出结果
			// System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
			if ("day".equals(type)) {
				return (int) day;
			} else if ("hour".equals(type)) {
				return (int) hour;
			} else if ("min".equals(type)) {
				return (int) min;
			} else if ("sec".equals(type)) {
				return (int) sec;
			}
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 生成多日期目录，如：/file/qblog/behavior/{2011/10/22,2011/10/23}/log
	 * 
	 * @param prePart
	 *            日期前一部分的路径
	 * @param backPart
	 *            日前后一部分的路径
	 * @param startDate
	 *            开始日期，格式为yyyymmdd
	 * @param endDate
	 *            结束日期，格式为yyyymmdd
	 * @return
	 */
	public static String createMultiPath(String startPath, String endPath,
			String startDate, String endDate) throws Exception {
		StringBuffer sb = new StringBuffer(), time = new StringBuffer();
		DateFormat pathFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date sdate = dateFormat.parse(startDate);
		Date edate = dateFormat.parse(endDate);
		if (startPath != null && !"".equals(startPath.trim())) {
            sb.append(startPath);
        }
		if (sdate.equals(edate)) {
			time = time.append(pathFormat.format(sdate));
		} else {
			time.append("{");
			Calendar c = Calendar.getInstance();
			while (sdate.compareTo(edate) <= 0) {
				time.append(pathFormat.format(sdate.getTime()));
				if (sdate.compareTo(edate) < 0) {
                    time.append(",");
                }
				c.setTime(sdate);
				c.add(Calendar.DATE, 1);
				sdate = c.getTime();
			}
			time.append("}");

		}
		sb.append(time);
		if (endPath != null && !"".equals(endPath.trim())) {
            sb.append(endPath);
        }
		return sb.toString();
	}

	/**
	 * 生成多日期目录，如：/file/qblog/behavior/{2011/10/22,2011/10/23}/log
	 * 
	 * @param prePart
	 *            日期前一部分的路径
	 * @param backPart
	 *            日前后一部分的路径
	 * @param startDate
	 *            开始日期，格式为yyyymmdd
	 * @param endDate
	 *            结束日期，格式为yyyymmdd
	 * @return
	 */
	public static String createMultiPath(String startPath, String endPath,
			String startDate, String endDate, String format) throws Exception {
		StringBuffer sb = new StringBuffer(), time = new StringBuffer();
		DateFormat pathFormat = new SimpleDateFormat(format);
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date sdate = dateFormat.parse(startDate);
		Date edate = dateFormat.parse(endDate);
		if (startPath != null && !"".equals(startPath.trim())) {
            sb.append(startPath);
        }
		if (sdate.equals(edate)) {
			time = time.append(pathFormat.format(sdate));
		} else {
			time.append("{");
			Calendar c = Calendar.getInstance();
			while (sdate.compareTo(edate) <= 0) {
				time.append(pathFormat.format(sdate.getTime()));
				if (sdate.compareTo(edate) < 0) {
                    time.append(",");
                }
				c.setTime(sdate);
				c.add(Calendar.DATE, 1);
				sdate = c.getTime();
			}
			time.append("}");

		}
		sb.append(time);
		if (endPath != null && !"".equals(endPath.trim())) {
            sb.append(endPath);
        }
		return sb.toString();
	}
	//对内调用
		/**
		 * 对Date类型的日期进行增减
		 * 根据日历的规则，为给定的日历字段添加或减去指定的时间量。例如，要从当前日历时间减去 5 天
		 * ，可以通过调用以下方法做到这一点： 
		 * add(Calendar.DAY_OF_MONTH, -5)。 	
		 * @param date
		 * @param field
		 * @param amount
		 * @return
		 */
		public static String add(Date date, int field, int amount) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);

			c.add(field, amount);
			return SDF_DATE.format(c.getTime());
		}
		public static String add(Date date,String partten, int field, int amount) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(field, amount);
			return new SimpleDateFormat(partten).format(c.getTime());
		}

		/**
		 * 对Date类型的日期进行设置具体日期
		 * 
		 * @param date
		 * @param field
		 * @param value
		 * @return
		 */
		public static String set(Date date, int field, int value) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(field, value);
			return SDF_DATE.format(c.getTime());
		}

		/**
		 * 获得昨天的日期
		 * 
		 * @param date
		 * @return
		 */
		public static String getYesterday(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, -1);
			return SDF_DATE.format(c.getTime());
		}
		public static String getYesterday(Date date,String partten) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, -1);
			return new SimpleDateFormat(partten).format(c.getTime());
		}
		/**
		 * 获得明天的日期
		 * 
		 * @param date
		 * @return
		 */
		public static String getTomorrow(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			return SDF_DATE.format(c.getTime());
		}
		/**
		 * 
		 * @param startDate
		 * @param endDate
		 * @return
		 */
		public static int getDaiesBetween2Date(Date startDate, Date endDate) {
			try {
				long date_1_time = startDate.getTime();

				long date_2_time = endDate.getTime();

				if (date_2_time < date_1_time) {
					return -1;
				}

				return (int) ((date_2_time - date_1_time) / ONE_DAY);
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		/**
		 * 
		 * @param from
		 * @param end
		 * @return
		 */
		public static List<String> getDatesBewteenTwoDate(Date from, Date end, boolean includeForm) {
			List<String> list = new ArrayList<String>();

			int a = getDaiesBetween2Date(from, end);

			if (a != -1) {
				Calendar c = Calendar.getInstance();
				c.setTime(from);

				if (includeForm) {
					list.add(SDF_DATE.format(from));
				}

				for (int i = 0; i < a; i++) {
					c.add(Calendar.DAY_OF_MONTH, 1);
					list.add(SDF_DATE.format(c.getTime()));
				}
			}

			return list;
		}
		/**
		 * 因为月返回的是从0开始的月份 所以直接返回int类型
		 * 
		 * @param date
		 * @param field
		 * @return
		 */
		public static int get(Date date, int field) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(field);
		}
		/**
		 * 获得月区间开始
		 * 
		 * @param date
		 * @return
		 */
		public static String getGasMonthStartDate(String date) {
			return DateUtils.getGasMonthStartDate(parse(date));
		}

		/**
		 * 获得年区间开始
		 * 
		 * @param date
		 * @return
		 */
		public static String getGasYearStartDate(String date) {
			return DateUtils.getGasYearStartDate(parse(date));
		}

		/**
		 * 获得月区间结束
		 * 
		 * @param date
		 * @return
		 */
		public static String getGasMonthEndDate(String date) {
			return DateUtils.getGasMonthEndDate(parse(date));
		}

		/**
		 * 获得年区间结束
		 * 
		 * @param date
		 * @return
		 */
		public static String getGasYearEndDate(String date) {
			return DateUtils.getGasYearEndDate(parse(date));
		}

		/**
		 * 获得月区间开始
		 * 
		 * @param date
		 * @return
		 */
		public static String getSulfurMonthStartDate(String date) {
			return DateUtils.getSulfurMonthStartDate(parse(date));
		}

		/**
		 * 获得年区间开始
		 * 
		 * @param date
		 * @return
		 */
		public static String getSulfurYearStartDate(String date) {
			return DateUtils.getSulfurYearStartDate(parse(date));
		}

		/**
		 * 获得月区间结束
		 * 
		 * @param date
		 * @return
		 */
		public static String getSulfurMonthEndDate(String date) {
			return DateUtils.getSulfurMonthEndDate(parse(date));
		}

		/**
		 * 获得年区间结束
		 * 
		 * @param date
		 * @return
		 */
		public static String getSulfurYearEndDate(String date) {
			return DateUtils.getSulfurYearEndDate(parse(date));
		}

		public static String getGasMonthStartDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (c.get(Calendar.DATE) < 27) {
				c.add(Calendar.MONTH, -1);
			}
			c.set(Calendar.DATE, 27);
			return SDF_DATE.format(c.getTime());
		}

		public static String getGasYearStartDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (!(c.get(Calendar.MONTH) == 11 && c.get(Calendar.DATE) > 26)) {
				c.add(Calendar.YEAR, -1);
			}
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 27);
			return SDF_DATE.format(c.getTime());
		}

		public static String getGasMonthEndDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (c.get(Calendar.DATE) > 26) {
				c.add(Calendar.MONTH, 1);
			}
			c.set(Calendar.DATE, 26);
			return SDF_DATE.format(c.getTime());
		}

		public static String getGasYearEndDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (c.get(Calendar.MONTH) == 11 && c.get(Calendar.DATE) > 26) {
				c.add(Calendar.YEAR, 1);
			}
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 26);
			return SDF_DATE.format(c.getTime());
		}

		public static String getSulfurMonthStartDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (c.get(Calendar.DATE) != c.getActualMaximum(Calendar.DATE)) {
				c.add(Calendar.MONTH, -1);
			}
			c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
			return SDF_DATE.format(c.getTime());
		}

		public static String getSulfurYearStartDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (!(c.get(Calendar.MONTH) == 11 && c.get(Calendar.DATE) == 31)) {
				c.add(Calendar.YEAR, -1);
			}
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 31);
			return SDF_DATE.format(c.getTime());
		}

		public static String getSulfurMonthEndDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
				c.add(Calendar.MONTH, 1);
			}
			c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE) - 1);
			return SDF_DATE.format(c.getTime());
		}

		public static String getSulfurYearEndDate(Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if (c.get(Calendar.MONTH) == 11 && c.get(Calendar.DATE) == 31) {
				c.add(Calendar.YEAR, 1);
			}
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 30);
			return SDF_DATE.format(c.getTime());
		}
		

	/**
	 * 获取时间戳
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取日期年份
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}

	/**
	 * 功能描述：返回月
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	public static int getCurrentHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 功能描述：返回小
	 *
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分
	 *
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫
	 *
	 * @param date
	 *            日期
	 * @return 返回毫
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	public static int getYearDiff(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return 0;
        }
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
		int yearInterval = year1 - year2;
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
		if(month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval --;
        }

		return yearInterval;
	}

	public static String getFisrtPaymentDay(Date d1,int day){
		Calendar calendar1 = Calendar.getInstance();//起息日
		calendar1.setTime(d1);
		calendar1.set(Calendar.DAY_OF_MONTH,day);
		//小于15天，则下月还款
		int between = (int)getDaiesBetween2Date(d1,calendar1.getTime());

		Calendar calendar2 = Calendar.getInstance();//预计首次还款日
		calendar2.setTime(d1);
		calendar2.set(Calendar.DAY_OF_MONTH,day);
		/**
		 * 计算首次还款日 add by xk 20180515
		 * 预计首次还款日 = 还款日转换为起息当月实际日期
		 * 1.还款日 - 起息日 <= 0  * 还款日加一个月 - 起息日 < 15 则还款日 = 预计还款日加两个月
		 *                       * 还款日加一个月 - 起息日 > 15 则还款日 = 预计还款日加一个月
		 * 2.还款日 - 起息日 > 0   * 还款日加一个月 - 起息日 < 15 则还款日 = 预计还款日加一个月
		 *                       * 还款日加一个月 - 起息日 > 15 则还款日 = 预计还款日
		 *
		 */
		if (between <= 0 ){
			//预计首次还款日
			calendar2.add(Calendar.MONTH,1);
			int days = (int) getDaiesBetween2Date(d1,calendar2.getTime());
			if(days < 15 ){
				calendar2.add(Calendar.MONTH,1);
			}
		}else{
			if( between <15){
				//预计首次还款日
				calendar2.add(Calendar.MONTH,1);
			}
		}
		return SDF_DATE.format( calendar2.getTime());
	}
	public static Date getLastDayOfMonth(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, last);
		return cal.getTime();
	}
	public static String getLastDayOfMonth(String d,String partten) {

		Date date = parse(d);
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, last);
		return format(cal.getTime(),partten);
	}
	/**
		判断日期是否是周末
	 */
	public static Boolean isWeekend(Date Date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(Date);
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		try {
		Date dt1=	convertToDate(FORMAT_DATE,"2018-05-28");
			System.out.println(getFisrtPaymentDay(dt1,1));
		} catch (ParseException e) {
			e.printStackTrace();
		}


		//System.out.println(parse("2018-09-11 12:09:00","yyyy-MM-dd"));


		System.out.println(getLastDayOfMonth("2018-01-11 12:09:00","yyyyMMdd"));

		System.out.println(add("2018-01-11",Calendar.DAY_OF_MONTH,1));
		System.out.println( getYesterday(new Date()));


		String today = DateUtils.getToday();
		for (int i = 1; i <= 12; i++) {
			String day =  DateUtils.add(today, "yyyy-MM-dd", Calendar.MONTH, -i);

			int m = DateUtils.getMonth(DateUtils.parse(day));

			String lastDay=  DateUtils.getLastDayOfMonth(day,"yyyyMMdd");

			System.out.println("月-"+m +"  "+lastDay);


		}

	}
}
