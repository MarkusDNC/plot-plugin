# Plot Plugin for Jenkins

## Support
Currently this plugin supports Pipeline by choosing `Plot build` from `step: General Build Step` in the `Snippet Generator`.

This standalone plugin only supports Pipeline projects. Install original Plot plugin [found here] (https://wiki.jenkins-ci.org/display/JENKINS/Plot+Plugin) for additional Freestyle and Matrix support.

## Installation
Install [Maven] (https://maven.apache.org/guides/getting-started/windows-prerequisites.html) and use it to create a `.hpi` with:
```
mvn install
```
This will create the file `plot-plugin.hpi` in the target folder. Upload this file in your plugin handler in Jenkins under the Advanced tab.

## Using
In a Pipeline project, go to `Steps` and in `Sample Step` select `step: General Build Step`. Finally in `Build Step` select `Plot build`. Now you are presented with the form where you fill in the desired values before pressing `Generate Groovy`. It should look like something like this:
```groovy
step([$class: 'PlotBuilder', csvFileName: 'foo-bar.csv', csvSeries: [[displayTableFlag: false, exclusionValues: '', file: 'data.plot', inclusionFlag: 'OFF', url: '']], exclZero: false, group: 'Group1', keepRecords: false, logarithmic: false, numBuilds: '30', style: 'line', title: 'Title2', useDescr: false, yaxis: 'Sample', yaxisMaximum: '', yaxisMinimum: ''])
```