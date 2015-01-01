package com.urltrimmer.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urltrimmer.util.AlphaNumericCodec;
import com.urltrimmer.util.Codec;

@Entity
@Table(name = "url")
public class URL implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Codec CODEC = new AlphaNumericCodec();

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "original_url", nullable = false, length = 500)
	private String originalUrl;

	@Column(name = "created")
	private Date created;
	
	@Column(name = "ramdom_id")
	private long ramdomId;
	


	public long getRamdomId() {
		return ramdomId;
	}

	public void setRamdomId(long ramdomId) {
		this.ramdomId = ramdomId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	
	
	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	/**
	 * Helper method.
	 * 
	 * @see http 
	 *      ://stackoverflow.com/questions/1590831/safely-casting-long-to-int
	 *      -in-java
	 */
	private static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(l
					+ " cannot be cast to int without changing its value.");
		}
		return (int) l;
	}

	public static Long shortCodeToId(String shortCode) {
		int i = CODEC.decode(shortCode);
		return new Long(i);
	}

	@Transient
	public String getShortUrl() {
		// FIXME precision can be lost
		int integerId = safeLongToInt(this.getRamdomId());
		return CODEC.encode(integerId);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
