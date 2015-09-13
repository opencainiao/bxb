package com.bxb.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeUtil {

	public static void main(String[] args) throws Exception {

		System.out.println(getAge("2014-1-08"));
	}

	/****
	 * 取年龄，格式为 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getAge(String date) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date dbDate = (Date) dateFormat.parse(date);

		return getAge(dbDate);
	}

	/****
	 * 取年龄
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static int getAge(String date, String format) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		Date dbDate = (Date) dateFormat.parse(date);

		return getAge(dbDate);
	}

	public static int getAge(Date dateOfBirth) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (dateOfBirth != null) {
			now.setTime(new Date());
			born.setTime(dateOfBirth);
			if (born.after(now)) {
				throw new IllegalArgumentException("Can't be born in the future");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}

		if (age == 0) {
			return 1;
		}
		return age;
	}
}
