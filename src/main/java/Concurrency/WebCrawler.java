package Concurrency;

import org.omg.CORBA.TIMEOUT;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class WebCrawler {
    private volatile TrackingExecutor executor;
    private final Set<URL> urlsToCrawler = new HashSet<>();

    public synchronized void start(){
        executor = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawler){
            submitCrawlTask(url);
        }
        urlsToCrawler.clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUncrawled(executor.shutdownNow());
            if(executor.awaitTermination(2,TimeUnit.SECONDS))
                saveUncrawled(executor.getCancelledTasks());
        }finally {
            executor = null;
        }
    }

    protected abstract List<URL> processPage(URL url);

    public void submitCrawlTask(URL url){
        executor.execute(new CrawlTask(url));
    }

    private void saveUncrawled(List<Runnable> uncrawled){
        for (Runnable runnable : uncrawled)
            urlsToCrawler.add(((CrawlTask) runnable).getUrl());
    }

    private class CrawlTask implements Runnable{
        private final URL url;

        public CrawlTask(URL url){
            this.url = url;
        }

        @Override
        public void run() {
            for (URL link : processPage(url)){
                if(Thread.currentThread().isInterrupted())
                    return;
                submitCrawlTask(link);
            }
        }

        public URL getUrl(){
            return url;
        }
    }
}
