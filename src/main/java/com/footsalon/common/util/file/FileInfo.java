package com.footsalon.common.util.file;

import com.footsalon.common.code.Config;
import com.footsalon.team.Team;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class FileInfo {
	
	@Id
	@GeneratedValue
	private Long flIdx;

	private String originFileName;
	private String renameFileName;
	private String savePath;

	@Column(columnDefinition = "date default sysdate")
	private LocalDate regDate;

	@Column(columnDefinition = "char default 'N'")
	private String delYn;
	
	public String getLink() {
		return Config.DOMAIN.DESC + "/file/" + savePath + renameFileName;
	}
	
	public String getDownloadPath() {
		return Config.UPLOAD_PATH.DESC + savePath+ renameFileName;
	}
}
