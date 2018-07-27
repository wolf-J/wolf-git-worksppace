package com.aia.search;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PostingItem implements Comparable{

	private int docId;
	private int frequency;
	private Set<Integer> positions=new HashSet();
	
	
	public PostingItem(int docId)
	{
		this.docId=docId;
	}
	
	public void setFrequency(int frequency)
	{
		this.frequency=frequency;
	}
	
	public int getFrequency()
	{
		return positions.isEmpty() ? frequency : positions.size();
	}
	
	public void setDocId(int docId)
	{
		this.docId=docId;
	}
	
	public int getDocId()
	{
		return this.docId;
	}
	
	public String positionsToStr()
	{
		StringBuilder sb = new StringBuilder();
		positions.stream().sorted().forEach(p -> sb.append(p).append(":"));
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
	

	public Set<Integer> getPositions()
	{
		return Collections.unmodifiableSet(this.positions);
	}
	
	public void addPosition(int position)
	{
		positions.add(position);
	}
	
	public void removePosition(int position)
	{
		this.positions.remove(position);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this==o)
			return true;
		if(!(o instanceof PostingItem))
			return false;
		PostingItem item = (PostingItem)o;
		return item.getDocId()==this.getDocId();
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
