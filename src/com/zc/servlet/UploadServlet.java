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

		// 1. �õ� FileItem �ļ��� items
		// Create a factory for disk-based file items
		//�������̹���
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
//		FileCleaningTracker fileCleaningTracker = 
//				FileCleanerCleanup.getFileCleaningTracker(getServletContext());
//		factory.setFileCleaningTracker(fileCleaningTracker);
		
		// Set factory constraints
		//�����ڴ滺��������ֵ.һ���ļ���С��������ֵ.�ļ�����д��Ӳ����
		factory.setSizeThreshold(1024 * 500);
		//setRepository(tempDirectory)������ʱ�洢�ļ���Ŀ¼.���ļ������˵���setSizeThreshold()�������õ���ֵ.�ļ�����д����Ŀ¼��
		File tempDirectory = new File("d:\\tempDirectory");
		factory.setRepository(tempDirectory);

		// Create a new file upload handler
		//����������
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		//�ϴ��ļ�������С
		upload.setSizeMax(1024 * 1024 * 5);

		// Parse the request
		try {
			//parseRequest(request):���ڴ����ϴ�������.����FileItem�����List.
			List<FileItem> /* FileItem */items = upload.parseRequest(request);

			// 2. ���� items:
			for (FileItem item : items) {
				// ����һ��һ��ı���, ���ı�.��ӡ��Ϣ
				if (item.isFormField()) {
					String name = item.getFieldName();
					//ʹ��ָ���ı��뷽ʽ.���ַ�����ʽ�����ļ������������
					String value = item.getString();

					System.out.println(name + ": " + value);
					
					
				}
				// �����ļ�������ļ����浽 d:\\files Ŀ¼��.
				else {
					//�����ļ��������Ӧ�ı��е��ֶε�����
					String fieldName = item.getFieldName();
					//���ؿͻ����ļ�ϵͳ���ļ���ԭʼ�ļ���
					String fileName = item.getName();
					//���ؿͻ�����������õ��ļ��������MIME����
					String contentType = item.getContentType();
					//��ȡ�ļ�������Ĵ�С
					long sizeInBytes = item.getSize();
						
					//�ļ���:������Ϊ  ----�ļ�
					System.out.println(fieldName);
					//����ļ���
					System.out.println(fileName);
					//����ĵ�����
					System.out.println(contentType);
					System.out.println(sizeInBytes);

					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					//����֮����ļ���.�ļ���Ŀ¼����д��
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
