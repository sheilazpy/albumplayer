package todd09.albumplayer;

import java.io.File;
import java.util.ArrayList;

public class APSettingsManager {

	public enum ImageSwitchMode {
		normal, random;
	}

	public interface OnSettingsChangedListner {
		public void onSettingsChanged(SettingsField changedFiled);
	}

	public static class Settings {

		String imageDirectory;

		int imageSwitchIntervalSeconds;

		ImageSwitchMode imageSwitchMode;

		String musicDirectory;
	}

	public enum SettingsField {
		ImageDirectory, ImageSwitchIntervalSeconds, ImageSwtichMode, MusicDirectory;
	}

	private static APSettingsManager mInstance;

	public static APSettingsManager getInstance() {
		if (mInstance == null)
			mInstance = new APSettingsManager();
		return mInstance;
	}

	private static <T> boolean isEqual(T obj1, T ojb2) {
		return (obj1 == ojb2) || (obj1 != null && obj1.equals(ojb2));
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
		this.mSettings.imageSwitchIntervalSeconds = 6;
		this.mSettings.imageSwitchMode = ImageSwitchMode.normal;
		this.onSettingsChangedListners = new ArrayList<>();
	}

	public String getImageDirectory() {
		return this.mSettings.imageDirectory;
	}

	public int getImageSwitchIntervalSeconds() {
		return this.mSettings.imageSwitchIntervalSeconds;
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

	private void notifySettingsChanged(SettingsField changedField) {
		for (OnSettingsChangedListner listener : onSettingsChangedListners) {
			listener.onSettingsChanged(changedField);
		}
	}

	public void registerOnSettingsChangedListener(
			OnSettingsChangedListner listner) {
		onSettingsChangedListners.add(listner);
	}

	public void setImageDirectory(String imageDirectory) {
		if (isEqual(imageDirectory, this.mSettings.imageDirectory)) {
			return;
		}
		this.mSettings.imageDirectory = imageDirectory;
		notifySettingsChanged(SettingsField.ImageDirectory);

	}

	public void setImageSwitchIntervalSeconds(int seconds) {
		if (isEqual(seconds, mSettings.imageSwitchIntervalSeconds)) {
			return;
		}
		this.mSettings.imageSwitchIntervalSeconds = seconds;
		notifySettingsChanged(SettingsField.ImageSwitchIntervalSeconds);
	}

	public void setImageSwitchMode(ImageSwitchMode mode) {
		if (isEqual(mode, mSettings.imageSwitchMode))
			return;
		this.mSettings.imageSwitchMode = mode;
		notifySettingsChanged(SettingsField.ImageSwtichMode);
	}

	public void setMusicDirectory(String musicDirectory) {
		if (isEqual(musicDirectory, mSettings.musicDirectory))
			return;
		this.mSettings.musicDirectory = musicDirectory;
		notifySettingsChanged(SettingsField.MusicDirectory);
	}

	public void unregisterAllOnSettingsChangedListener() {
		this.onSettingsChangedListners.clear();
	}

	public void unregisterOnSettingsChangedListener(
			OnSettingsChangedListner listner) {
		onSettingsChangedListners.remove(listner);
	}
}
