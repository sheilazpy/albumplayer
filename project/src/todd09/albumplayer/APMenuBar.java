package todd09.albumplayer;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class APMenuBar extends JMenuBar {

	private ActionListener mActionListener;

	public final static String MBAC_PLAY_START = "MBAC_PLAY_START";
	public final static String MBAC_PLAY_SETTING = "MBAC_PLAY_SETTINGS";
	public final static String MBAC_PLAY_SWTICH_MUSIC = "MBAC_PLAY_SWITCH_MUSIC";
	public final static String MBAC_PLAY_EXIT = "MBAC_PLAY_EXIT";
	public final static String MBAC_HELP_HELP = "MBAC_HELP_HELP";
	public final static String MBAC_HELP_ABOUT = "MBAC_HELP_ABOUT";

	private static final long serialVersionUID = 1L;

	public APMenuBar(ActionListener actionListener) {
		this.mActionListener = actionListener;
		addMenus();
	}

	private void addMenus() {
		// “播放”菜单
		JMenu playMenu = new JMenu("播放");
		// addMenuItem(playMenu, "开始", MBAC_PLAY_START);
		addMenuItem(playMenu, "设置", MBAC_PLAY_SETTING);
		addMenuItem(playMenu, "切换音乐", MBAC_PLAY_SWTICH_MUSIC);
		addMenuItem(playMenu, "退出", MBAC_PLAY_EXIT);
		add(playMenu);

		// “帮助”菜单
		JMenu helpMenu = new JMenu("帮助");
		addMenuItem(helpMenu, "帮助", MBAC_HELP_HELP);
		addMenuItem(helpMenu, "关于", MBAC_HELP_ABOUT);
		add(helpMenu);
	}

	private void addMenuItem(JMenu targetMenu, String name, String command) {
		if (targetMenu == null)
			return;
		JMenuItem item = new JMenuItem(name);
		item.setActionCommand(command);
		if (mActionListener != null)
			item.addActionListener(mActionListener);
		targetMenu.add(item);
	}

}
