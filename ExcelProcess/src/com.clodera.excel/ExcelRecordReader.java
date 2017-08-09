package com.cloudera.excel;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataInputStream;

import java.io.IOException;
import java.io.InputStream;

public class ExcelRecordReader extends RecordReader<LongWritable, Text> {
	
	private LongWritable key;
	private Text value;
	private InputStream myinpstream;
	private String[] strLinesArr; 

		
	
	@Override
	public void initialize(InputSplit insplit, TaskAttemptContext context)
			throws IOException, InterruptedException {		
		
		FileSplit split = (FileSplit) insplit;
		Configuration conf = context.getConfiguration();
		final Path filepath = split.getPath();
		
		FileSystem myfs = filepath.getFileSystem(conf);
		
		FSDataInputStream fileInstream = myfs.open(filepath);
		this.myinpstream = fileInstream;
		StringBuilder strBline = new ExcelXConversion().convertExcelXCellData(this.myinpstream);
		this.strLinesArr = strBline.toString().split("\n");
		
	}
	
	@Override
	public boolean nextKeyValue() throws IOException,
				   InterruptedException{	
				
		if(key==null){
			key = new LongWritable(0);
		}
		
		if(value==null){
			value = new Text(strLinesArr[0]);
		}
		
		
		if(key.get() < (this.strLinesArr.length-1))
		{
			long lngPosition=(int) key.get();
			key.set(lngPosition + 1);			
			value.set(this.strLinesArr[(int) (lngPosition + 1)]);
			lngPosition ++;
		}
		else
		{
			return false;
		}
		
		if(key==null || value==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}	
		
	@Override
	public LongWritable getCurrentKey() throws IOException,
			InterruptedException {
		
		return key;
	}
	
	
	
	@Override
	public Text getCurrentValue() throws IOException,
			InterruptedException{
		return value;
	}
	
	@Override
	public float getProgress() throws IOException,
			InterruptedException{
		return 0;
	}
	
	@Override
	public void close() throws IOException
	{
		if(myinpstream !=null)
		{
			myinpstream.close();
			
		}
	}
	
}
