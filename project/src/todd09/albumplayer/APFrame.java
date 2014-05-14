package todd09.albumplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class APFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private APImagePanel mImagePanel;

	public APFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		// 设置整体外观
		APUtils.setLookAndFeel();
		setTitle("Java相册播放器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 360);

		// 设置菜单栏
		setJMenuBar(new APMenuBar(this));

		// 设置画布
		mImagePanel = new APImagePanel();
		setContentPane(mImagePanel);
		mImagePanel.startPlay();
		setVisible(true);
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
		System.out.println(actionCommand);
	}
}
