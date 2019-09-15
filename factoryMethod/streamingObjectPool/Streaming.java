/*stessa cosa dell'esempio precedente, ma qui si usa un objectpool*/

package designPattern.factoryMethod.streamingObjectPool;
import java.util.LinkedList;

interface IStream {
	public void start();
	public void stop();
}

class AudioVideoStream implements IStream {
	private String audioSource;
	private String videoSource;
	public AudioVideoStream(String audioSource, String videoSource) {
		this.audioSource = audioSource;
		this.videoSource = videoSource;
	}
	public void start() {
		System.out.println("Audio source: " + audioSource + " video source: " + videoSource);
	}
	public void stop() {
		System.out.println("stopping audio and video");
	}
}

class VideoStream implements IStream {
	private String videoSource;
	public VideoStream(String videoSource) {
		this.videoSource = videoSource;
	}
	public void start() {
		System.out.println("Video source: " + videoSource);
	}
	public void stop() {
		System.out.println("stopping video");
	}
}

class AudioStream implements IStream {
	private String audioSource;
	public AudioStream(String audioSource) {
		this.audioSource = audioSource;
	}
	public void start() {
		System.out.println("Audio source: " + audioSource);
	}
	public void stop() {
		System.out.println("stopping audio");
	}
}

//creator con pool di oggetti
class CStreaming {
	private CStreaming() {}
	private static LinkedList<AudioVideoStream> audioVideoStreamPool = new LinkedList<AudioVideoStream>();
	private static LinkedList<VideoStream> pool = new LinkedList<VideoStream>();
	private static LinkedList<AudioStream> pool = new LinkedList<AudioStream>();

	private static final int SIZE = 3;
	public static IStream addStreaming(int selector) {
		IStream selected = null;
		int currentSize = pool.size();
		if (currentSize == SIZE) {
			System.out.println("Too many stream-objs");
			return null;
		}
		switch (selector) {
		case 0:
			if (currentSize > 0)
				selected = new AudioStream("microphone");
			break;
		case 1:
			selected = new VideoStream("webcam");
			break;
		case 2:
			selected = new AudioVideoStream("microphone", "webcam");
			break;
		}

		return selected;
	}
}

public class Streaming {
	public static void main(String[] args) {

		System.out.println("Creo uno streaming audio");
		IStream streamingAudio = CStreaming.addStreaming(0);
		streamingAudio.start();

		System.out.println("Creo uno streaming video");
		IStream streamingVideo = CStreaming.addStreaming(1);
		streamingVideo.start();

		System.out.println("Creo uno streaming audio/video");
		IStream streamingAudioVideo = CStreaming.addStreaming(2);
		streamingAudioVideo.start();

		streamingAudio.stop();
		streamingVideo.stop();
		streamingAudioVideo.stop();
	}
}