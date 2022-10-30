public class UnionFind {
    public LLAddOnly[] sets;
    public int index = 0;

    public UnionFind(int size){
        sets = new LLAddOnly[size*size];
    }

    public void makeSet(Cell a){
        sets[index] = new LLAddOnly();
        sets[index].add(a);
        index++;
    }

    public LLAddOnly find(Cell a){
        return a.head;
    }

    public void union(Cell a, Cell b){
        LLAddOnly aHead = a.head;
        LLAddOnly bHead = b.head;
        Cell set2 = bHead.first;
        aHead.last.next = bHead.first;
        aHead.last = bHead.last;
        while (set2 != null){
            set2.head = a.head;
            set2 = set2.next;
        }
        for (int i = 0; i < sets.length; i++){
            if (bHead == sets[i]) {
                sets[i] = null;
            }
        }
    }

    public boolean oneSet(){
        int check = 0;
        for (LLAddOnly set : sets){
            if (set != null)
                check++;
        }
        return (check == 1);
    }
}
