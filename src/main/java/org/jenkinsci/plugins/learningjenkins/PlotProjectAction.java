package org.jenkinsci.plugins.learningjenkins;

import hudson.model.Action;
import hudson.model.Job;

/**
 * Created by max on 2016-06-20.
 */
public class PlotProjectAction implements Action{

    private final Job<?, ?> job;

    public PlotProjectAction(Job<?, ?> job){
        this.job = job;
    }

    @Override
    public String getIconFileName() {
        return "/plugin/learning-jenkins/graph.gif";
    }

    @Override
    public String getDisplayName() {
        return "Plots";
    }

    @Override
    public String getUrlName() {
        return "plot";
    }
}
