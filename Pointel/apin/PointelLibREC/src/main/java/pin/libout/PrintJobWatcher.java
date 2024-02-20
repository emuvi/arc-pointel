package pin.libout;

import javax.print.*;
import javax.print.event.*;

class PrintJobWatcher {
    boolean done = false;

    PrintJobWatcher(DocPrintJob job) {
        job.addPrintJobListener(new PrintJobAdapter() {
            @Override public void printJobCanceled(PrintJobEvent pje) { allDone(); }
            @Override public void printJobCompleted(PrintJobEvent pje) { allDone(); }
            @Override public void printJobFailed(PrintJobEvent pje) { allDone(); }
            @Override public void printJobNoMoreEvents(PrintJobEvent pje) { allDone(); }
            void allDone() {
                synchronized (PrintJobWatcher.this) {
                    done = true;
                    PrintJobWatcher.this.notify();
                }
            }
        });
    }

    public synchronized void waitForDone() {
        try { while (! done) { wait(); }
        } catch (InterruptedException e) { }
    }
}