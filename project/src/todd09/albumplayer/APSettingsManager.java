package todd09.albumplayer;

import java.io.File;
import java.util.ArrayList;

public class APSettingsManager {

	public enum ImageSwitchMode {
		normal, random;
	}

	public interface OnSettingsChangedListner {
		public void onSettingsChanged();
	}

	public static class Settings {

		String imageDirectory;

		int imageSwitchIntevalSeconds;

		ImageSwitchMode imageSwitchMode;

		String musicDirectory;
	}

	private static APSettingsManager mInstance;

	public static APSettingsManager getInstance() {
		if (mInstance == null)
			mInstance = new APSettingsManager();
		return mInstance;
	}

	private Settings mSettings;
	private ArrayList<OnSettingsChangedListner> onSettingsChangedListners;

	private APSettingsManager() {
		this.mSettings = new Settings();
		// 设置默认值
		String workDir = System.getProperty("user.dir");
		this.mSettings.imageDirectory = new File(workDir, "data/images/")
				.getAbsolutePath();
		this.mSettings.musicDirectory = new File(workDir, "data/musics/")
				.getAbsolutePath();
		this.mSettings.imageSwitchIntevalSeconds = 6;
		this.mSettings.imageSwitchMode = ImageSwitchMode.normal;
		this.onSettingsChangedListners = new ArrayList<>();
	}

	public String getImageDirectory() {
		return this.mSettings.imageDirectory;
	}

	public int getImageSwitchIntervalSeconds() {
		return this.mSettings.imageSwitchIntevalSeconds;
	}

	public ImageSwitchMode getImageSwitchMode() {
		return this.mSettings.imageSwitchMode;
	}

	public String getMusicDirectory() {
		return this.mSettings.musicDirectory;
	}

	public Settings getSettings() {
		return this.mSettings;
	}

	private void notifySettingsChanged() {
		for (OnSettingsChangedListner listener : onSettingsChangedListners) {
			listener.onSettingsChanged();
		}
	}

	public void registerOnSettingsChangedListener(
			OnSettingsChangedListner listner) {
		onSettingsChangedListners.add(listner);
	}

	public void setImageDirectory(String imageDirectory) {
		this.mSettings.imageDirectory = imageDirectory;
		notifySettingsChanged();
	}

	public void setImageSwitchIntervalSeconds(int seconds) {
		this.mSettings.imageSwitchIntevalSeconds = seconds;
		notifySettingsChanged();
	}

	public void setImageSwitchMode(ImageSwitchMode mode) {
		this.mSettings.imageSwitchMode = mode;
		notifySettingsChanged();
	}

	public void setMusicDirectory(String musicDirectory) {
		this.mSettings.musicDirectory = musicDirectory;
		notifySettingsChanged();
	}

	public void unregisterAllOnSettingsChangedListener() {
		this.onSettingsChangedListners.clear();
	}

	public void unregisterOnSettingsChangedListener(
			OnSettingsChangedListner listner) {
		onSettingsChangedListners.remove(listner);
	}
}
