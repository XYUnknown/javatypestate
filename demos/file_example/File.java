package demos.file_example;
import java.io.*;
class File  {
	private MyBufferedReader reader;
	private String file;
	private char[] readBuffer;
	private int i;
	File(String file){
		this.file = file;
		reader = new MyBufferedReader(file);
		readBuffer = new char[1024];
		i = 0;
	}
	Status open() {
		if(reader.open()) return new Status(Status.OK);
		return new Status(Status.ERROR);
	}
	void close() {
		reader.close();
	}
	BooleanEnum hasNext() {
		if(reader.ready()) return new BooleanEnum(BooleanEnum.TRUE);
		return new BooleanEnum(BooleanEnum.FALSE);
	}
	void read() {
		readBuffer[i++] = reader.read();
	}
	public static void main(String[] args) {
		File myFile = new File("file.txt");
		File a = myFile;
		processFile(a);
	}
	public static void processFile(File myFile) {
		switch(myFile.open().getEnum()){
			case Status.OK:
			loop:
			while(true) {
				switch(myFile.hasNext().getEnum()){
					case BooleanEnum.TRUE:
					myFile.read();
					break;
					case BooleanEnum.FALSE:
					break loop;
				}
			}
			myFile.close();
			break;
			case Status.ERROR:
			System.out.println("file not found!!!!");
			break;
		}
	}
}
