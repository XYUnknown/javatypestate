package demos.Traversal;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
class CRole  {
	SessionSocket a, b;
	CRole(int Aport, int Bport){
		try {
			a = new SessionSocket(new Socket("localhost", Aport));
			b = new SessionSocket(new Socket("localhost", Bport));
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(+1);
		}
	}
	void DATAToA() {
		a.send(new Choice(Choice.DATA));
	}
	void DATAToB() {
		b.send(new Choice(Choice.DATA));
	}
	void NO_DToA() {
		a.send(new Choice(Choice.NO_D));
	}
	void NO_DToB() {
		b.send(new Choice(Choice.NO_D));
	}
	void ENDToA() {
		a.send(new Choice(Choice.END));
	}
	void ENDToB() {
		b.send(new Choice(Choice.END));
	}
	void nodeToA(Node n) {
		a.send(n);
	}
	void nodeToB(Node n) {
		b.send(n);
	}
	Node nodeFromA() {
		return (Node) a.receiveObject();
	}
	Node nodeFromB() {
		return (Node) b.receiveObject();
	}
	Choice choiceFromA() {
		return (Choice) a.receiveObject();
	}
	Choice choiceFromB() {
		return (Choice) b.receiveObject();
	}
}
