package com.aia.search;

import org.apdplat.word.segmentation.SegmentationAlgorithm;

public interface Indexer {
	void indexDir(String dir);
	
	void indexDir(String dir, SegmentationAlgorithm segmentationAlgorithm);
}
