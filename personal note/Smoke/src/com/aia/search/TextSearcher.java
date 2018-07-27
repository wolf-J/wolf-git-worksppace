package com.aia.search;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

public class TextSearcher implements Searcher{

	private String indexText = "data/index_text.txt";
    public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Map<String, String> getIndexMap() {
		return indexMap;
	}

	public void setIndexMap(Map<String, String> indexMap) {
		this.indexMap = indexMap;
	}

	private String index = "data/index.txt";
    private Map<String,String> indexMap=new ConcurrentHashMap();
    public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private Score score = new WordFrequencyScore();
    private int pageSize=10;
    
    
    
    private SegmentationAlgorithm segmentationAlgorithm;
    
    public TextSearcher(SegmentationAlgorithm segmentationAlgorithm)
    {
    	this.segmentationAlgorithm=segmentationAlgorithm;
    	init();
    }
    
    public TextSearcher(String index, String indexText, SegmentationAlgorithm segmemtationAlgorithm)
    {
    	this.index=index;
    	this.indexText=indexText;
    	this.pageSize=10;
    	this.segmentationAlgorithm=segmentationAlgorithm;
    }
    
    public TextSearcher(String index, String indexText, int pageSize, SegmentationAlgorithm segmentationAlgorithm)
    {
    	this.index=index;
    	this.indexText=indexText;
    	this.pageSize=pageSize;
    	this.segmentationAlgorithm=segmentationAlgorithm;
    	init();
    } 
    
    private void init()
    {
    	try
    	{
    		long start=System.currentTimeMillis();
    		Files.readAllLines(Paths.get(index)).parallelStream().forEach(line -> {
    			String[] attrs=line.split("=");
    			if(attrs!=null && attrs.length==3)
    			{
    				indexMap.put(attrs[0], attrs[2]);
    			}
    		});
    	}catch(Exception e)
    	{
    		throw new RuntimeException(e);
    	}
    }
    
	@Override
	public Hits search(String keyword) {
		// TODO Auto-generated method stub
		return search(keyword, 1);
	}

	@Override
	public Hits search(String keyword, SearchMode searchMode) {
		// TODO Auto-generated method stub
		return search(keyword, searchMode, 1);
	}

	@Override
	public Hits search(String keyword, int page) {
		// TODO Auto-generated method stub
		return search(keyword, SearchMode.INTERSECTION);
	}

	@Override
	public Hits search(String keyword, SearchMode searchMode, int page) {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();
		
		
		return null;
	}
	
	private Set<Doc> term(String text)
	{
		return null;
	}
	
	private List<Doc> hit(String keyword, SearchMode searchMode)
	{
		long start=System.currentTimeMillis();
		List<Word> words=WordSegmenter.seg(keyword, segmentationAlgorithm);
		Set<Doc> result=new ConcurrentSkipListSet();
		if(words.size()==1)
		{
			result.addAll(term(words.get(0).getText()));
		}else if(words.size()>1)
		{
			result.addAll(term(words.get(0).getText()));
			for(int i=1;i<words.size();i++)
			{
				if(searchMode==SearchMode.INTERSECTION)
				{
					SearchMode.interfaction(result, term(words.get(i).getText()));
				}
				else
				{
					searchMode.union(result, term(words.get(i).getText()));
				}
			}
		}
		
		return null;
		
	}

}
