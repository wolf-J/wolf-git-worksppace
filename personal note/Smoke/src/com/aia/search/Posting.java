package com.aia.search;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Posting {
	private Map<Integer, PostingItem> postingItems = new HashMap();
	
	public int size()
	{
		return postingItems.size();
	}
	
	public Collection<PostingItem> getPostingItems()
	{
		return Collections.unmodifiableCollection(postingItems.values());
	}
	
	public void putIfAbsent(int docId)
	{
		postingItems.putIfAbsent(docId, new PostingItem(docId));
	}
	
	 public PostingItem get(int docId){
	        return this.postingItems.get(docId);
	 }
	
	public void remove(PostingItem postingItem)
	{
		this.postingItems.remove(postingItem);
	}
	
	public void clear()
	{
		this.postingItems.clear();
	}
}
