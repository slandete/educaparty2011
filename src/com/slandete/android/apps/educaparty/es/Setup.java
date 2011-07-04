package com.slandete.android.apps.educaparty.es;

import java.util.Locale;
import java.util.TimeZone;

import com.slandete.android.apps.educaparty.util.ParserUtils;

public class Setup {


	/**
	 * Clone then google docs document from here
	 * @link https://spreadsheets.google.com/ccc?key=0Akgh73WhU1qHdHJmSUlSb0JIckowX1ZiQkhsYmdkdkE&hl=en&authkey=CIaKgpcD
	 *
	 * or from templates gallery
	 * 0AvaLBPt3XQmodFVkUTZJZm5yRUlGRXJRRExGR01GSkE
	 * @link https://docs.google.com/templates?q=gddsched&sort=hottest&view=author
	 */

	/*public static final String WORKSHEETS_URL = "http://spreadsheets.google.com/feeds/worksheets/0AubAZvONZm3adHpoTzZYdE1ERDdjM3ZZR0haZElvSEE/public/basic"; */
	public static final String WORKSHEETS_URL = "http://spreadsheets.google.com/feeds/worksheets/0AvaLBPt3XQmodDRWdm1rZm5FbDF3XzJtVndzbEhWQkE/public/basic";
	public static final String EXTRA_STATUS_RECEIVER = "com.slandete.android.educaparty.extra.STATUS_RECEIVER";
	public static final TimeZone CONFERENCE_TIME_ZONE = TimeZone.getTimeZone("GMT+01:00");

	public static final String TIME_ZONE = "-0000"; // RFC 822 - time zone

	public static final String DAY1_START = "2011-07-11T00:00:00.000-00:00";
	public static final String DAY1_END = "2011-07-11T23:59:59.999-00:00";
	public static final String DAY2_START = "2011-07-12T00:00:00.000-00:00";
	public static final String DAY2_END = "2011-07-12T23:59:59.999-00:00";
	public static final String DAY3_START = "2011-07-13T00:00:00.000-00:00";
	public static final String DAY3_END = "2011-07-13T23:59:59.999-00:00";
	public static final String DAY4_START = "2011-07-14T00:00:00.000-00:00";
	public static final String DAY4_END = "2011-07-14T23:59:59.999-00:00";
	public static final String DAY5_START = "2011-07-15T00:00:00.000-00:00";
	public static final String DAY5_END = "2011-07-15T23:59:59.999-00:00";
	public static final String DAY6_START = "2011-07-16T00:00:00.000-00:00";
	public static final String DAY6_END = "2011-07-16T23:59:59.999-00:00";
	public static final String DAY7_START = "2011-07-17T00:00:00.000-00:00";
	public static final String DAY7_END = "2011-07-17T23:59:59.999-00:00";

	public static final long CONFERENCE_START_MILLIS = ParserUtils.parseTime("2011-07-13T11:30:00.000-00:00");
	public static final long CONFERENCE_END_MILLIS = ParserUtils.parseTime("2011-07-16T23:59:59.999-00:00");

	public static final Locale LOCALE = Locale.US;
	// public static final Locale LOCALE = Locale.GERMANY;
	public static final String EVENT_YEAR = "2011";
	// public static final String DATETIME_FORMAT = "EEEE MMM d yyyy h:mma Z";
	public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm Z";
	public static final String CONFERNCE_URL = "http://www.educared.org/global/campus-party-educaparty";
	public static final String TV_URL = "http://tv.campus-party.org";
	
	public static final String CONTENT_AUTHORITY = "com.slandete.android.apps.educaparty.es";
	public static final String DATABASE_NAME = "schedule_eparty.db";

	public static final boolean USE_WIFI_CHECK = false;
	public static final int VER_SESSION_HASHTAG = 27;
	

}