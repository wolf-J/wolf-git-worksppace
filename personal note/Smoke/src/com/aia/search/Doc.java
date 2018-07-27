package com.aia.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doc implements Comparable {
	
	public int getDocId() {
		return DocId;
	}

	public void setDocId(int docId) {
		DocId = docId;
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	private int DocId;
	private int frequence;
	private String text;
	private Map<String,List<Integer>> wordPositions=new HashMap();
	private float score;
	
	public void merge(Doc doc)
	{
		if(DocId == doc.getDocId())
		{
			this.frequence+=doc.getFrequence();
			this.wordPositions.putAll(doc.getWordPositions());
		}
	}
	
	public Map<String,List<Integer>> getWordPositions()
	{
		return Collections.unmodifiableMap(wordPositions);
	}
	
	public void putWordPositions(String text, List<Integer> positions)
	{
		this.wordPositions.put(text, positions);
	}
	
	public void removeWordPositions(String text)
	{
		this.wordPositions.remove(text);
	}
	
	public void clearWordPositions()
	{
		this.wordPositions.clear();
	}
	
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return new Integer(DocId).compareTo(((Doc)o).getDocId());
	}
	
}
