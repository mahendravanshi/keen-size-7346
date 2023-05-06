package com.masai;

import java.io.Serializable;





import java.time.LocalDateTime;
import java.util.Objects;

public class Bus implements Serializable{
	private int bid;
	private String name;
	private String type;
	private String source;
	private String dest;
	private LocalDateTime arrival;
	private LocalDateTime dept;
	private int seat;
	
	public Bus() {
		// TODO Auto-generated constructor stub
	}

	public Bus(int bid, String name, String type, String source, String dest, LocalDateTime arrival, LocalDateTime dept,
			int seat) {
		super();
		this.bid = bid;
		this.name = name;
		this.type = type;
		this.source = source;
		this.dest = dest;
		this.arrival = arrival;
		this.dept = dept;
		this.seat = seat;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDept() {
		return dept;
	}

	public void setDept(LocalDateTime dept) {
		this.dept = dept;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(arrival, dept, dest, name, seat, source, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bus other = (Bus) obj;
		return Objects.equals(arrival, other.arrival) && Objects.equals(dept, other.dept)
				&& Objects.equals(dest, other.dest) && Objects.equals(name, other.name) && seat == other.seat
				&& Objects.equals(source, other.source) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Bus [bid=" + bid + ", name=" + name + ",type=" + type + ", source=" + source
				+ ",dest=" + dest + ",arrival=" + arrival + ", dept=" + dept + ", seat=" + seat + "]";
	}
}
