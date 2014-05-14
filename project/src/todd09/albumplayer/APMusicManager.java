package todd09.albumplayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import todd09.albumplayer.APSettingsManager.OnSettingsChangedListner;
import todd09.albumplayer.APSettingsManager.SettingsField;

public class APMusicManager implements OnSettingsChangedListner {

	private class APPlaybackListner extends PlaybackListener {
		@Override
		public void playbackFinished(PlaybackEvent event) {
			super.playbackFinished(event);
			playNextMusic();
		}
	}

	private static APMusicManager instance;

	public static APMusicManager getInstance() {
		if (instance == null) {
			instance = new APMusicManager();
		}
		return instance;
	}

	private int mLastMusicIndex = 0;
	private APPlaybackListner mListner;
	private ArrayList<String> mMusicFiles;
	private AdvancedPlayer mPlayer;

	private APMusicManager() {
		mListner = new APPlaybackListner();
		APSettingsManager.getInstance().registerOnSettingsChangedListener(this);
	}

	private void closeCurrentPlayer() {
		if (mPlayer != null) {
			mPlayer.close();
		}
	}

	@Override
	public void onSettingsChanged(SettingsField changedFiled) {
		if (SettingsField.MusicDirectory.equals(changedFiled)) {
			startPlay();
		}
	}

	public void playNextMusic() {
		if (mMusicFiles == null || mMusicFiles.size() == 0) {
			return;
		}
		closeCurrentPlayer();
		int index = (mLastMusicIndex + 1) % mMusicFiles.size();
		final String musicFile = mMusicFiles.get(index);
		mLastMusicIndex = index;

		new Thread() {
			public void run() {
				try {
					BufferedInputStream buffer = new BufferedInputStream(
							new FileInputStream(musicFile));
					mPlayer = new AdvancedPlayer(buffer);
					mPlayer.setPlayBackListener(mListner);
					mPlayer.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	public void startPlay() {
		mMusicFiles = APUtils.retrieveMusicFiles();
		closeCurrentPlayer();
		playNextMusic();
	}

}
