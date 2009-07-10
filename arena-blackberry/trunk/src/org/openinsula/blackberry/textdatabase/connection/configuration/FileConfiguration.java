package org.openinsula.blackberry.textdatabase.connection.configuration;

public class FileConfiguration extends ConnectorConfiguration {

	private String filePath;

	private boolean deleteExistentFile;

	private boolean createNotExistentFile;

	public FileConfiguration() {
	}

	public FileConfiguration(String filePath) {
		this.filePath = filePath;
	}

	public int getBeanSeparator() {
		return '^';
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public boolean isDeleteExistentFile() {
		return deleteExistentFile;
	}

	public boolean isCreateNotExistentFile() {
		return createNotExistentFile;
	}

	public void setDeleteExistentFile(boolean deleteExistentFile) {
		this.deleteExistentFile = deleteExistentFile;
	}

	public void setCreateNotExistentFile(boolean createNotExistentFile) {
		this.createNotExistentFile = createNotExistentFile;
	}

}
