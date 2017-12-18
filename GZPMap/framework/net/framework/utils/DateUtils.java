package net.framework.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 
* ********************************************
* ClassName: DateUtils
* Description: 日期工具类
* author:   jason
* date:     Jun 30, 2013 4:25:08 PM
* version:  v1.0
* ********************************************
 */
public class DateUtils {

	private static final int SECOND = 60;
	private static final int LAST_HOUR = 23;
	private static final int LAST_SEC = 59;
	private static final int LAST_MIN = 59;
	public static String SIMPLE_DEFAULT_FORMAT = "yyyy-MM-dd";
	public static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final Date DEFAULT_DATETIME= new Date(0,0,0,0,0);

	
	/**
	 * 日期相减
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long subtractDate(Date date1, Date date2) {
		try {
			long l = date1.getTime() - date2.getTime();
			return  l / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static Date addDateOfYMWD(Date date, Integer year, Integer month, Integer week, Integer day) {
		return addDate(date, year, month, week, day, null, null, null);
	}
	public static Date addDateOfHMS(Date date, Integer hour, Integer min, Integer sec) {
		return addDate(date, null, null, null, null, hour, min, sec);
	}
	/**
	 * 向日期加（减）时间数
	 * @param date
	 * @param year
	 * @param month
	 * @param week
	 * @param day
	 * @param hour
	 * @param min
	 * @param sec
	 * @return
	 */
	public static Date addDate(Date date, Integer year, Integer month, Integer week, Integer day, Integer hour, Integer min, Integer sec) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar cc = (Calendar) calendar.clone();
		if(year != null) {
			cc.add(Calendar.YEAR, year);
		}
		if(month != null) {
			cc.add(Calendar.MONTH, month);
		} 
		if(week != null) {
			cc.add(Calendar.WEEK_OF_MONTH, week);
		} 
		if(day != null) {
			cc.add(Calendar.DAY_OF_MONTH, day);
		}
		if(hour != null) {
			cc.add(Calendar.HOUR_OF_DAY, hour);
		}
		if(min != null) {
			cc.add(Calendar.MINUTE, min);
		}
		if(sec != null) {
			cc.add(Calendar.SECOND, sec);
		}
		return cc.getTime();
	}

	
	
	/**
	 * 
	 * Description:取得当星期的第一天0时0分0移
	 * author: jason
	 * @param @param current
	 * @param @return 设定文件 
	 * @return  Date 返回类型 
	 * @throws
	 */
	public static Date getStartDayOfWeek(Date current) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		Calendar startCalendar = (Calendar) calendar.clone();
		startCalendar.set(Calendar.DAY_OF_WEEK, 1);
		Date start = DateUtils.getStartOfDay(startCalendar.getTime());
		return start;
	}
	/**
	 * 
	 * Description:取得当星期最后一个时间23时59分59移
	 * author: jason
	 * @param @param current
	 * @param @return 设定文件 
	 * @return  Date 返回类型 
	 * @throws
	 */
	public static Date getEndDayOfWeek(Date current) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		Calendar endCalendar = (Calendar) calendar.clone();
		endCalendar.set(Calendar.DAY_OF_WEEK, 7);
		Date end = DateUtils.getEndOfDay(endCalendar.getTime());
		return end;
	}
	
	/**
	 * 
	* Description:取得当月的第一天0时0分0移
	* author: jason
	* @param @param current
	* @param @return 设定文件 
	* @return  Date 返回类型 
	* @throws
	 */
	public static Date getStartDayOfMonth(Date current) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		Calendar startCalendar = (Calendar) calendar.clone();
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		Date start = DateUtils.getStartOfDay(startCalendar.getTime());
		return start;
	}

	/**
	 * 
	* Description:取得当月的最后一天的23时59分59移
	* author: jason
	* @param @param current
	* @param @return 设定文件 
	* @return  Date 返回类型 
	* @throws
	 */
	public static Date getEndDayOfMonth(Date current) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		Date end = DateUtils.getEndOfDay(calendar.getTime());
		return end;
	}

	/**
	 * 
	* Description:取得当天的最早一个0时0分0移
	* author: jason
	* @param @param start
	* @param @return 设定文件 
	* @return  Date 返回类型 
	* @throws
	 */
	public static Date getStartOfHour(Date start) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		int year = startCal.get(Calendar.YEAR);
		int month = startCal.get(Calendar.MONTH);
		int day = startCal.get(Calendar.DAY_OF_MONTH);
		int hour = startCal.get(Calendar.HOUR_OF_DAY);
		return new GregorianCalendar(year, month, day, hour, 0, 0).getTime();
	}

	/**
	 * 
	* Description:取得当天的最早一个0时0分0移
	* author: jason
	* @param @param end
	* @param @return 设定文件 
	* @return  Date 返回类型 
	* @throws
	 */
	public static Date getEndOfHour(Date end) {
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		int year = endCal.get(Calendar.YEAR);
		int month = endCal.get(Calendar.MONTH);
		int day = endCal.get(Calendar.DAY_OF_MONTH);
		int hour = endCal.get(Calendar.HOUR_OF_DAY);
		return new GregorianCalendar(year, month, day, hour, LAST_MIN, LAST_SEC).getTime();
	}

	/**
	 * 
	* Description:取得当天的最早一个0时0分0移
	* author: jason
	* @param @param start
	* @param @return 设定文件 
	* @return  Date 返回类型 
	* @throws
	 */
	public static Date getStartOfDay(Date start) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		int year = startCal.get(Calendar.YEAR);
		int month = startCal.get(Calendar.MONTH);
		int day = startCal.get(Calendar.DAY_OF_MONTH);
		return new GregorianCalendar(year, month, day, 0, 0, 0).getTime();
	}

	/**
	 * 
	* Description:取得当天的最后一个时间23时59分59移
	* author: jason
	* @param @param end
	* @param @return 设定文件 
	* @return  Date 返回类型 
	* @throws
	 */
	public static Date getEndOfDay(Date end) {
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		int year = endCal.get(Calendar.YEAR);
		int month = endCal.get(Calendar.MONTH);
		int day = endCal.get(Calendar.DAY_OF_MONTH);
		return new GregorianCalendar(year, month, day, LAST_HOUR, LAST_MIN, LAST_SEC).getTime();
	}

	public static boolean isTheSameDay(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) && (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
	}

	public static String format(Date date) {
		return format(date, DEFAULT_FORMAT);
	}

	public static String format(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static Date toDate(String date) {
		return toDate(date, SIMPLE_DEFAULT_FORMAT);
	}

	public static Date toDate(String date, String format) {
		if (date == null || date.equals("")) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			Date result = dateFormat.parse(date);
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	public static String secondFormat(int second) {
		int hour = 0;
		int minute = 0;
		if (second >= SECOND) {
			minute = second / SECOND;
			second = second % SECOND;
		}
		if (minute > SECOND) {
			hour = minute / SECOND;
			minute = minute % SECOND;
		}
		DecimalFormat df = new DecimalFormat("00");
		return (df.format(hour) + ":" + df.format(minute) + ":" + df.format(second));
	}

	public static String getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(Calendar.YEAR));
	}
	
	/**
	 * 
	* Description:mysql日期timestamp类型格式数据
	* author: jason
	* @param @param date
	* @param @return 设定文件 
	* @return  String 返回类型 
	* @throws
	 */
	public static String sqlDateFormate(Date date){
		SimpleDateFormat sf = new SimpleDateFormat(DEFAULT_FORMAT);
		String dateStr = sf.format(date);
		dateStr = "DATE_FORMAT('"+ dateStr +"', 'yyyy-MM-dd hh24:mi:ss')";
		return dateStr;
	}
	
	public static String getStringFormatNowDate(String format)
	{
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(new Date());
	}
	/**
     * 当前年的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static   Date getStimeOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     * 
     * @return
     */
    public static  Date getEtimeOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     * 
     * @return
     */
    public static  Date getStimeOfQuarter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     * 
     * @return
     */
    public  static Date getEtimeOfQuarter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取前/后半年的开始时间
     * @return
     */
    public static Date getStimeOfHalfYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 6){
                c.set(Calendar.MONTH, 0);
            }else if (currentMonth >= 7 && currentMonth <= 12){
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }
    /**
     * 获取前/后半年的结束时间
     * @return
     */
    public static Date getEtimeOfHalfYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 6){
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            }else if (currentMonth >= 7 && currentMonth <= 12){
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Title: compare
     * Description: 0-2个日期相等；1-前面的日期比较大；2-后面的日期比较大
     * Created On: 2014-4-9 下午4:20:53
     * @author lfg 
     * @param date1
     * @param date2
     * @return 
     */
    public static int compare(Date date1, Date date2){
    	if (date1 == null && date2 ==null){
    		return 0;
    	}else if(date1 ==null){
    		return -1;
    	}else if(date2 == null){
    		return 1;
    	}else if(date1.getTime() > date2.getTime()){
    		return 1;
    	}else if(date1.getTime() < date2.getTime()){
    		return -1;
    	}else{
    		return 0;
    	}
    }
    
    /**
     * Title: getYear
     * Description:获取年份字符串
     * Created On: 2014-4-10 上午10:52:16
     * @author ssj 
     * @param date
     * @return 
     */
    public static String getYear(Date date){
    	Calendar startCal = Calendar.getInstance();
		startCal.setTime(date);
		int year = startCal.get(Calendar.YEAR);
		return String.valueOf(year);
    }
    
    /**
     * Title: getYearAndQuarter
     * Description:获取年份和季度字符串
     * Created On: 2014-4-11 下午1:39:09
     * @author ssj 
     * @param date
     * @return 
     */
    public static String getYearAndQuarter(Date date){
    	 Calendar c = Calendar.getInstance();
         c.setTime(date);
         int year = c.get(Calendar.YEAR);
         int currentMonth = c.get(Calendar.MONTH) + 1;
         try {
             if (currentMonth >= 1 && currentMonth <= 3) {
                 return year+"年第{0}季度";
             } 
             if (currentMonth >= 4 && currentMonth <= 6) {
            	 return year+"年第{1}季度";
             }
             if (currentMonth >= 7 && currentMonth <= 9) {
            	 return year+"年第{2}季度";
             }
             if (currentMonth >= 10 && currentMonth <= 12) {
            	 return year+"年第{3}季度";
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
    }
    
    public static String getYearAndMonth(Date date){
    	 Calendar c = Calendar.getInstance();
         c.setTime(date);
         int year = c.get(Calendar.YEAR);
         int currentMonth = c.get(Calendar.MONTH) + 1;
         return year+"年"+currentMonth+"月";
    }
    
    public static void main(String[] args) {
    	//System.out.println(getEndDayOfWeek(addDate(new Date(),null,null, -4,null,null,null,null)));
    	System.out.println(caculateDays(new Date(), DateUtils.addDate(new Date(), null, 3, null, 1, null, null, null)));
	}
    /**
     * Title: getMonthOfYear
     * Description:
     * Created On: 2014-4-17 下午5:43:40
     * @author lfg 
     * @param date
     * @return 
     */
    public static int getMonthOfYear(Date date){
    	 Calendar c = Calendar.getInstance();
         c.setTime(date);
         return c.get(Calendar.MONTH);
    }
    public static int getDateOfMonth(Date date){
   		Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
   }
    public static int getHourOfDay(Date date){
   		Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
   }
   public static Integer caculateDays(Date sdate,Date edate){
	   Long l = (edate.getTime()-sdate.getTime())/(1000*60*60*24);
	   return l.intValue();
   }
	
}

