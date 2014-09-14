package com.litao.twill;

import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.twill.api.AbstractTwillRunnable;
import org.apache.twill.api.TwillController;
import org.apache.twill.api.TwillRunnerService;
import org.apache.twill.common.Services;
import org.apache.twill.yarn.YarnTwillRunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Tao Li on 9/7/14.
 */
public class HelloTwill {
    static Logger LOG = LoggerFactory.getLogger(HelloTwill.class);

    static class HelloTwillRunnable extends AbstractTwillRunnable {
        @Override
        public void run() {
            LOG.info("Hello World");
        }
    }

    public static void main(String[] args) throws Exception {
        YarnConfiguration conf = new YarnConfiguration();
        TwillRunnerService runner = new YarnTwillRunnerService(conf, "localhost:2181");
        runner.startAndWait();
        TwillController controller = runner.prepare(new HelloTwillRunnable()).start();
        Services.getCompletionFuture(controller).get();
    }
}
