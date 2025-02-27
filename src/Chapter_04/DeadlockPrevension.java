package Chapter_04;
// linked - Stand-Alone
class Pen {

    public synchronized void writeWithPaper(Paper paper){
        System.out.println("writing on Paper, waiting for Pen ::");
        paper.getPaper();                                    // dependent on class Paper
    }
    public synchronized void getPen(){
        System.out.println("getting pen...");
    }
}

class Paper {

    public synchronized void writeOnPaper(Pen pen){
        System.out.println("writing with Pen, waiting for Paper ::");
        pen.getPen();                                          // dependent on class Pen
    }
    public synchronized void getPaper(){
        System.out.println("getting paper...");
    }
}

class Task1 implements Runnable {

    Paper paper = new Paper();
    Pen pen = new Pen();

    public Task1(Paper paper, Pen pen){
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
        pen.writeWithPaper(paper);
    }
}

class Task2 implements Runnable {

    Paper paper = new Paper();
    Pen pen = new Pen();

    public Task2(Paper paper, Pen pen){
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized (pen){                     // avoid deadlock
            paper.writeOnPaper(pen);
        }
    }
}

public class DeadlockPrevension {

    public static void main(String[] args) {

        Paper paper = new Paper();
        Pen pen = new Pen();

        Thread t1 = new Thread(new Task1(paper, pen));
        Thread t2 = new Thread(new Task2(paper, pen));

        t1.start();
        t2.start();

    }

}
