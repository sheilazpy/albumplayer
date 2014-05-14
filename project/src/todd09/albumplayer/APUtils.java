package todd09.albumplayer;

import javax.swing.UIManager;

public class APUtils {

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Fail to setLookAndFeel.");
		}
	}

}
