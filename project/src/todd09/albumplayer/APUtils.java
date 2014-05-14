package todd09.albumplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.UIManager;

import todd09.albumplayer.APSettingsManager.ImageSwitchMode;

public class APUtils {

	public static final String MSG_ABOUT;
	public static final String MSG_HELP;

	static {
		String ls = System.getProperty("line.separator");

		String aboutMsg = "";
		aboutMsg += "Java相册播放器" + ls + ls;
		aboutMsg += "基于Java+Swing开发" + ls;
		aboutMsg += "感谢JLayer MP3 Library" + ls + ls;
		aboutMsg += "(c)Todd, 2014";
		MSG_ABOUT = aboutMsg;

		String helpMsg = "";
		helpMsg += "Java相册播放器" + ls + ls;
		helpMsg += "如有问题，请联系2306044120@qq.com" + ls + ls;
		helpMsg += "(c)Todd, 2014";
		MSG_HELP = helpMsg;
	}

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
			if (fileName.endsWith(".mp3")) {
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
