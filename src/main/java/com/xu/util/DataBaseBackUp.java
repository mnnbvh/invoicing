package com.xu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@PropertySource(value = "classpath:db.properties", encoding = "utf-8")
public class DataBaseBackUp {
	/*private static String backuppath;
	private static String restorepath;
	
	public static void init(){
		Properties p = new Properties();
		InputStream in = DataBaseBackUp.class.getResourceAsStream("/com/xu/util/db.properties");
		 try {
			 p.load(in);  
			 in.close();
			 backuppath = (String) p.get("backuppath");
			 restorepath = (String) p.get("restorepath");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
    @Value("${db_home_path}")
	private String dbPath;
    @Value("${store_path}")
    private String storePath;
	public  void backup() {
		//init();
        try {
            Runtime rt = Runtime.getRuntime();
 
            // 调用 调用mysql的安装目录的命令
            Process child = rt
                    .exec(dbPath+"mysqldump -h localhost -uroot -proot jxc");
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
 
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
 
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            // 要用来做导入用的sql目标文件：
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
            FileOutputStream fout;
            System.out.println(new File(storePath).mkdirs());

            fout = new FileOutputStream(storePath+sdf.format(new Date())+".sql");



            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
	
	/**
	 * 备份数据恢复
	 * @param databaseName
	 */
	public   void restore(String fileName) {
		//init();
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec(dbPath +"mysql.exe -hlocalhost -uroot -p123 --default-character-set=utf8 jxc");
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(storePath+fileName), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            // System.out.println(str);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                    "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
