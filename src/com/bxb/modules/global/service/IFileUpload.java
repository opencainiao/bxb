package com.bxb.modules.global.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bxb.modules.global.model.Attachment;
import com.mongodb.gridfs.GridFSDBFile;

/****
 * 全局文件上传Service
 * 
 * @author NBQ
 *
 */
public interface IFileUpload {

	/****
	 * 上传一个文件，以新文件名命名，上传到指定目录
	 * 
	 * @param attach
	 * @param newFileName
	 * @param dirpath
	 * @throws IOException
	 */
	public File doUploadOneFileToServerDisk(MultipartFile attach, String newFileName,
			String dirpath) throws IOException;

	/****
	 * 上传一个附件
	 * 
	 * @param attach
	 * @param request
	 * @param dirpath
	 * @param needCompress
	 * @return
	 * @throws IOException
	 */
	public Attachment uploadOneAttachmentToServerDisk(MultipartFile attach,
			HttpServletRequest request, String dirpath, boolean needCompress, List<ThumbParam> tps)
					throws IOException;

	/****
	 * 删除一个附件<br>
	 * 1.删除数据库中的信息 <br>
	 * 2.删除存储目录中的文件 <br>
	 * 3.如果文件是图片，删除缩略图的文件
	 * 
	 * @param _id_m
	 */
	public void deleteOneAttachment(String _id_m, HttpServletRequest request);

	/****
	 * 上传一个文件，以新文件名命名，上传到MongoDB数据库
	 * 
	 * 以文件的MD5值做id
	 * 
	 * @param attach
	 * @param newFileName
	 * @return 文件的ID
	 */
	public String doUploadOneFileToMongo(MultipartFile attach, String newFileName)
			throws IOException;

	/****
	 * 上传一个附件
	 * 
	 * @param attach
	 * @param request
	 * @param needCompress
	 * @param tps
	 * @return
	 * @throws IOException
	 */
	public Attachment uploadOneAttachmentToMongo(MultipartFile attach, HttpServletRequest request,
			boolean needCompress, List<ThumbParam> tps) throws IOException;

	/****
	 * 根据_id查询存储在mongodb中的文件
	 * 
	 * @param _id
	 * @return
	 */
	public GridFSDBFile getById(String _id);

	/****
	 * 根据_id查询附件信息
	 * 
	 * @param _id
	 * @return
	 */
	public Attachment getAttachMent(String _id);
}
