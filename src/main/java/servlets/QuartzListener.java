package servlets;

import jobs.EmailBirthdayJob;
import org.quartz.*;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class QuartzListener implements ServletContextListener {
    private Scheduler scheduler;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent ctx) {
        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(EmailBirthdayJob.class)
                .withIdentity("myJob", "group1").build();

        // Trigger the job to run now, and then every 10 minutes
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInHours(24).repeatForever())
                .build();
        // Tell quartz to schedule the job using our trigger

        try {
            scheduler = ((StdSchedulerFactory) ctx.getServletContext()
                    .getAttribute(
                            QuartzInitializerListener.QUARTZ_FACTORY_KEY))
                    .getScheduler();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {

        }
    }
}