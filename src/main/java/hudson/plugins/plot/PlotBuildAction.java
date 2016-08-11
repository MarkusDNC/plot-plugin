package hudson.plugins.plot;

import hudson.model.Action;
import hudson.model.InvisibleAction;
import hudson.model.Run;
import jenkins.tasks.SimpleBuildStep;
import org.kohsuke.stapler.StaplerProxy;
import org.kohsuke.stapler.export.ExportedBean;

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

    public void addPlots( List<Plot> plots ) {
        this.plots.addAll( plots );
    }
}
