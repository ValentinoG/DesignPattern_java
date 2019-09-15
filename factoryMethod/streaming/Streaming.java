/*Simulazione della generazione di una fonte di streaming che da al client la stessa interfaccia per
gestirlo attraverso un factory method*/

package designPattern.factoryMethod.streaming;


interface IStream{
	public void start();
	public void stop();
}

class AudioVideoStream implements IStream{
	private String audioSource;
	private String videoSource;
	public AudioVideoStream(String audioSource, String videoSource){
		this.audioSource=audioSource;
		this.videoSource=videoSource;
	}
	public void start(){
		System.out.println("Audio source: "+audioSource+" video source: "+videoSource);
	}
	public void stop(){
		System.out.println("stopping audio and video");
	}
}

class VideoStream implements IStream{
	private String videoSource;
	public VideoStream(String videoSource){
		this.videoSource=videoSource;
	}
	public void start(){
		System.out.println("Video source: "+videoSource);
	}
	public void stop(){
		System.out.println("stopping video");
	}
}

class AudioStream implements IStream{
	private String audioSource;
	public AudioStream(String audioSource){
		this.audioSource=audioSource;
	}
	public void start(){
		System.out.println("Audio source: "+audioSource);
	}
	public void stop(){
		System.out.println("stopping audio");
	}
}


class CStreaming{
	private CStreaming(){}
	public static IStream addStreaming(int selector){
		IStream selected=null;
		switch(selector){
			case 0:
				selected=new AudioStream("microphone");
				break;
			case 1:
				selected=new VideoStream("webcam");
				break;
			case 2:
				selected=new AudioVideoStream("microphone","webcam");
				break;
		}
		return selected;
	}
}

public class Streaming{
	public static void main(String[] args){

		System.out.println("Creo uno streaming audio");
		IStream streamingAudio=CStreaming.addStreaming(0);
		streamingAudio.start();

		System.out.println("Creo uno streaming video");
		IStream streamingVideo=CStreaming.addStreaming(1);
		streamingVideo.start();

		System.out.println("Creo uno streaming audio/video");
		IStream streamingAudioVideo=CStreaming.addStreaming(2);
		streamingAudioVideo.start();

		streamingAudio.stop();
		streamingVideo.stop();
		streamingAudioVideo.stop();
	}
}