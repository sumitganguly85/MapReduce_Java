package com.cloudera.excel;

import java.io.InterruptedIOException;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.RecordReader;

public class ExcelInputFormat extends FileInputFormat<LongWritable, Text> {

	@Override
	public RecordReader<LongWritable,Text> createRecordReader(InputSplit split,
			TaskAttemptContext context) throws IOException,InterruptedIOException
	{		
		return new ExcelRecordReader();
	}
}
