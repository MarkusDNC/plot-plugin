package org.jenkinsci.plugins.learningjenkins.steps;

import hudson.Extension;
import org.kohsuke.stapler.DataBoundConstructor;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Created by max on 2016-06-16.
 */
public class HelloWorldStep extends AbstractStepImpl{
    @DataBoundConstructor
    public HelloWorldStep() {}

    @Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {
        public DescriptorImpl() { super(HelloWorldStepExecution.class); }

        @Override
        public String getFunctionName() { return "helloWorld"; }

        @Nonnull
        @Override
        public String getDisplayName() { return "HellowWorld"; }
    }
}