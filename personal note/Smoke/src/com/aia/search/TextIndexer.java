package com.aia.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.nio.file.attribute.BasicFileAttributes;






import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

public class TextIndexer implements Indexer{

	public String getIndexText() {
		return indexText;
	}

	public void setIndexText(String indexText) {
		this.indexText = indexText;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public int getIndexLengthLimit() {
		return indexLengthLimit;
	}

	public void setIndexLengthLimit(int indexLengthLimit) {
		this.indexLengthLimit = indexLengthLimit;
	}

	private String indexText="data/index_text.txt";;
	private String index="data/index.txt";
	
	private int indexLengthLimit=1000;
	
	public TextIndexer()
	{}
	
	public TextIndexer(String indexText, String index, int indexLengthLimit)
	{
		this.indexText=indexText;
		this.index=index;
		this.indexLengthLimit=indexLengthLimit;
	}
	
	@Override
	public void indexDir(String dir) {
		// TODO Auto-generated method stub
		indexDir(dir,SegmentationAlgorithm.PureEnglish);
	}
	
	@Override
	public void indexDir(String dir, SegmentationAlgorithm segmentationAlgorithm) {
		// TODO Auto-generated method stub
		try
		{
			long startTime=System.currentTimeMillis();
			Path indexTextPath=Paths.get(this.indexText);
			if(!Files.exists(indexTextPath.getParent()))
			{
				indexTextPath.getParent().toFile().mkdirs();
			}
			Path indexPath=Paths.get(this.index);
			if(!Files.exists(indexPath.getParent()))
			{
				indexPath.getParent().toFile().mkdirs();
			}
			
			Map<String, Posting> index=new HashMap();
			AtomicInteger lineCount=new AtomicInteger();
			BufferedWriter writer = Files.newBufferedWriter(indexTextPath, Charset.forName("UTF-8"));
			getFileNames(dir).forEach(file -> {
				try
				{
					List<String> lines=Files.readAllLines(Paths.get(file));
					if(lines.size()<1)
					{
						new File(file).delete();
						System.out.println("Delete empty file.");
					}
					AtomicInteger i=new AtomicInteger();
					lines.forEach(line -> {
						try{
							writer.append(line).append("¡¶").append(Paths.get(file).getFileName().toString().split("\\.")[0]).append("¡·¡¾").append(lines.size()+"/"+i.incrementAndGet()).append("¡¿\n");
							lineCount.incrementAndGet();
							List<Word> words=WordSegmenter.seg(line,segmentationAlgorithm);
							for(int j=0;j<words.size();j++)
							{
								Word word=words.get(j);
								index.putIfAbsent(word.getText(), new Posting());
								if(index.get(word.getText()).size()<indexLengthLimit)
								{
									index.get(word.getText()).putIfAbsent(lineCount.get());
									index.get(word.getText()).get(lineCount.get()).addPosition(j+1);
								}
							}
						}catch(IOException e)
						{}
					});
					
				}catch(IOException e)
				{}
			});
			writer.close();
			List<String> indices = index.entrySet()
					.stream().sorted((a,b) -> b.getValue().size()-a.getValue().size())
					.map(entry -> {
						StringBuilder docs=new StringBuilder();
						AtomicInteger lastDocId = new AtomicInteger();
						entry.getValue().getPostingItems().stream().sorted().forEach(postingItem -> {
							docs.append(postingItem.getDocId()-lastDocId.get()).append("_").append(postingItem.getFrequency()).append("_").append(postingItem.positionsToStr()).append("|");
							lastDocId.set(postingItem.getDocId());
						});
						if(docs.length()>1)
						{
							docs.setLength(docs.length()-1);
							return entry.getKey()+"="+entry.getValue().size()+"="+docs.toString();
						}
						return entry.getKey()+"=0";
					}).collect(Collectors.toList());
			Files.write(indexPath, indices, Charset.forName("UTF-8"));
		}catch(Exception e)
		{}
	
	}	
	
	private Set<String> getFileNames(String path)
	{
		Set<String> fileNames=new HashSet();
		if(Files.isDirectory(Paths.get(path)))
			System.out.println("Directory: "+path);
		else
		{
			System.out.println("Process file");
			fileNames.add(path);
			return fileNames;
		}
		
		try
		{
			Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
				{
					if(file.toFile().getName().startsWith("."))
						return FileVisitResult.CONTINUE;
					String fileName=file.toFile().getAbsolutePath();
					if(!fileName.endsWith(".txt"))
					{
						System.out.println("Not plain text file");
						return FileVisitResult.CONTINUE;
					}
					fileNames.add(fileName);
					return FileVisitResult.CONTINUE;
				}
			});
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return fileNames;
	}
	
	public static void main(String ...args)
	{
		String line="This is Jason, now worked in AIA Technology Share service.";
		List<Word> words=WordSegmenter.seg(line,SegmentationAlgorithm.PureEnglish);
		words.stream().forEach(word -> System.out.println("Word: "+word.getText()));
		/*TextIndexer ti=new TextIndexer();
		Set<String> filenames=ti.getFileNames("D:\\AIA");
		filenames.parallelStream().forEach(file -> System.out.println("FileName: "+file));*/
		
	}
}
