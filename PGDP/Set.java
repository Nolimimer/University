public class Set<T> {

	public final List<T> head;
	public final List<T> next;

	public Set() {
		this.next = null;
		this.head = null;
		List<T> s = new List<T>(null, null);
	}

	public asdf<T> add(T e) {
		asdf<T> s = new asdf();
		if (e == null) {
			throw new NullPointerException();
		}

		if (contains(e)) {
			return (asdf<T>) e;
		}

		if (head == null) {
			List<T> s2 = new List(e, null);
			return new asdf<T>();
		} else {
			List<T> s3 = new List(e, null);
			while (s3.next != null) {
				add(e);

			}
		}

		add(e);

		return s;
	}

	public boolean contains(Object o) {
		List<T> s = new List(o, null);
		while (s.next != null){
			if (s.equals(o)) {
				return true;
				
			}
			s = s.next;
		}

		return false;
	}

	public boolean equals(Object o) {
		List<T> s = new List(o, null);
		if (this.getClass().equals(o.getClass())) {
			while (s.next != null) {
				if (!this.contains(o)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
    public String toString() {
        String out = "[";
        List<T> s = new List(null, null);
        if (s.next != null) {
            out += s.info;
            List<T> tmp = next.next;
            while (s.next != null) {
                out = out + "," + tmp.info;
                tmp = tmp.next;
            }
        }
        out += "]";
        return out;
    }


	public final class List<T> {
		public final List<T> next;
		public final T info;

		public List(T info, List<T> next) {
			this.next = next;
			this.info = info;
		}

	}
	
	public static void main(String args){
		
	}


}