package xce.tripod.util;

import java.io.UnsupportedEncodingException;

import xce.tripod.TripodException;


/**
 * 
 * @author michael
 * 
 */
public class SafeEncoder {
    public static byte[] encode(final String str) {
	try {
	    return str.getBytes("UTF-8");
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
	return new byte[0];
    }

    public static String encode(final byte[] data) {
	try {
	    return new String(data, "UTF-8");
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
	return "";
    }
}
