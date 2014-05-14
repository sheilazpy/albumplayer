package todd09.albumplayer;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class APImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image mImage;

	public APImagePanel() {
		super();
	}

	public void setImage(String imageFile) {
		try {
			mImage = ImageIO.read(new File(imageFile));
			this.invalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		if (mImage != null)
			g.drawImage(mImage, 50, 10, 200, 200, this); // at location 50,10
	}
}