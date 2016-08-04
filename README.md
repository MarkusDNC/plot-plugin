# Plot Plugin for Jenkins
Working on adding Pipeline support to the Jenkins Plot Plugin
This is a beta version at best so use it as such.

## Where are we?
Currently this plugin supports Pipeline by choosing `Plot build` from `step: General Build Step` in the `Snippet Generator`. Note that this is an ongoing project, feel free to contribute.

In the beginning this project was called `learning-jenkins` (I was doing just that and then used that project as a foundation of trying to get the Plot plugin to work. Not a good practice I know). I have renamed the project but if you should see any residue of that in the code feel free to correct it or inform me.

## Using
Generate a Groovy script with the `Snippet Generator`, add a value to `csvFileName` as seen below.

```groovy
step([$class: 'PlotBuilder', csvFileName: 'foo-bar.csv', csvSeries: [[displayTableFlag: false, exclusionValues: '', file: 'data.plot', inclusionFlag: 'OFF', url: '']], exclZero: false, group: 'Group1', keepRecords: false, logarithmic: false, numBuilds: '30', style: 'line', title: 'Title2', useDescr: false, yaxis: 'Sample', yaxisMaximum: '', yaxisMinimum: ''])
```

# Caution
The pipeline support has come at the cost of Freestyle support. Thou there has been no testing, changes have been made that will 
effect Freestyle projects. In those cases use the original plot plugin found [here](https://github.com/jenkinsci/plot-plugin).
