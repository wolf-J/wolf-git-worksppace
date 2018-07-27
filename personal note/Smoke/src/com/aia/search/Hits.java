package com.aia.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hits {
	private int hitCount;
	private List<Doc> docs=new ArrayList();
	
	public Hits(int hitCount, List<Doc> docs)
	{
		this.hitCount=hitCount;
		this.docs=docs;
	}
	
	public int getHitCount()
	{
		return this.hitCount;
	}
	
	public List<Doc> getDocs()
	{
		return Collections.unmodifiableList(docs);
	}
	
}
