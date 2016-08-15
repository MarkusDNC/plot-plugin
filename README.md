# Plot Plugin for Jenkins
Working on adding Pipeline support to the Jenkins Plot Plugin.

This is a beta version at best so use it as such.

## Where are we?
Currently this plugin supports Pipeline by choosing `Plot build` from `step: General Build Step` in the `Snippet Generator`.

Note that this is an ongoing project, feel free to contribute.

## Installation
Use Maven to create a `.hpi` with:
```
mvn install
```
This will create the file `plot-plugin.hpi` in the target folder. Upload this file in your plugin handler in Jenkins under the Advanced tab.

## Using
In a Pipeline project, go to `Steps` and in `Sample Step` select `step: General Build Step`. Finally in `Build Step` select `Plot build`. Now you are presented with the form where you fill in the desired values before pressing `Generate Groovy`. It should look like something like this:
```groovy
step([$class: 'PlotBuilder', csvFileName: 'foo-bar.csv', csvSeries: [[displayTableFlag: false, exclusionValues: '', file: 'data.plot', inclusionFlag: 'OFF', url: '']], exclZero: false, group: 'Group1', keepRecords: false, logarithmic: false, numBuilds: '30', style: 'line', title: 'Title2', useDescr: false, yaxis: 'Sample', yaxisMaximum: '', yaxisMinimum: ''])
```

# Caution
The pipeline support has come at the cost of Freestyle support. In the beginning I tried to combine support for both Freestyle and Pipline, but in the end I decided to focus on support for Pipeline. You can find the original plot plugin [here]
(https://github.com/jenkinsci/plot-plugin) or in your plugin handler in Jenkins. These two plugins should work togheter in Jenkins.
