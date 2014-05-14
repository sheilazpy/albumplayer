package todd09.albumplayer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class APImagePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Image mImage;
	private ArrayList<String> mImageFiles;
	private int mLastImageFileIndex;
	private Timer mTimer;

	public APImagePanel() {
		super();
		mTimer = new Timer(1000, this);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() instanceof Timer) {
			if (mImageFiles == null || mImageFiles.size() == 0)
				return;
			int index = (mLastImageFileIndex + 1) % mImageFiles.size();
			mLastImageFileIndex = index;
			String file = mImageFiles.get(index);
			setImage(file);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (mImage != null) {
			// 缩放至居中显示
			int panelWidth = getWidth();
			int panelHeight = getHeight();
			int imageWidth = mImage.getWidth(this);
			int imageHeight = mImage.getHeight(this);
			float scaleRate = Math.min(1f * panelWidth / imageWidth, 1f
					* panelHeight / imageHeight);
			int displayWidth = (int) (imageWidth * scaleRate);
			int displayHeight = (int) (imageHeight * scaleRate);
			g.drawImage(mImage, (panelWidth - displayWidth) / 2,
					(panelHeight - displayHeight) / 2, displayWidth,
					displayHeight, this);
		}
	}

	public void setImage(String imageFile) {
		try {
			mImage = ImageIO.read(new File(imageFile));
			this.updateUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startPlay() {
		int delay = APSettingsManager.getInstance()
				.getImageSwitchIntervalSeconds() * 1000;
		mTimer.setDelay(delay);
		mImageFiles = APUtils.retrieveImageFiles();
		if (mTimer.isRunning()) {
			mTimer.restart();
		} else {
			mTimer.start();
		}
	}
}