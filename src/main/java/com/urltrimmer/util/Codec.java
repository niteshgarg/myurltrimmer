package com.urltrimmer.util;
/**
 * Interface for a coder/decoder of some sort.
 *  
 */
public interface Codec {
	String encode(int i);
	int decode(String s);
}