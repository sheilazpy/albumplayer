package todd09.albumplayer;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class APDirectoryChoosePanel extends Panel implements ActionListener {

	public interface OnDirectoryChoosedListener {
		public void onDirectoryChoosed(int panelId, String directory);
	}

	private static final String DCPAC_CHOOSE_DIRECTORY = "DCPAC_CHOOSE_DIRECTORY";
	public static final int ID_IMAGE = 1300;
	public static final int ID_MUSIC = 1301;
	private static final long serialVersionUID = 1L;

	private JLabel directoryLabel;
	private JFileChooser mFileChooser;
	private int mId;
	private OnDirectoryChoosedListener mListener;

	public APDirectoryChoosePanel(int id, String defaultDirectory) {
		super();
		mId = id;
		mFileChooser = new JFileChooser();
		mFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		mFileChooser.setCurrentDirectory(new File(defaultDirectory));
		if (mId == ID_IMAGE) {
			mFileChooser.setDialogTitle("选择图片文件夹……");
		} else if (mId == ID_MUSIC) {
			mFileChooser.setDialogTitle("选择音乐文件夹……");
		}
		directoryLabel = new JLabel();
		directoryLabel.setText(defaultDirectory);
		JButton chooseButton = new JButton();
		chooseButton.setText("浏览");
		chooseButton.setActionCommand(DCPAC_CHOOSE_DIRECTORY);
		chooseButton.addActionListener(this);
		add(directoryLabel);
		add(chooseButton);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String actionCommand = actionEvent == null ? null : actionEvent
				.getActionCommand();
		if (DCPAC_CHOOSE_DIRECTORY.equals(actionCommand)) {
			int retval = mFileChooser.showOpenDialog(this);
			if (retval == JFileChooser.APPROVE_OPTION) {
				String directory = mFileChooser.getSelectedFile()
						.getAbsolutePath();
				directoryLabel.setText(directory);
				if (mListener != null) {
					mListener.onDirectoryChoosed(mId, directory);
				}
			}
			return;
		}
	}

	public void setOnDirectoryChoosedListener(
			OnDirectoryChoosedListener listener) {
		this.mListener = listener;
	}

}
