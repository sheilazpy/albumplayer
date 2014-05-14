package todd09.albumplayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import todd09.albumplayer.APDirectoryChoosePanel.OnDirectoryChoosedListener;
import todd09.albumplayer.APSettingsManager.ImageSwitchMode;

public class APSettingsDialog extends JDialog implements ActionListener,
		OnDirectoryChoosedListener {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> mImageIntervalComboBox;
	private JComboBox<String> mImageModeComboBox;

	public APSettingsDialog(JFrame parentFrame) {
		super(parentFrame, "设置", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initGUI();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width - getWidth()) / 2,
				(dim.height - getHeight()) / 2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == mImageIntervalComboBox) {
			int[] seconds = { 3, 6, 12, 18 };
			int index = mImageIntervalComboBox.getSelectedIndex();
			if (index < 0 || index > 3)
				return;
			APSettingsManager.getInstance().setImageSwitchIntervalSeconds(
					seconds[index]);
		} else if (source == mImageModeComboBox) {
			ImageSwitchMode[] modes = { ImageSwitchMode.normal,
					ImageSwitchMode.random };
			int index = mImageModeComboBox.getSelectedIndex();
			if (index < 0 || index > 1)
				return;
			APSettingsManager.getInstance().setImageSwitchMode(modes[index]);
		}
	}

	private void initGUI() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// 创建组件
		// 音乐目录
		JLabel musicDirLabel = new JLabel("音乐目录：");
		String musicDir = APSettingsManager.getInstance().getMusicDirectory();
		APDirectoryChoosePanel musicDCP = new APDirectoryChoosePanel(
				APDirectoryChoosePanel.ID_MUSIC, musicDir);
		musicDCP.setOnDirectoryChoosedListener(this);
		// 图片目录
		JLabel imageDirLabel = new JLabel("图片目录：");
		String imageDir = APSettingsManager.getInstance().getImageDirectory();
		APDirectoryChoosePanel imageDCP = new APDirectoryChoosePanel(
				APDirectoryChoosePanel.ID_IMAGE, imageDir);
		imageDCP.setOnDirectoryChoosedListener(this);
		// 图片播放速度
		JLabel imageIntervalLabel = new JLabel("图片播放速度：");
		String[] imageIntervals = { "快（3s）", "中（6s）", "慢（12s）", "很慢（18s）" };
		mImageIntervalComboBox = new JComboBox<>(imageIntervals);
		mImageIntervalComboBox.setSelectedIndex(1);
		mImageIntervalComboBox.addActionListener(this);
		// 图片播放模式
		JLabel imageModeLabel = new JLabel("图片播放模式：");
		String[] imageModes = { "顺序循环", "随机循环" };
		mImageModeComboBox = new JComboBox<>(imageModes);
		mImageModeComboBox.addActionListener(this);

		// 添加组件到面板
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(4, 2, 4, 2);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(musicDirLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		panel.add(musicDCP, c);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(imageDirLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		panel.add(imageDCP, c);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(imageIntervalLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		panel.add(mImageIntervalComboBox, c);

		c.gridx = 0;
		c.gridy = 3;
		panel.add(imageModeLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		panel.add(mImageModeComboBox, c);

		setContentPane(panel);
		pack();
		setResizable(false);
	}

	@Override
	public void onDirectoryChoosed(int panelId, String directory) {
		if (panelId == APDirectoryChoosePanel.ID_IMAGE) {
			APSettingsManager.getInstance().setImageDirectory(directory);
		} else if (panelId == APDirectoryChoosePanel.ID_MUSIC) {
			APSettingsManager.getInstance().setMusicDirectory(directory);
		}
	}

}
