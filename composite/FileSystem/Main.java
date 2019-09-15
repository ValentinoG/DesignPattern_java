/*Simulazione di un File System attraverso il design patter in composite*/


package designPattern.composite.FileSystem;

import java.util.LinkedList;

abstract class FileSystem {
	public void add(FileSystem child) {}
	public void remove(FileSystem child) {}
	public abstract void readContent();
}

class File extends FileSystem {
	private String content;
	public File(String textInput) {
		content = textInput;
	}
	public void readContent() {
		System.out.println(content);
	}
}

class Directory extends FileSystem {
	private LinkedList<FileSystem> children = new LinkedList<FileSystem>();
	public void add(FileSystem child) {
		children.add(child);
	}
	public void remove(FileSystem child) {
		children.remove(child);
	}
	public void readContent() {
		for (int i = 0; i < children.size(); i++)
			children.get(i).readContent();
	}
}



public class Main {
	public static void main(String[] args) {
		Directory nomi=new Directory();
		File file1 = new File("Valentino");
		File file2 = new File("Giorgia");
		File file3 = new File("Enrico");
		File file4 = new File("Barbara");
		nomi.add(file1);
		nomi.add(file2);
		nomi.add(file3);
		nomi.add(file4);
		Directory cani=new Directory();
		File file5 = new File("Milu");
		File file6 = new File("Pupa");
		File file7 = new File("Laki");
		File file8 = new File("Fuffi");
		File file9 = new File("Ettore");
		File file10 = new File("Argo");
		cani.add(file5);
		cani.add(file6);
		cani.add(file7);
		cani.add(file8);
		cani.add(file9);
		cani.add(file10);
		Directory cartella1 = new Directory();
		File file=new File("File senza contenuto");
		cartella1.add(nomi);
		cartella1.add(cani);
		cartella1.add(file);
		cartella1.readContent();

	}
}