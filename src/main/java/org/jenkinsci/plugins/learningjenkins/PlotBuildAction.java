package org.jenkinsci.plugins.learningjenkins;

import hudson.FilePath;
import hudson.model.Action;
import hudson.model.InvisibleAction;
import hudson.model.Run;
import jenkins.tasks.SimpleBuildStep;
import org.kohsuke.stapler.StaplerProxy;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.export.ExportedBean;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by max on 2016-06-20.
 */
@ExportedBean
public class PlotBuildAction extends InvisibleAction implements StaplerProxy, SimpleBuildStep.LastBuildAction{

    private Run<?, ?> run;
    private List<Plot> plots;

    public PlotBuildAction(Run<?, ?> run, List<Plot> plots) {
        this.run = run;
        this.plots = plots;
    }

    @Override
    public Collection<? extends Action> getProjectActions() {
        return Collections.<Action>singleton(new PlotProjectAction(run.getParent(), plots));
    }

    @Override
    public Object getTarget() {
        return null;
    }


    // called from href created in PlotProjectAction/index.jelly
    public PlotReport getDynamic(String group, StaplerRequest req, StaplerResponse rsp) throws IOException {
        return new PlotReport(run.getParent(), group, plots);
    }
}
