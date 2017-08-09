package com.cloudera.job;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import com.cloudera.excel.ExcelInputFormat;
import com.cloudera.mapper.ExcelStoreMapper;

public class ExcelStoreJob extends Configured implements Tool {

	public static void main(String[] args) {
		
		if(args.length<2)
		{
			System.out.println("Java Usuage " + ExcelStoreJob.class.getName() + 
							"[config] /hdfs/path/to/input /hdfs/path/to/output");
			return;
		}
		
		Configuration conf = new Configuration(true);
		
		try
		{
			int i = ToolRunner.run(conf,new ExcelStoreJob(),args);
			if(i==0)
			{
				System.out.println("Sucess");
			}
			else
			{
				System.out.println("Failed");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public int run(String[] args) throws Exception {
		
		Configuration conf = super.getConf();
		Job ExcelStoreJob = Job.getInstance(conf,this.getClass().getName());
		ExcelStoreJob.setJarByClass(ExcelStoreJob.class);
		final String InputPathLocation = args[0];
		final Path InputPathLoc = new Path(InputPathLocation);
		final String hdfsoutputPathLocation = args[1];
		final Path hdfsOutputPathLoc = new Path(hdfsoutputPathLocation);
		
		ExcelStoreJob.setMapperClass(ExcelStoreMapper.class);
		ExcelStoreJob.setNumReduceTasks(0);
		
		FileInputFormat.addInputPath(ExcelStoreJob,InputPathLoc);
		FileOutputFormat.setOutputPath(ExcelStoreJob, hdfsOutputPathLoc);
		
		ExcelStoreJob.setInputFormatClass(ExcelInputFormat.class);
		
		ExcelStoreJob.waitForCompletion(true);
		
		return 0;
		
	}

	

}
