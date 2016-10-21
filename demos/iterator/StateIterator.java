package demos.iterator;

import java.util.Iterator;
//import Annotations.Typestate;

@Typestate("StateIteratorProtocol")
class StateIterator /*typestate StateIteratorProtocol*/ {
	private int head;
	private int current;
	private MyArray a;

	public StateIterator(MyArray a) {
		this.a = a;
		head = 0;
		current = 0;
	}

	public Object next() {
		current = head;
		return a.get(head++);
	}

	public Boolean hasNext() {
		for(;head < a.size() && a.get(head) == null; head++);

		if(head >= a.size())
			return Boolean.False;
		return Boolean.True;
	}

	public Object remove() {
		return a.remove(current);
	}
}
