# plot-plugin
Working on adding Pipeline support to the Jenkins Plot Plugin

## Where are we?
Currently this plugin supports Pipeline by choosing `Plot build` from `step: General Build Step` in the `Snippet Generator`.

## Using
Generate a Groovy script with the `Snippet Generator`, add a value to `csvFileName`

```groovy
step([$class: 'PlotBuilder', csvFileName: 'foo', csvSeries: [[displayTableFlag: false, exclusionValues: '', file: 'data.plot', inclusionFlag: 'OFF', url: '']], exclZero: false, group: 'Group1', keepRecords: false, logarithmic: false, numBuilds: '30', style: 'line', title: 'Title2', useDescr: false, yaxis: 'Sample', yaxisMaximum: '', yaxisMinimum: ''])
```

# Caution
The pipeline support has come at the cost of Freestyle support. Thou there has been no testing, changes have been made that will 
effect Freestyle projects.
