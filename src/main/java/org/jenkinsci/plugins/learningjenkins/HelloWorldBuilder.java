package org.jenkinsci.plugins.learningjenkins;
import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample {@link Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link DescriptorImpl#newInstance(StaplerRequest)} is invoked
 * and a new {@link HelloWorldBuilder} is created. The created
 * instance is persisted to the project configuration XML by using
 * XStream, so this allows you to use instance fields (like {@link #group})
 * to remember the configuration.
 *
 * <p>
 * When a build is performed, the {@link #perform} method will be invoked. 
 *
 * @author Kohsuke Kawaguchi
 */
public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

    private final String group;
    private final String title;
    private final String numBuilds;
    private final String yaxis;
    private final String style;
    private final Boolean useDescr;
    private final Boolean exclZero;
    private final Boolean logarithmic;
    private final Boolean keepRecords;
    private final String yaxisMinimum;
    private final String yaxisMaximum;
    public String csvFileName;
    /** List of data series. */
    public List<Series> series;

    private List<Plot> plots;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public HelloWorldBuilder(String group, String title, String numBuilds, String yaxis, String style,
                             Boolean useDescr, Boolean exclZero, Boolean logarithmic, Boolean keepRecords,
                             String yaxisMinimum, String yaxisMaximum, String csvFileName, List<Series> series) {
        this.group = group;
        this.title = title;
        this.numBuilds = numBuilds;
        this.yaxis = yaxis;
        this.style = style;
        this.useDescr = useDescr;
        this.exclZero = exclZero;
        this.logarithmic = logarithmic;
        this.keepRecords = keepRecords;
        this.yaxisMinimum = yaxisMinimum;
        this.yaxisMaximum = yaxisMaximum;
        this.csvFileName = csvFileName;
    }

    public String getGroup() {
        return group;
    }

    public String getTitle() { return title; }

    public String getNumBuilds() { return numBuilds; }

    public String getYaxis() { return yaxis; }

    public String getStyle() { return style; }

    public Boolean getUseDescr() { return useDescr; }

    public Boolean getExclZero() { return exclZero; }

    public Boolean getLogarithmic() { return logarithmic; }

    public Boolean getKeepRecords() { return keepRecords; }

    public String getYaxisMinimum() { return yaxisMinimum; }

    public String getYaxisMaximum() { return yaxisMaximum; }

    public List<Series> getSeries() { return series; }



    @Override
    public void perform(Run<?,?> build, FilePath workspace, Launcher launcher, TaskListener listener) {
        // This is where you 'build' the project.

        plots = new ArrayList<>();
        Plot plot = new Plot(title, yaxis, group, numBuilds, csvFileName, style, false, false, false, false, yaxisMinimum, yaxisMaximum);
        plot.series = series;
        plot.addBuild(build, listener.getLogger(), workspace);
        plots.add(plot);
        build.addAction( new PlotBuildAction( build, plots ) );
    }

    // Overridden for better type safety.
    // If your plugin doesn't really define any property on Descriptor,
    // you don't have to do this.
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    /**
     * Descriptor for {@link HelloWorldBuilder}. Used as a singleton.
     * The class is marked as public so that it can be accessed from views.
     *
     * <p>
     * See <tt>src/main/resources/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly</tt>
     * for the actual HTML fragment for the configuration screen.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        /**
         * To persist global configuration information,
         * simply store it in a field and call save().
         *
         * <p>
         * If you don't want fields to be persisted, use <tt>transient</tt>.
         */
        private boolean useFrench;

        /**
         * In order to load the persisted global configuration, you have to 
         * call load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }

        public FormValidation doCheckName(@QueryParameter String value)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error("Please set a group");
            if (value.length() < 4)
                return FormValidation.warning("Isn't the group too short?");
            return FormValidation.ok();
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }

        /**
         * This human readable group is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Plot build";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            // To persist global configuration information,
            // set that to properties and call save().
            useFrench = formData.getBoolean("useFrench");
            // ^Can also use req.bindJSON(this, formData);
            //  (easier when there are many fields; need set* methods for this, like setUseFrench)
            save();
            return super.configure(req,formData);
        }

        /**
         * This method returns true if the global configuration says we should speak French.
         *
         * The method group is bit awkward because global.jelly calls this method to determine
         * the initial state of the checkbox by the naming convention.
         */
        public boolean getUseFrench() {
            return useFrench;
        }
    }
}

