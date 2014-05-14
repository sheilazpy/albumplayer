package todd09.albumplayer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class APFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private APImagePanel mImagePanel;

	public APFrame() {
		super();
		initGUI();
		APMusicManager.getInstance().startPlay();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String actionCommand = actionEvent == null ? null : actionEvent
				.getActionCommand();
		if (actionCommand == null)
			return;

		if (APMenuBar.MBAC_PLAY_SETTING.equals(actionCommand)) {
			new APSettingsDialog(this);
			return;
		}

		if (APMenuBar.MBAC_PLAY_SWTICH_MUSIC.equals(actionCommand)) {
			APMusicManager.getInstance().playNextMusic();
			return;
		}

		if (APMenuBar.MBAC_PLAY_EXIT.equals(actionCommand)) {
			System.exit(0);
			return;
		}

		if (APMenuBar.MBAC_HELP_HELP.equals(actionCommand)) {
			JOptionPane.showMessageDialog(this, APUtils.MSG_HELP, "帮助",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (APMenuBar.MBAC_HELP_ABOUT.equals(actionCommand)) {
			JOptionPane.showMessageDialog(this, APUtils.MSG_ABOUT, "关于",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

	}

	private void initGUI() {
		// 设置整体外观
		APUtils.setLookAndFeel();
		setTitle("Java相册播放器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width - getWidth()) / 2,
				(dim.height - getHeight()) / 2);

		// 设置菜单栏
		setJMenuBar(new APMenuBar(this));

		// 设置画布
		mImagePanel = new APImagePanel();
		setContentPane(mImagePanel);
		mImagePanel.startPlay();
		setVisible(true);
	}
}
