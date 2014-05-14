package todd09.albumplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.UIManager;

import todd09.albumplayer.APSettingsManager.ImageSwitchMode;

public class APUtils {

	public static ArrayList<String> retrieveImageFiles() {
		ArrayList<String> imageFiles = new ArrayList<>();
		String dir = APSettingsManager.getInstance().getImageDirectory();
		if (dir == null)
			return null;
		File imageDirFile = new File(dir);
		if (!imageDirFile.exists())
			return null;
		for (File file : imageDirFile.listFiles()) {
			if (file.isDirectory() || !file.canRead())
				continue;
			String fileName = file.getName().toLowerCase();
			if (fileName.endsWith(".jpg") || fileName.endsWith(".gif")
					|| fileName.endsWith(".png")) {
				imageFiles.add(file.getAbsolutePath());
			}
		}
		ImageSwitchMode mode = APSettingsManager.getInstance()
				.getImageSwitchMode();
		if (ImageSwitchMode.random.equals(mode)) {
			Collections.shuffle(imageFiles);
		} else if (ImageSwitchMode.normal.equals(mode)) {
			// Collections.sort(imageFiles);
		}
		return imageFiles;
	}

	public static ArrayList<String> retrieveMusicFiles() {
		ArrayList<String> musicFiles = new ArrayList<>();
		String dir = APSettingsManager.getInstance().getMusicDirectory();
		if (dir == null)
			return null;
		File musicDirFile = new File(dir);
		if (!musicDirFile.exists())
			return null;
		for (File file : musicDirFile.listFiles()) {
			if (file.isDirectory() || !file.canRead())
				continue;
			String fileName = file.getName().toLowerCase();
			if (fileName.endsWith(".mp3") || fileName.endsWith(".wav")) {
				musicFiles.add(file.getAbsolutePath());
			}
		}
		return musicFiles;
	}

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Fail to setLookAndFeel.");
		}
	}

}
