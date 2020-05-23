package com.sectorseven.model.common;

import org.springframework.web.multipart.MultipartFile;


public class FileBucket {

	MultipartFile file;
	
	String description;
	
    String authorName;
	
    String datePublished;
	
    String videoUrl;

    String audioUrl;

    SevenSigma sevenSigma;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public SevenSigma getSevenSigma() {
		return sevenSigma;
	}

	public void setSevenSigma(SevenSigma sevenSigma) {
		this.sevenSigma = sevenSigma;
	}


}