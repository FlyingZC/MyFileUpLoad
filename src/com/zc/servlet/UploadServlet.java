package com.zc.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(name = "uploadServlet", urlPatterns = { "/uploadServlet" })
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 1. 得到 FileItem 的集合 items
		// Create a factory for disk-based file items
		//创建磁盘工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
//		FileCleaningTracker fileCleaningTracker = 
//				FileCleanerCleanup.getFileCleaningTracker(getServletContext());
//		factory.setFileCleaningTracker(fileCleaningTracker);
		
		// Set factory constraints
		//设置内存缓冲区的阈值.一旦文件大小超出该阈值.文件将被写到硬盘上
		factory.setSizeThreshold(1024 * 500);
		//setRepository(tempDirectory)设置临时存储文件的目录.当文件超出了调用setSizeThreshold()方法设置的阈值.文件将被写到该目录下
		File tempDirectory = new File("d:\\tempDirectory");
		factory.setRepository(tempDirectory);

		// Create a new file upload handler
		//创建处理工具
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		//上传文件的最大大小
		upload.setSizeMax(1024 * 1024 * 5);

		// Parse the request
		try {
			//parseRequest(request):用于处理上传的数据.返回FileItem对象的List.
			List<FileItem> /* FileItem */items = upload.parseRequest(request);

			// 2. 遍历 items:
			for (FileItem item : items) {
				// 若是一个一般的表单域, 即文本.打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					//使用指定的编码方式.以字符串形式返回文件数据项的内容
					String value = item.getString();

					System.out.println(name + ": " + value);
					
					
				}
				// 若是文件域则把文件保存到 d:\\files 目录下.
				else {
					//返回文件数据项对应的表单中的字段的名称
					String fieldName = item.getFieldName();
					//返回客户端文件系统中文件的原始文件名
					String fileName = item.getName();
					//返回客户端浏览器设置的文件数据项的MIME类型
					String contentType = item.getContentType();
					//获取文件数据项的大小
					long sizeInBytes = item.getSize();
						
					//文件域:输出结果为  ----文件
					System.out.println(fieldName);
					//输出文件名
					System.out.println(fileName);
					//输出文档类型
					System.out.println(contentType);
					System.out.println(sizeInBytes);

					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					//保存之后的文件名.文件夹目录不能写错
					fileName = "d:\\files\\" + fileName;
					System.out.println(fileName);

					OutputStream out = new FileOutputStream(fileName);

					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}

					out.close();
					in.close();
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}

}
