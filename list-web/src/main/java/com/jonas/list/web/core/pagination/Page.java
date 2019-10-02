package com.jonas.list.web.core.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

/**
 * Auth: Jo.Ho
 * Date: 2019/7/11
 */
@Getter
@JsonIgnoreProperties({"skip"})
public class Page {

	private int pageSize;

	private int pageNumber;

	private int pageTotal;

	private long count;

	private int skip;

	public Page(int pageSize, int pageNumber) {
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.skip = (pageNumber -1) * pageSize;
	}

	public void setCount(long count) {
		this.count = count;
		this.pageTotal = (int)Math.ceil((double) count/(double) pageSize);
	}

	@Override
	public String toString() {
		return "{" +
				"pageSize=" + pageSize +
				", pageNumber=" + pageNumber +
				", pageTotal=" + pageTotal +
				", count=" + count +
				", skip=" + skip +
				'}';
	}
}
