package com.footsalon.common.util.file;

import com.footsalon.common.code.Config;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class FileInfo {
	
	@Id
	@GeneratedValue
	private Long flIdx;
	private String tmCoe;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private LocalDate regDate;
	private String delYn;
	
	public String getLink() {
		return Config.DOMAIN.DESC + "/file/" + savePath + renameFileName;
	}
	
	public String getDownloadPath() {
		return Config.UPLOAD_PATH.DESC + savePath+ renameFileName;
	}
}
